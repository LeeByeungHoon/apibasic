package com.example.apibasic.res;

import com.example.apibasic.req.ApiController2;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


// @ResponseBody //JSON 형식으로 넘김
// @Controller
@RestController // @ResponseBody + @Controller
@Slf4j
public class ApiController3 {

    @GetMapping("/res1")
    public String res1(){
        log.info("/res1 GET!!");
        return "hello";
    }
    @GetMapping(value = "/res2", produces = "application/json") // produces 기본 값 application/json
    public List<String> res2(){
        log.info("/res2 GET!!");
        return List.of("짜장면","볶음밥","탕수육");
    }

    @GetMapping(value = "/res3", produces = "application/json")
    public ApiController2.OrderInfo res3(){
        log.info("/res3 GET!!");
        ApiController2.OrderInfo orderInfo = new ApiController2.OrderInfo();
        orderInfo.setGoodsAmount(2);
        orderInfo.setOrderNo(12L);
        orderInfo.setGoodsName("세탁기");
        return orderInfo;
    }

    //사원 목록 정보
    @GetMapping("/employees")
    public List<Employee> empList() {
        return List.of(
                new Employee("홍길동1","영업부1", LocalDate.of(2019,12,4)),
                new Employee("홍길동2","영업부2", LocalDate.of(2020,12,5)),
                new Employee("홍길동3","영업부3", LocalDate.of(2021,12,6))
        );
    }
    @Setter@Getter@ToString
    @NoArgsConstructor
    @EqualsAndHashCode
    @AllArgsConstructor
    @Builder
    public static class Employee {
        private String empName;
        private String deptNmae;
        private LocalDate hireDate;
    }

    // 응답시에 응답헤더정보와 응답상태코드를 조작하려면 ResponseEntity 객체 사용
    @GetMapping("/res4")
    public ResponseEntity<?> res4(String nick) {
        if(nick == null || nick.equals("")){
            return ResponseEntity.badRequest().build();
        }
        //응답 헤더 생성
        HttpHeaders headers = new HttpHeaders();
        headers.set("department","business");
        headers.set("a","b");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new Employee(nick,"영업부",LocalDate.of(2019,11,12)));
    }
}