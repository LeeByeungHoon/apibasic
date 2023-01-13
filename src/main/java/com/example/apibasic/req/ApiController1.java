package com.example.apibasic.req;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller //클라이언트가 보낸 요청을 받을 수 있음
@ResponseBody
@Slf4j // 로그 라이브러리
@RequestMapping("/say")
public class ApiController1 {

    //요청을 받아서 처리할 메서드
    @RequestMapping(value = "/hello", method = {POST, GET})
    public String hello(HttpServletRequest request) {

        log.info("hello spring! - {}", request.getMethod());
        log.warn("warn 로그");
        log.trace("trace 로그");
        log.error("error 로그");
        log.debug("debug 로그");
        return "hello spring!";
    }

    //GET 요청만 따로 처리
    //@RequestMapping(value = "/greet", method = GET,POST) -> method 2개 이상일때
    @GetMapping(value = "/greet")
    public String greet(){
        log.info("/greet GET 요청");
        return "greet";
    }
    @PostMapping(value = "/greet")
    public String greet2(){
        log.info("/greet POST 요청");
        return "greet";
    }

    @RequestMapping(value = "/hello2",method = GET)
    public String sayHello(){
        log.info("/greet POST 요청");
        return "greet";
    }

    // 요청 헤더 읽기
    @GetMapping("/header")
    public String header(HttpServletRequest request) {
        String header = request.getHeader("host");
        String accept = request.getHeader("accept");
        String pet = request.getHeader("pet");
        log.info("======== header info ========");
        log.info("# headers : {}", header);
        log.info("# accept : {}", accept);
        log.info("# pet : {}", pet);

        return "";
    }

}
