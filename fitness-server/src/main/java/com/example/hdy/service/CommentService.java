package com.example.hdy.service;

import com.example.hdy.domain.Comment;

import java.util.List;

public interface CommentService {

    boolean addComment(Comment comment);

    boolean updateCommentMsg(Comment comment);

    boolean deleteComment(int id);

    List<Comment> allComment();

    List<Comment> commentOfVideoID(int videoID);

}
