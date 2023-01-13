package com.example.apibasic.post.entity;

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

    private Long postNo; // 게시물 식별 번호
    private String writer; // 게시물 작성자 이름
    private String title; // 게시물 제목
    private String content; // 게시물 내용
    private List<String> hashTags; // 해시태그 목록
    private LocalDateTime createDate; // 작성 시간
    private LocalDateTime modifyDate; // 수정 시간

}
