package com.twinline.blog.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogDto {
    private String name;
    private String body;
}
