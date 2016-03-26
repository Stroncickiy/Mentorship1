package com.epam.spring.controller;


import com.epam.spring.service.MethodExecutionService;
import com.epam.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("executions")
public class MethodExecutionsController {

    @Autowired
    private MethodExecutionService methodExecutionService;

    @Autowired
    private UserService userService;


    @RequestMapping("/all")
    public String all(Model model) {
        model.addAttribute("executions", methodExecutionService.getAll());
        return "methodExecutions";
    }

    @RequestMapping("/long")
    public String longExecutions(Model model) {
        model.addAttribute("executions", methodExecutionService.getLongRunningMethods());
        return "methodExecutions";

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
