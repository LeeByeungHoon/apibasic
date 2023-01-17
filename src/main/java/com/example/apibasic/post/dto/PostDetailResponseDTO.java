package com.example.apibasic.post.dto;

import com.example.apibasic.post.entity.PostEntity;
import lombok.*;

import java.time.LocalDateTime;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PostDetailResponseDTO extends PostResponseDTO {

    private LocalDateTime modifyDate;

    public PostDetailResponseDTO(PostEntity entity) {
        super(entity);
        this.modifyDate = entity.getModifyDate();
    }
}