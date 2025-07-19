package com.blogs.controller;

import com.blogs.dto.CommentDtoReq;
import com.blogs.dto.CommentDtoRes;
import com.blogs.service.interf.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/{postId}")
    public List<CommentDtoRes> getCommentsByPostId(@PathVariable Long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    @PostMapping
    public CommentDtoRes addComment(@RequestBody CommentDtoReq commentDtoReq) {
        return commentService.addComment(commentDtoReq);
    }

    @GetMapping("/replies/{commentId}")
    public List<CommentDtoRes> getReplies(@PathVariable Long commentId) {
        return commentService.getReplies(commentId);
    }
}