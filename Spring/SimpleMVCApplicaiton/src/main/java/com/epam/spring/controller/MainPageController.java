package com.epam.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainPageController {


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }


}
