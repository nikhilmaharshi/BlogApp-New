package com.twinline.blog.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "blogs")  // Specify the table name in the database.
@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String body;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
