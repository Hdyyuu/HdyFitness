package com.example.hdy.dao;

import com.example.hdy.domain.Like;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeMapper {
    int addLike(Like like);
    int existVideoID(@Param("userID") Integer userID, @Param("videoID") Integer videoID);
    int countLike(Integer videoID);
    int deleteLike(@Param("userID") Integer userID, @Param("videoID") Integer videoID);
}
