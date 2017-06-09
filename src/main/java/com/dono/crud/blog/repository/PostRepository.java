package com.dono.crud.blog.repository;

import com.dono.crud.blog.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface PostRepository extends PagingAndSortingRepository<Post, Integer> {
    Page<Post> findAllByReaderUsername(String string, Pageable pageable);
    List<Post> findTop5ByOrderByCreatedDateDesc();
    List<Post> findTop3ByOrderByCreatedDateDesc();
    Post findTopByOrderByCreatedDateDesc();
}
