package com.example.apibasic.post.api;

import com.example.apibasic.post.dto.PatchCreateDTO;
import com.example.apibasic.post.dto.PostCreateDTO;
import com.example.apibasic.post.dto.PostResponseDTO;
import com.example.apibasic.post.entity.PostEntity;
import com.example.apibasic.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    //@Autowired // 스프링 컨테이너에게 의존객체를 자동주입해달라, 생성자가 단 하나면 생략 가능
//    public PostApiController(PostRepository postRepository){
//        this.postRepository = postRepository;
//    }
    // setter 생성자 -> 바뀔 가능성이 있음

    //게시물 목록 조회
    @GetMapping()
    public ResponseEntity<?> list(){
        log.info("/posts GET request");
        // 엔터티 리스트를 DTO 리스트로 면환해서 클라이언트에 응답
        List<PostEntity> list = postRepository.findAll();

        List<PostResponseDTO> responseDTOList = list.stream()
                .map(PostResponseDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(responseDTOList);
    }
    //게시물 개별 조회

    @GetMapping("/{postNo}")
    public ResponseEntity<?> detail(@PathVariable/*("postNo") 생략 가능*/ Long postNo){
        log.info("/posts/{} GET request", postNo);
        PostEntity post = postRepository.findOne(postNo);
        return ResponseEntity.ok().body(post);
    }
    //게시물 등록
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody PostCreateDTO createDTO){
        log.info("/posts POST request");
        // dto 를 entity 변환 작업
        PostEntity entity = createDTO.toEntity();
        log.info("게시물 정보: {}", entity);

        postRepository.save(entity);
        return ResponseEntity.ok().body("INSERT-SUCCESS");
    }

    //게시물 수정
    @PatchMapping("/{postNo}")
    public ResponseEntity<?> modify(@RequestBody PatchCreateDTO patchCreateDTO, @PathVariable Long postNo){
        log.info("/posts/{} Patch request", postNo);
        PostEntity entity = patchCreateDTO.toEntity(postNo);
        log.info("게시물 정보: {}", entity);
        postRepository.save(entity);
        return ResponseEntity.ok().body("INSERT-SUCCESS");
    }
    @DeleteMapping("/{postNo}")
    public ResponseEntity<?> delete(@PathVariable/*("postNo") 생략 가능*/ Long postNo){
        log.info("/posts/{} delete request", postNo);
        postRepository.delete(postNo);
        return ResponseEntity.ok().body("INSERT-SUCCESS");
    }
}
