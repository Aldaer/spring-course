package beans.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ControllerAdvice("beans.controller")
@EnableWebMvc
public class GlobalExceptionHandler {

    @ExceptionHandler
    public String handleException(Exception e, Model model) {
        model.addAttribute("error", e);
        return "error.html";
    }
}
