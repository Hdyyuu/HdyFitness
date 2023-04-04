package com.example.hdy.dao;

import com.example.hdy.domain.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper {
    int deleteByPrimaryKey(int id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(int id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    int updateCommentMsg(Comment record);

    int deleteComment(int id);

    List<Comment> allComment();

    List<Comment> commentOfVideoID(int videoID);


}
