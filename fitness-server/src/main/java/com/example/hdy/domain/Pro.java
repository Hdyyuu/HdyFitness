package com.example.hdy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Negative;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pro {
    private int id;

    private String name;

    private String pic;

    private String introduction;
}
