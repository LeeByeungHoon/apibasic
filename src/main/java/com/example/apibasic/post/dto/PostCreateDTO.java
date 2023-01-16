package com.example.apibasic.post.dto;

import com.example.apibasic.post.entity.PostEntity;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
    @Size(min = 2, max = 5) // 글자수는 2~5자 사이
    private String writer;
    @NotBlank
    @Size(min = 2, max = 5) // 1~20자 사이
    private String title;
    private String content;
    private List<String> hashTags;
    //PostEntity 로 변환하는 유틸 메서드
    public PostEntity toEntity(){
        return PostEntity
                .builder()
                .postNo(PostEntity.sequence++)
                .writer(this.writer)
                .content(this.content)
                .title(this.title)
                .createDate(LocalDateTime.now())
                .hashTags(this.hashTags)
                .build();
    }
}
