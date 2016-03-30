package com.epam.spring.controller;


import com.epam.spring.model.User;
import com.epam.spring.service.UserService;
import com.epam.spring.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String allUsersPage(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }


    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String addUser(Model model) {
        model.addAttribute("newUser", new User());
        return "addUser";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute @Valid User newUser, BindingResult bindingResult, Model model) {
        userValidator.validate(newUser, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("message", bindingResult.getAllErrors());
            return "message";
        }
        userService.register(newUser);
        return "redirect:/users/all";
    }


    @RequestMapping(path = "edit/{userId}", method = RequestMethod.GET)
    public String openUpdateUserData(Model model, @PathVariable Long userId) {
        model.addAttribute("userToEdit", userService.getById(userId));
        return "editUser";
    }

    @RequestMapping(path = "edit", method = RequestMethod.POST)
    public String updateUserData(@ModelAttribute User userToEdit) {
        userService.update(userToEdit);
        return "redirect:/users/all";
    }


    @RequestMapping(path = "remove/{userId}")
    public String removeUser(@PathVariable("userId") Long id) {
        userService.remove(id);
        return "redirect:/users/all";
    }

    @RequestMapping(path = "process")
    public String processAllUsers() {
        userService.processAllUsers();
        return "redirect:/users/all";
    }


}
