package beans.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice("beans.controller")
public class GlobalExceptionHandler {

    @ExceptionHandler
    public String handleException(@ModelAttribute Exception error) {
        return "error.html";
    }
}
