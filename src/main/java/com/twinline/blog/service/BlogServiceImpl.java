package com.twinline.blog.service;

import com.twinline.blog.exceptions.ResourceNotFoundException;
import com.twinline.blog.dto.BlogDto;
import com.twinline.blog.entity.Blog;
import com.twinline.blog.repository.BlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl implements BlogService{

    @Autowired
    private BlogRepo blogRepo;
    @Override
    public Blog createBlog(BlogDto blogDto) {
        Blog blog = Blog.builder()
                .name(blogDto.getName())
                .body(blogDto.getBody())
                .build();
        return blogRepo.save(blog);
    }

    @Override
    public Blog updateBlog(BlogDto blogDto, Integer blogId) {
        Blog blog = blogRepo.findById(blogId).orElseThrow(() -> new ResourceNotFoundException("Blog","Id",blogId));
        blog.setName(blogDto.getName());
        blog.setBody(blogDto.getBody());
        return blogRepo.save(blog);
    }

    @Override
    public void deleteBlog(Integer blogId) {
        Blog blog = blogRepo.findById(blogId).orElseThrow(() -> new ResourceNotFoundException("Blog","Id",blogId));
        blogRepo.delete(blog);
    }

    @Override
    public List<Blog> getAllBlogs() {
        return blogRepo.findAll();
    }

    private static final Set<String> STOP_WORDS = Set.of(
            "the", "to", "and", "a", "of", "in", "that", "is", "it", "with", "as", "for", "was", "on", "at", "by", "an", "be", "this", "which", "or", "from", "but", "are", "not", "so", "if", "then", "when", "what", "all", "any", "some", "no", "do", "has", "have", "will", "can", "should", "would", "could", "one", "two", "three", "he", "she", "we", "they"
    );

    public Map<String, Integer> getTopWordsByUser(Integer userId) {
        List<String> blogContents = blogRepo.findBodyByUserId(userId);
        Map<String, Integer> wordCountMap = new HashMap<>();

        // Combine and split content
        String allContent = String.join(" ", blogContents).toLowerCase().replaceAll("[^a-zA-Z ]", "");
        String[] words = allContent.split("\\s+");

        // Count word frequencies while excluding stop words and words shorter than 4 characters
        for (String word : words) {
            if (word.isEmpty() || STOP_WORDS.contains(word) || word.length() < 4) continue;
            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
        }

        // Sort and get top 5
        return wordCountMap.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .limit(5)
                .collect(Collectors.toMap(
                        Map.Entry::getKey, // Key mapper
                        Map.Entry::getValue, // Value mapper
                        (e1, e2) -> e1, // Merge function (not needed here but required)
                        LinkedHashMap::new // LinkedHashMap to maintain order
                ));
    }
}
