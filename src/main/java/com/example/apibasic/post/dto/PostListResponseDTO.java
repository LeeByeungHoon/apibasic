package com.example.apibasic.post.dto;

import lombok.*;

import java.util.List;

@Getter@Setter@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class PostListResponseDTO {

    private int count;
    private List<PostResponseDTO> posts;

}
