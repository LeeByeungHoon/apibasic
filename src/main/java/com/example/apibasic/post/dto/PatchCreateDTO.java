package com.example.apibasic.post.dto;

import com.example.apibasic.post.entity.PostEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter@Getter@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class PatchCreateDTO {

    private String title;
    private String content;

    //PostEntity 로 변환하는 유틸 메서드
    public PostEntity toEntity(PostEntity entity){
        return PostEntity
                .builder()
                .postNo(entity.getPostNo())
                .writer(entity.getWriter())
                .content(this.content)
                .title(this.title)
                .createDate(entity.getCreateDate())
                .modifyDate(LocalDateTime.now())
                .hashTags(entity.getHashTags())
                .build();
    }
}
