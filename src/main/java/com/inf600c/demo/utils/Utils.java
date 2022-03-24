package com.inf600c.demo.utils;

import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.time.LocalDateTime;

public class Utils {
    public static File convertMultiPartFileToFile(MultipartFile multipartFile) {
        File file = new File(multipartFile.getOriginalFilename());
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(multipartFile.getBytes());
        } catch (Exception e) {
            // logger.error("Error +", e.getMessage());
            // commented out because I heard about log4shell
        }

        return file;
    }
    public static MediaType getContentType(String filename) {
        if (StringUtils.isEmpty(filename)) {
            return MediaType.APPLICATION_OCTET_STREAM;
        }

        // we get the extension
        String[] arr = filename.split("\\.");
        String type = arr[arr.length - 1];
        // we only want to handle legitimate files
        switch (type) {
            case "txt":
                return MediaType.TEXT_PLAIN;
            case "png":
                return MediaType.IMAGE_PNG;
            case "jpg":
                return MediaType.IMAGE_JPEG;
            default:
                return MediaType.APPLICATION_OCTET_STREAM;
        }
    }

    public static ByteArrayOutputStream toByteArrayOutputStream(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int length;
        while ((length = is.read(buffer, 0, buffer.length)) != -1) {
            baos.write(buffer, 0, length);
        }

        return baos;
    }

    // create unique filename so no one can overwrite images
    public static String genFilename(File file) {
        return file.getName() + "_" + LocalDateTime.now();
    }
}
