package com.example.apibasic.post.dto;

import com.example.apibasic.post.entity.PostEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class PostResponseDTO {

    private Long postNo;
    private String author; // writer
    private String title;
    private String content;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime regDate;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime modifyDate;

    // 엔터티를 DTO로 변환하는 생성자
    public PostResponseDTO(PostEntity entity){
            this.postNo = entity.getPostNo();
            this.author = entity.getWriter();
            this.content = entity.getContent();
            this.title = entity.getTitle();
            this.regDate = entity.getCreateDate();
            this.modifyDate = entity.getModifyDate();
    }
}
