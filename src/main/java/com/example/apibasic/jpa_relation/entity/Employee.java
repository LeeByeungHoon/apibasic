package com.example.apibasic.jpa_relation.entity;

import lombok.*;

import javax.persistence.*;

@Setter@Getter@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "empId")
@Builder
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId; // 사원번호

    private String empName; // 사원이름

    /*
        EAGER : 항상 JOIN 을 하도록 설정
        LAZY : 부서정보가 필요할때만 JOIN (실무에서는 ManyToOne 시 무조건 LAZY)
     */
    @ManyToOne(fetch = FetchType.LAZY) // 연관관계 매핑
    @JoinColumn(name = "dept_id") // FK 컬럼 이름 지정
    private Department department; // 부서 코드

}
