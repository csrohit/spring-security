package com.csrohit.springsecurity.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    @RequestMapping("/error")
    public RedirectView handleError(HttpServletRequest request) {
        String errorMsg = "";
        int httpErrorCode = getErrorCode(request);
        String uri = request.getRequestURI();

        switch (httpErrorCode) {

            case 404: {
                errorMsg = "/page";
                break;
            }
            default: {
                errorMsg = "/service";
                break;
            }
        }

        return new RedirectView(errorMsg);

    }

    @Override
    public String getErrorPath() {
        System.out.println("here");
        return null;
    }

    @RequestMapping("/page")
    public String page() {
        return "index.html";
    }
    @RequestMapping("/service")
    public ResponseEntity<String> service() {
        return new ResponseEntity<>("redirected", HttpStatus.NOT_FOUND);
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
                .getAttribute("javax.servlet.error.status_code");
    }
}
