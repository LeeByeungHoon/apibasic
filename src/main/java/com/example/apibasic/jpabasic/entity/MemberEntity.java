package com.example.apibasic.jpabasic.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter@Setter@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "userId")
@Builder
// JPA
@Entity // JPA 의 Entity 객체
@Table(name = "tbl_member")
public class MemberEntity {

    @Id // 기본키 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 생성 전략
    @Column(name = "user_code")
    private Long userId; // 회원식별 코드(기본키)
    @Column(nullable = false, unique = true, length = 30) // NOT NULL 제약조건, UNIQUE 제약조건, Length 크기
    private String account; // 계정명
    @NotNull
    private String password; // 패스워드
    @Column(name = "user_nick", nullable = false)
    private String nickname; // 닉네임

    @Enumerated(EnumType.STRING)// ORDINAL: 순번(0부터 시작), STRING: 순수 문자열
    @Column(length = 10)
    private Gender gender; // 성별

    @CreationTimestamp // INSERT 시점에 서버시간을 자동으로 입력
    private LocalDateTime joinDate; // 가입일자와 시간
    @UpdateTimestamp // UPDATE 시점에 서버시간을 자동으로 입력
    private LocalDateTime modifyDate; // 정보 수정 시간
}
