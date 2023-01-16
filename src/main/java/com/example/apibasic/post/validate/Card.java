package com.example.apibasic.post.validate;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.YearMonth;

@Setter@Getter@ToString
public class Card {
    @NotBlank
    private String cardNo;

    @JsonFormat(pattern = "yyyy-MM")
    @Future
    private YearMonth validMonth;
}
