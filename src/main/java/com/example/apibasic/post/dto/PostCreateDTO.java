package com.example.apibasic.post.dto;

import com.example.apibasic.post.entity.PostEntity;
import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
//ctrl + alt + O 불필요 요소 제거

@Setter@Getter@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class PostCreateDTO {

    /*
        NotNull : null 값일 경우 에러 발생
        NotEmpty : 빈 문자열일 경우 에러발생
        NotBlank : null 이거나 빈 문자열일 경우 에러발생
     */
    @NotBlank
    @Size(min = 1, max = 20) // 글자수는 2~5자 사이
    private String writer;
    @NotBlank
    @Size(min = 1, max = 20) // 1~20자 사이
    private String title;
    private String content;
    //PostEntity 로 변환하는 유틸 메서드
    public PostEntity toEntity(){
        return PostEntity
                .builder()
                .writer(this.writer)
                .content(this.content)
                .title(this.title)
                .build();
    }
}
