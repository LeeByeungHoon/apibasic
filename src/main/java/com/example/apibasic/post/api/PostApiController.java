package com.example.apibasic.post.api;

import com.example.apibasic.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// 리소스 : 게시물 (Post)
/*
    게시물 목록 조회: /posts       - GET
    게시물 개별 조회: /posts/{id}  - GET
    게시물 등록: /posts           - POST
    게시물 수정: /posts/{id}      - PATCH
    게시물 삭제: /posts/{id}      - DELETE
 */
@RestController
@Slf4j
@RequestMapping("/posts")
@RequiredArgsConstructor //final 생성자 선언하면 자동으로 생성자 생성
public class PostApiController {

    //PostRepository 에게 의존하는 관계
    private final PostRepository postRepository;

    //@Autowired // 스프링 컨테이너에게 의존객체를 자동주입해달라, 생성자가 단 하나면 생략 가능
//    public PostApiController(PostRepository postRepository){
//        this.postRepository = postRepository;
//    }
    // setter 생성자 -> 바뀔 가능성이 있음

    //게시물 목록 조회
    @GetMapping()
    public ResponseEntity<?> list(){
        log.info("/posts GET request");
        return null;
    }
    //게시물 개별 조회
    @GetMapping("/{postNo}")
    public ResponseEntity<?> detail(@PathVariable/*("postNo") 생략 가능*/ Long postNo){
        log.info("/posts/{} GET request", postNo);
        return null;
    }
    //게시물 등록
    @PostMapping()
    public ResponseEntity<?> create(){
        log.info("/posts POST request");
        return null;
    }

    //게시물 수정
    @PatchMapping("/{postNo}")
    public ResponseEntity<?> modify(@PathVariable/*("postNo") 생략 가능*/ Long postNo){
        log.info("/posts/{} Patch request", postNo);
        return null;
    }
    @DeleteMapping("/{postNo}")
    public ResponseEntity<?> delete(@PathVariable/*("postNo") 생략 가능*/ Long postNo){
        log.info("/posts/{} delete request", postNo);
        return null;
    }
}
