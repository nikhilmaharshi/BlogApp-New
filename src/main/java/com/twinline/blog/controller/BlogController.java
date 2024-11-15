package com.twinline.blog.controller;

import com.twinline.blog.dto.BlogDto;
import com.twinline.blog.entity.Blog;
import com.twinline.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/all")
    public ResponseEntity<List<Blog>> getAllBlogs() {
        List<Blog> blogs = blogService.getAllBlogs();
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Blog> createBlog(@RequestBody BlogDto blogDto) {
        Blog blog = blogService.createBlog(blogDto);
        return new ResponseEntity<>(blog, HttpStatus.CREATED);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Blog> updateBlog(@RequestBody BlogDto updatedBlog,@PathVariable Integer id) {
        Blog blog = blogService.updateBlog(updatedBlog, id);
        return new ResponseEntity<>(blog, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable Integer id) {
        blogService.deleteBlog(id);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
