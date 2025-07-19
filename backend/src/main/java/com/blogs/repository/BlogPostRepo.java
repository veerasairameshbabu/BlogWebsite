package com.blogs.repository;

import com.blogs.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogPostRepo extends JpaRepository<BlogPost, Long> {
    List<BlogPost> findByBlogger_Id(Long bloggerId);
    List<BlogPost> findByStatus(boolean status);
}


