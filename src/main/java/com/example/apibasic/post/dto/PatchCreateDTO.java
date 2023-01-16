package com.example.apibasic.post.dto;

import com.example.apibasic.post.entity.PostEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Setter@Getter@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class PatchCreateDTO {

    private String title;
    private String content;

    //PostEntity 로 변환하는 유틸 메서드
    public PostEntity toEntity(Optional<PostEntity> entity){
        return PostEntity
                .builder()
                .postNo(entity.get().getPostNo())
                .writer(entity.get().getWriter())
                .content(this.content)
                .title(this.title)
                .createDate(entity.get().getCreateDate())
                .modifyDate(LocalDateTime.now())
                .build();
    }
}
