package com.example.apibasic.post.api;

import com.example.apibasic.post.dto.PatchCreateDTO;
import com.example.apibasic.post.dto.PostCreateDTO;
import com.example.apibasic.post.dto.PostListResponseDTO;
import com.example.apibasic.post.dto.PostResponseDTO;
import com.example.apibasic.post.entity.PostEntity;
import com.example.apibasic.post.repository.PostRepository;
import com.example.apibasic.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

// 리소스 : 게시물 (Post)
/*
    게시물 목록 조회: /posts       - GET
    게시물 개별 조회: /posts/{id}  - GET
    게시물 등록: /posts           - POST
    게시물 수정: /posts/{id}      - PATCH
    게시물 삭제: /posts/{id}      - DELETE
 */
// 개별조회 수정 삭제
@RestController
@Slf4j
@RequestMapping("/posts")
@RequiredArgsConstructor //final 생성자 선언하면 자동으로 생성자 생성
public class PostApiController {

    //PostRepository 에게 의존하는 관계
    private final PostRepository postRepository;
    private final PostService postService;
    //@Autowired // 스프링 컨테이너에게 의존객체를 자동주입해달라, 생성자가 단 하나면 생략 가능
//    public PostApiController(PostRepository postRepository){
//        this.postRepository = postRepository;
//    }
    // setter 생성자 -> 바뀔 가능성이 있음

    //게시물 목록 조회
    @GetMapping
    public ResponseEntity<?> list(){
        log.info("/posts GET request");
        // service 에서 오류를 전달하면 처리
        //ctrl + alt + t -> try catch
        try {
            PostListResponseDTO listResponseDTO = postService.getList();
            return ResponseEntity.ok().body(listResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    //게시물 개별 조회

    @GetMapping("/{postNo}")
    public ResponseEntity<?> detail(@PathVariable/*("postNo") 생략 가능*/ Long postNo){
        log.info("/posts/{} GET request", postNo);
        try {
            PostResponseDTO dto = postService.getDetail(postNo);
            return ResponseEntity.ok().body(dto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    //게시물 등록
    @PostMapping()
    public ResponseEntity<?> create(@Validated @RequestBody PostCreateDTO createDTO,
                                    BindingResult result // 검증 에러 정보를 갖고 있는 객체
    ){
        boolean flag = postService.insert(createDTO);
        log.info("게시물 정보: {}", flag);
        if (result.hasErrors()){ // 검증에러가 발생할 시 true 리턴
            List<FieldError> fieldErrors = result.getFieldErrors();
            return ResponseEntity
                    .internalServerError().body(fieldErrors.get(0).getDefaultMessage());

        }
        return ResponseEntity.ok().body("INSERT-SUCCESS");
    }

    //게시물 수정
    @PatchMapping("/{postNo}")
    public boolean modify(@RequestBody PatchCreateDTO patchCreateDTO, @PathVariable Long postNo){
        log.info("/posts/{} Patch request", postNo);
        return postService.update(postNo, patchCreateDTO);
    }
    @DeleteMapping("/{postNo}")
    public boolean delete(@PathVariable/*("postNo") 생략 가능*/ Long postNo){
        log.info("/posts/{} delete request", postNo);
        return postService.delete(postNo);
    }
}
