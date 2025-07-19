//package com.blogs.service.impl;
//
//
//import com.blogs.dto.CommentDTO;
//import com.blogs.entity.Comment;
//import com.blogs.repository.CommentRepository;
//import com.blogs.service.interf.CommentService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import java.util.List;
//
//@Service
//public class CommentServiceImpl  implements  CommentService{
//
//    @Autowired
//    private CommentRepository commentRepository;
//
//    public List<Comment> getCommentsByPostId(Long postId) {
//        return commentRepository.findByBlogpostId(postId);
//    }
//
//    public Comment addComment(Comment comment) {
//        return commentRepository.save(comment);
//    }
//
//    public List<Comment> getReplies(Long commentId) {
//        return commentRepository.findByReplyToId(commentId);
//    }
//}







//package com.blogs.service.impl;
//
//import com.blogs.dto.CommentDtoReq;
//import com.blogs.dto.CommentDtoRes;
//import com.blogs.entity.Comment;
//import com.blogs.entity.User;
//import com.blogs.entity.BlogPost;
//import com.blogs.repository.CommentRepository;
//import com.blogs.repository.UserRepo;
//import com.blogs.repository.BlogPostRepo;
//import com.blogs.service.interf.CommentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class CommentServiceImpl implements CommentService {
//
//    @Autowired
//    private CommentRepository commentRepository;
//
//    @Autowired
//    private UserRepo userRepository;
//
//    @Autowired
//    private BlogPostRepo blogPostRepository;
//
//    @Override
//    public List<CommentDtoRes> getCommentsByPostId(Long postId) {
//        return commentRepository.findByBlogpostId(postId)
//                .stream()
//                .map(this::convertToDtoRes)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public CommentDtoRes addComment(CommentDtoReq commentDtoReq) {
//        // Fetch the blog post and commenter from the database
//        BlogPost blogPost = blogPostRepository.findById(commentDtoReq.getBlogPostId())
//                .orElseThrow(() -> new RuntimeException("Blog post not found"));
//        User commenter = userRepository.findById(commentDtoReq.getCommenterId())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        // Create a new Comment entity
//        Comment comment = new Comment();
//        comment.setComment(commentDtoReq.getComment());
//        comment.setBlogpost(blogPost);
//        comment.setCommenter(commenter);
//
//        // Set replyTo if it exists
//        if (commentDtoReq.getReplyToId() != null) {
//            Comment replyTo = commentRepository.findById(commentDtoReq.getReplyToId())
//                    .orElseThrow(() -> new RuntimeException("Reply-to comment not found"));
//            comment.setReplyTo(replyTo);
//        }
//
//        // Save the comment
//        Comment savedComment = commentRepository.save(comment);
//
//        // Convert the saved comment to DTO and return
//        return convertToDtoRes(savedComment);
//    }
//
//    @Override
//    public List<CommentDtoRes> getReplies(Long commentId) {
//        return commentRepository.findByReplyToId(commentId)
//                .stream()
//                .map(this::convertToDtoRes)
//                .collect(Collectors.toList());
//    }
//
//    private CommentDtoRes convertToDtoRes(Comment comment) {
//        CommentDtoRes dtoRes = new CommentDtoRes();
////        dtoRes.setId(comment.getId());
//        dtoRes.setComment(comment.getComment());
////        dtoRes.setCreatedOn(comment.getCreatedOn());
////        dtoRes.setUpdatedOn(comment.getUpdatedOn());
//        dtoRes.setBlogPostId(comment.getBlogpost().getId());
//        dtoRes.setCommenterId(comment.getCommenter().getId());
//        dtoRes.setCommenterName(comment.getCommenter().getName());
//        dtoRes.setReplyToId(comment.getReplyTo() != null ? comment.getReplyTo().getId() : null);
//        return dtoRes;
//    }
//}





package com.blogs.service.impl;

import com.blogs.dto.CommentDtoReq;
import com.blogs.dto.CommentDtoRes;
import com.blogs.entity.BlogPost;
import com.blogs.entity.Comment;
import com.blogs.entity.User;
import com.blogs.repository.BlogPostRepo;
import com.blogs.repository.CommentRepository;
import com.blogs.repository.UserRepo;
import com.blogs.service.interf.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private BlogPostRepo blogPostRepository;

    @Override
    public List<CommentDtoRes> getCommentsByPostId(Long postId) {
        return commentRepository.findByBlogpostId(postId)
                .stream()
                .map(this::convertToDtoRes)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDtoRes addComment(CommentDtoReq commentDtoReq) {
        BlogPost blogPost = blogPostRepository.findById(commentDtoReq.getBlogPostId())
                .orElseThrow(() -> new RuntimeException("Blog post not found"));
        User commenter = userRepository.findById(commentDtoReq.getCommenterId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Comment comment = new Comment();
        comment.setComment(commentDtoReq.getComment());
        comment.setBlogpost(blogPost);
        comment.setCommenter(commenter);

        if (commentDtoReq.getReplyToId() != null) {
            Comment replyTo = commentRepository.findById(commentDtoReq.getReplyToId())
                    .orElseThrow(() -> new RuntimeException("Reply-to comment not found"));
            comment.setReplyTo(replyTo);
        }

        Comment savedComment = commentRepository.save(comment);
        return convertToDtoRes(savedComment);
    }

    @Override
    public List<CommentDtoRes> getReplies(Long commentId) {
        return commentRepository.findByReplyToId(commentId)
                .stream()
                .map(this::convertToDtoRes)
                .collect(Collectors.toList());
    }

    private CommentDtoRes convertToDtoRes(Comment comment) {
        CommentDtoRes dtoRes = new CommentDtoRes();
        dtoRes.setComment(comment.getComment());
        dtoRes.setBlogPostId(comment.getBlogpost().getId());
        dtoRes.setCommenterId(comment.getCommenter().getId());
        dtoRes.setCommenterName(comment.getCommenter().getName());
        dtoRes.setReplyToId(comment.getReplyTo() != null ? comment.getReplyTo().getId() : null);
        return dtoRes;
    }
}