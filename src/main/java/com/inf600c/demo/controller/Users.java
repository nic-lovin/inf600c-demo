package com.inf600c.demo.controller;

import com.inf600c.demo.model.User;
import com.inf600c.demo.persistence.Database;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class Users {
    @PostMapping(value = "/add", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody User user) {
        int id = Database.create(user);
        return "{ " + "\"id\": " + "\"" + id + "\"}";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String update(@RequestBody User user) {
        Database.update(user);
        return "ok";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable("id") int id) {
        Database.delete(id);
        return "ok";
    }

    @GetMapping(value = "/user/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public String getFile(@PathVariable int id) {
        User user = Database.getUser(id);
        if (user == null) {
         return "err";
        }
        String response = "{ " +
                "\"firstname\": " + "\"" + user.getFirstname() + "\"," +
                "\"lastname\": " + "\"" + user.getLastname() + "\"," +
                "\"id\": " + "\"" + user.getId() + "\"" +
                " }";

        return response;
    }

    @GetMapping(value = "/addImageToUser", produces = "application/json")
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public String mapUserToImage() {
        return "not implemented yet";
    }
}
