package com.twinline.blog.controller;

import com.twinline.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/top-words/{userId}")
    public ResponseEntity<Map<String, Integer>> getTopWords(@PathVariable Integer userId) {
        Map<String, Integer> topWords = blogService.getTopWordsByUser(userId);
        return ResponseEntity.ok(topWords);
    }
}