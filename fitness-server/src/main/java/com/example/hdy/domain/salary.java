package com.example.hdy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class salary {
    private int id;

    private String name;

    private String department;

    private String position;

    private int  days;

    private int basePay;

    private int bonus;

    private int month;
}
