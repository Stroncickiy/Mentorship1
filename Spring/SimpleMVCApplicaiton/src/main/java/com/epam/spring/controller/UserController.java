package com.epam.spring.controller;


import com.epam.spring.model.User;
import com.epam.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    protected UserService userService;

    @RequestMapping(path = "{userId}")
    public ModelAndView userPage(@PathVariable Long userId) {
        return new ModelAndView("user", "user", userService.getById(userId));
    }

    @RequestMapping(path = "add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute User userToAdd) {
        userToAdd.setProcessed(false);
        userService.register(userToAdd);
        return "redirect:/users";
    }


}
