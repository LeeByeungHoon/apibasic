package com.example.apibasic.post.api;

import com.example.apibasic.post.dto.*;
import com.example.apibasic.post.entity.PostEntity;
import com.example.apibasic.post.repository.PostRepository;
import com.example.apibasic.post.service.PostService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<?> list(PageRequestDTO pageRequestDTO){
        log.info("/posts GET request");
        log.info("request page info - {}", pageRequestDTO);
        // service 에서 오류를 전달하면 처리
        //ctrl + alt + t -> try catch
        try {
            PostListResponseDTO listResponseDTO = postService.getList(pageRequestDTO);
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
            PostDetailResponseDTO dto = postService.getDetail(postNo);
            return ResponseEntity.ok().body(dto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    //게시물 등록
    @PostMapping()
    @Parameters({
            @Parameter(name = "작성자", description = "게시물 작성자를 입력", example = "김철수"),
            @Parameter(name = "내용", description = "글 내용을 입력", example = "ㅁㄴㅇㄴㅁㅇㅇㄴㄴㅇ")
    })
    public ResponseEntity<?> create(@Validated @RequestBody PostCreateDTO createDTO,
                                    BindingResult result // 검증 에러 정보를 갖고 있는 객체
    ){
        PostDetailResponseDTO flag = null;

        if (createDTO == null){
            return ResponseEntity.badRequest().body("게시물 정보를 전달해주세요.");
        }
        if (result.hasErrors()){ // 검증에러가 발생할 시 true 리턴
            List<FieldError> fieldErrors = result.getFieldErrors();
            return ResponseEntity
                    .internalServerError().body(fieldErrors.get(0).getDefaultMessage());
        }
        try {
            flag = postService.insert(createDTO);
            return ResponseEntity.ok().body(flag);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }
    }

    //게시물 수정
    @PatchMapping("/{postNo}")
    public ResponseEntity modify(@RequestBody PatchCreateDTO patchCreateDTO, @PathVariable Long postNo){
        log.info("/posts/{} Patch request", postNo);
        try {
            PostDetailResponseDTO responseDTO = postService.update(postNo, patchCreateDTO);
            return ResponseEntity
                    .ok()
                    .body(responseDTO);
        } catch (RuntimeException e) {
            log.error("modify fail : caused by - {}", e.getMessage());
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }
    }
    @DeleteMapping("/{postNo}")
    public ResponseEntity delete(@PathVariable/*("postNo") 생략 가능*/ Long postNo){
        log.info("/posts/{} delete request", postNo);
        try {
            postService.delete(postNo);
            return ResponseEntity.ok().body("Delete Success");
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
