package com.example.hdy.service.impl;

import com.example.hdy.dao.CommentMapper;
import com.example.hdy.service.CommentService;
import com.example.hdy.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public boolean addComment(Comment comment) {
        return commentMapper.insertSelective(comment) > 0 ? true:false;
    }

    @Override
    public boolean updateCommentMsg(Comment comment) {
        return commentMapper.updateCommentMsg(comment) >0 ?true:false;
    }

//    删除评论
    @Override
    public boolean deleteComment(int id) {
        return commentMapper.deleteComment(id) >0 ?true:false;
    }

    @Override
    public List<Comment> allComment()
    {
        return commentMapper.allComment();
    }

    @Override
    public List<Comment> commentOfVideoID(int videoID)

    {
        return commentMapper.commentOfVideoID(videoID);
    }


}
