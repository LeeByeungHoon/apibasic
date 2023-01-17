package com.example.apibasic.post.service;

import com.example.apibasic.post.dto.*;
import com.example.apibasic.post.entity.PostEntity;
import com.example.apibasic.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service //스프링 빈 등록
public class PostService {

    @Autowired
    public PostRepository postRepository;
    public PostEntity postEntity;

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
        log.info(list.toString());
        return listResponseDTO;
    }

    // 개별조회
    public PostDetailResponseDTO getDetail(Long postNo) {

        PostEntity post = postRepository.findById(postNo).orElseThrow(() -> new RuntimeException("검색된 자료가 없습니다."));
        return new PostDetailResponseDTO(post);
    }

    public PostDetailResponseDTO insert(final PostCreateDTO createDTO) throws RuntimeException{
        // dto를 entity 변환 작업

        PostEntity entity = PostEntity.builder()
                .title(createDTO.getTitle())
                .content(createDTO.getContent())
                .writer(createDTO.getWriter())
                .build();
        PostEntity savedPost = postRepository.save(entity);
        // 저장된 객체를 DTO로 변환해서 반환
        return new PostDetailResponseDTO(savedPost);
    }

    public PostDetailResponseDTO update(Long postNo, final PatchCreateDTO patchCreateDTO) throws RuntimeException{
        final PostEntity post = postRepository.findById(postNo).orElseThrow(() -> new RuntimeException("수정 전 데이터가 존재하지 않습니다."));
        PostEntity entity = patchCreateDTO.toEntity(post);
        postRepository.save(entity);
        return new PostDetailResponseDTO(entity);
    }
    public void delete(Long postNo) throws RuntimeException{
        postRepository.deleteById(postNo);
    }
}
