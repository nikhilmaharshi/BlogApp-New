package com.twinline.blog.service;

import com.twinline.blog.dto.BlogDto;
import com.twinline.blog.entity.Blog;

import java.util.List;
import java.util.Map;

public interface BlogService {

    Blog createBlog(BlogDto blogDto);
    Blog updateBlog(BlogDto blogDto, Integer blogId);
    void deleteBlog(Integer blogId);

    List<Blog> getAllBlogs();

    Map<String, Integer> getTopWordsByUser(Integer userId);
}
