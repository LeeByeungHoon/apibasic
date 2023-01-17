package com.example.apibasic.post.repository;

import com.example.apibasic.post.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PostRepository extends JpaRepository<PostEntity, Long> {


    Page<PostEntity> findByTitleContainning(String title, Pageable pageable);
    // 페이징 조회

}
