//package com.blogs.service.interf;
//
//import java.util.List;
//
//import com.blogs.dto.CommentDTO;
//import com.blogs.entity.Comment;
//
//public interface CommentService {
//	List<Comment> getCommentsByPostId(Long postId);
//	Comment addComment(Comment comment);
//	List<Comment> getReplies(Long commentId);
//
//}


package com.blogs.service.interf;

import com.blogs.dto.CommentDtoReq;
import com.blogs.dto.CommentDtoRes;
import java.util.List;

public interface CommentService {
    List<CommentDtoRes> getCommentsByPostId(Long postId);
    CommentDtoRes addComment(CommentDtoReq commentDtoReq);
    List<CommentDtoRes> getReplies(Long commentId);
}