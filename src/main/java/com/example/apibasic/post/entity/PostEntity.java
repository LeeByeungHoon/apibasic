package com.example.apibasic.post.entity;

import com.example.apibasic.post.dto.PostResponseDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

// 게시물의 데이터 자바빈즈
@Setter@Getter@ToString
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Builder
public class PostEntity {

    public static Long sequence = 1L; // 연속된 일련번호
    private Long postNo; // 게시물 식별 번호
    private String writer; // 게시물 작성자 이름
    private String title; // 게시물 제목
    private String content; // 게시물 내용
    private List<String> hashTags; // 해시태그 목록
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate; // 작성 시간
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifyDate; // 수정 시간

}
