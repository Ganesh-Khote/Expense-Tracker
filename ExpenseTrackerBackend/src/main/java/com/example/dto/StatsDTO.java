package com.example.dto;

import com.example.entity.Expense;
import com.example.entity.Income;

import lombok.Data;

@Data
public class StatsDTO {

    Double income;
    double expense;

    Income latestIncome;
    Expense latestExpense;

    Double balance;
    Double minIncome;
    Double maxIncome;
    Double minExpense;
    Double maxExpense;
    

}
