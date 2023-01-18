package com.example.apibasic.post.service;

import com.example.apibasic.post.dto.*;
import com.example.apibasic.post.entity.HashTagEntity;
import com.example.apibasic.post.entity.PostEntity;
import com.example.apibasic.post.repository.HashTagRepository;
import com.example.apibasic.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service //스프링 빈 등록
public class PostService {

    @Autowired
    private final PostRepository postRepository;
    private final HashTagRepository hashTagRepository;
    // 목록 조회 중간처리
    public PostListResponseDTO getList(PageRequestDTO pageRequestDTO) {

        PageRequest pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1,
                pageRequestDTO.getSizePerPage(),
                Sort.Direction.DESC,
                "createDate"
        );

        final Page<PostEntity> pageData = postRepository.findAll(pageable);
        List<PostEntity> list = pageData.getContent();
        if (list.isEmpty()){
            throw new RuntimeException("조회 결과가 없습니다.");
        }
        //throw 오류를 유발, throws 오류를 호출한 곳에 던지다
        List<PostResponseDTO> responseDTOList = list.stream()
                .map(PostResponseDTO::new)
                .collect(Collectors.toList());


        PostListResponseDTO listResponseDTO = PostListResponseDTO.builder()
                .count(responseDTOList.size())
                .pageInfo(new PageResponseDTO<PostEntity>(pageData))
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

        // hashtag 를 db에 저장
        List<String> hashTags = createDTO.getHashTags();

        // 해시태그 문자열 리스트에서 문자열들을 하나하나 추출한 뒤
        // 해시태그 엔터티로 만들고 그 엔터티를 데이터베이스에 저장한다.
        for (String ht : hashTags) {
            HashTagEntity tagEntity = HashTagEntity.builder()
                    .tagName(ht)
                    .build();
            hashTagRepository.save(tagEntity);
        }
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
