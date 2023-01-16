package com.example.apibasic.jpabasic.repository;

import com.example.apibasic.jpabasic.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

// JPA로 CRUD Operation을 하려면 JpaRepository 인터페이스를 상속
// 제너릭타입으로는 첫번째로 CRUD 할 엔터티클래스의 타입, 두번째로는 해당 엔터티의 ID(PK)의 타입
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {


}
