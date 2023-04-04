package com.example.hdy.dao;

import com.example.hdy.domain.Rank;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RankMapper {

    int insert(Rank record);
    int insertSelective(Rank record);
    int selectScoreSum(int videoID);
    int selectRankNum(int videoID);
    int selectOneScore(@Param("userID") Integer userID, @Param("videoID") Integer videoID);
    int isRanked(@Param("userID") Integer userID, @Param("videoID") Integer videoID);
    int deleteRank(@Param("userID") Integer userID, @Param("videoID") Integer videoID);
}
