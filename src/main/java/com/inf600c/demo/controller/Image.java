package com.inf600c.demo.controller;

import com.inf600c.demo.utils.S3Properties;
import com.inf600c.demo.utils.Utils;
import com.inf600c.demo.utils.S3Handler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayOutputStream;
import java.io.File;

@Controller
public class Image {
    private final S3Properties awsS3props = S3Properties.getInstance();
    private final S3Handler storageS3 = new S3Handler(awsS3props, awsS3props.getS3Conn());

    @PostMapping(value = "/upload", produces = "application/json")
    public ResponseEntity<String> uploadFile(@RequestPart(value = "file") MultipartFile multipartFile) {
        // create temporary file
        File file = Utils.convertMultiPartFileToFile(multipartFile);
        // push it to the s3 bucket
        String s3Filename = storageS3.uploadFile(file);
        // delete temporary file
        file.delete();
        String response = "{ " + "\"filename\": " + "\"" + s3Filename + "\" }";

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/storage/{filename}")
    public ResponseEntity<byte[]> getFile(@PathVariable String filename) {
        ByteArrayOutputStream fileIS = storageS3.getFile(filename);

        return ResponseEntity.ok()
                .contentType(Utils.getContentType(filename))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + filename + "\"")
                .body(fileIS.toByteArray());
    }
}
