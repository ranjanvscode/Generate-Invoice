package com.invoice.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFound.class)
    public String handleResourceNotFound3(ResourceNotFound ex, Model model) {
        Map<String, Object> errorAttributes = new HashMap<>();
        errorAttributes.put("error", ex.getMessage());
        errorAttributes.put("message", "We're sorry, the requested resource was not found. Please try again.");
        errorAttributes.put("status","Error Code: 404");
        model.addAttribute("errorAttributes", errorAttributes);
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleResourceNotFound2(Exception ex) {
        return "error";
    }
}
