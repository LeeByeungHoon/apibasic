package com.example.apibasic.post.entity;

import com.example.apibasic.post.dto.PostResponseDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// 게시물의 데이터 자바빈즈
@Getter@Setter@ToString(exclude = "hashTags")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "postNo")
@Builder
// JPA
@Entity // JPA 의 Entity 객체
@Table(name = "tbl_post")
public class PostEntity {

    @Id // 기본키 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 생성 전략
    private Long postNo; // 게시물 식별 번호 post_no로 저장됨
    @Column(nullable = false, length = 10)
    private String writer; // 게시물 작성자 이름
    @Column(nullable = false, length = 30)
    private String title; // 게시물 제목
    @Column(nullable = false)
    private String content; // 게시물 내용

    @OneToMany(mappedBy = "post")
    private List<HashTagEntity> hashTags = new ArrayList<>();
    @CreationTimestamp
    private LocalDateTime createDate; // 작성 시간
    @UpdateTimestamp
    private LocalDateTime modifyDate; // 수정 시간

}
