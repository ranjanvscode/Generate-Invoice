package com.invoice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/home")
    public String homaPage() {
        return "home";
    }

    @GetMapping("/invoice")
    public String invoicePage() {
        return "invoice";
    }

    @GetMapping("/")
    public String invoicePage2() {
        return "invoice";
    }

    @GetMapping("/error")
    public String errorPage() {
        return "error";
    }
}
