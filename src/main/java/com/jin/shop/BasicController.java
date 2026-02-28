package com.jin.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Controller // @Controller 붙이면 main함수에 쏴줌
public class BasicController {

    @GetMapping("/mypage")
    @ResponseBody
    String mypage() {
        return "마이페이지";
    }
}
