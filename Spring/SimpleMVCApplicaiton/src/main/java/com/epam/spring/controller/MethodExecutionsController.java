package com.epam.spring.controller;


import com.epam.spring.model.MethodExecutionRecord;
import com.epam.spring.service.MethodExecutionService;
import com.epam.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("executions")
public class MethodExecutionsController {

    @Autowired
    private MethodExecutionService methodExecutionService;

    @Autowired
    private UserService userService;


    @RequestMapping("/all")
    public ModelAndView all() {
        return new ModelAndView("methodExecutions", "executions", methodExecutionService.getAll());
    }

    @RequestMapping("/long")
    public ModelAndView longExecutions() {
        return new ModelAndView("methodExecutions", "executions", methodExecutionService.getLongRunningMethods());

    }

    @RequestMapping("/startLong")
    public String startLong() {
        try {
            userService.longMethod();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "redirect:/executions/long";
    }
}
