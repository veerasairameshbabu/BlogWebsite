//package com.blogs.dto;

//import lombok.Data;
//
////import java.time.LocalDateTime;
//
//@Data
//public class CommentDtoRes {
////    private Long id; // Comment ID
//    private String comment; // Comment text
////    private LocalDateTime createdOn; // Timestamp when the comment was created
////    private LocalDateTime updatedOn; // Timestamp when the comment was last updated
//    private Long blogPostId; // ID of the blog post the comment belongs to
//    private Long commenterId; // ID of the user who commented
//    private String commenterName; // Username of the commenter
//    private Long replyToId; // ID of the comment this is replying to (nullable)
//}




package com.blogs.dto;

import lombok.Data;

@Data
public class CommentDtoRes {
    private String comment;
    private Long blogPostId;
    private Long commenterId;
    private String commenterName;
    private Long replyToId;
}