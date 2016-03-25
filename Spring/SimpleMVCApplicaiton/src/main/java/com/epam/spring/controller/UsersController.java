package com.epam.spring.controller;

import com.epam.spring.model.User;
import com.epam.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView allUsersPage() {
        return new ModelAndView("users", "users", userService.getAll());
    }


    @RequestMapping(path = "add", method = RequestMethod.GET)
    public ModelAndView addUser() {
        return new ModelAndView("addUser", "userToAdd", new User());
    }


    @RequestMapping(path = "update/{userId}")
    public String updateUserData(@PathVariable("usersId") Long id, @RequestParam String firstName, @RequestParam String lastName, @RequestParam boolean processed) {
        User userById = userService.getById(id);
        userById.setFirstName(firstName);
        userById.setLastName(lastName);
        userById.setProcessed(processed);
        userService.update(userById);
        return "redirect:/user/" + id;
    }


    @RequestMapping(path = "remove/{userId}")
    public String removeUser(@PathVariable("userId") Long id) {
        userService.remove(id);
        return "redirect:/users";
    }

    @RequestMapping(path = "process")
    public String processAllUsers() {
        userService.processAllUsers();
        return "redirect:/users";
    }


}
