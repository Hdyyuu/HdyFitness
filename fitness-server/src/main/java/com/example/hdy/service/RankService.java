package com.example.hdy.service;

import com.example.hdy.domain.Rank;
import org.apache.ibatis.annotations.Param;

public interface RankService {

    int rankOfVideoId(int videoID);

    boolean addRank(Rank rank);

    boolean IsRanked(Integer userID, Integer videoID);

    int selectOneScore(Integer userID, Integer videoID);

    boolean deleteRank(Integer userID, Integer videoID);
}
