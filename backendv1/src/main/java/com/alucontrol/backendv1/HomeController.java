package com.alucontrol.backendv1;


import org.springframework.web.bind.annotation.GetMapping;

public class HomeController
{
    @GetMapping("/")
    public String home()
    {
        return "index";
    }
}
