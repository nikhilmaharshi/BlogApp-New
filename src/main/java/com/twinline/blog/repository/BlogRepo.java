package com.twinline.blog.repository;

import com.twinline.blog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogRepo extends JpaRepository<Blog,Integer> {
    @Query("SELECT body FROM Blog WHERE user_id = :userId")
    List<String> findBodyByUserId(Integer userId);
}
