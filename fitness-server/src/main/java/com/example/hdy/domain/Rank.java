package com.example.hdy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rank implements Serializable {

    private int id;

    private int userID;

    private int videoID;

    private Integer score;

}
