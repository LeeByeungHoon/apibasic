package com.example.apibasic.post.service;

import com.example.apibasic.post.dto.PatchCreateDTO;
import com.example.apibasic.post.dto.PostCreateDTO;
import com.example.apibasic.post.dto.PostListResponseDTO;
import com.example.apibasic.post.dto.PostResponseDTO;
import com.example.apibasic.post.entity.PostEntity;
import com.example.apibasic.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service //스프링 빈 등록
public class PostService {

    private final PostRepository postRepository;
    // 목록 조회 중간처리
    public PostListResponseDTO getList() {
        List<PostEntity> list = postRepository.findAll();

        if (list.isEmpty()){
            throw new RuntimeException("조회 결과가 없습니다.");
        }
        //throw 오류를 유발, throws 오류를 호출한 곳에 던지다
        List<PostResponseDTO> responseDTOList = list.stream()
                .map(PostResponseDTO::new)
                .collect(Collectors.toList());


        PostListResponseDTO listResponseDTO = PostListResponseDTO.builder()
                .count(responseDTOList.size())
                .posts(responseDTOList)
                .build();

        return listResponseDTO;
    }

    // 개별조회
    public PostResponseDTO getDetail(Long postNo) {
        PostEntity post = postRepository.findOne(postNo);

        if(post == null) throw new RuntimeException(postNo + "번 게시물이 존재하지 않습니다.");

        return new PostResponseDTO(post);

    }

    public boolean insert(final PostCreateDTO createDTO){
        // dto를 entity 변환 작업
        PostEntity entity = createDTO.toEntity();

        return postRepository.save(entity);
    }

    public boolean update(Long postNo, final PatchCreateDTO patchCreateDTO){
        final PostEntity post = postRepository.findOne(postNo);
        final PostEntity entity = patchCreateDTO.toEntity(post);
        return postRepository.save(entity);
    }
    public boolean delete(Long postNo){
        return  postRepository.delete(postNo);
    }
}
