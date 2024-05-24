package com.example.board.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        // 에러 페이지로 이동할 경로를 반환합니다.
        return "error"; // 예를 들어, "error.html"을 사용한다면 "error"를 반환합니다.
    }


    public String getErrorPath() {
        return "/error";
    }
}
