package com.example.dto;

import java.util.List;

import com.example.entity.Expense;
import com.example.entity.Income;

import lombok.Data;

@Data
public class GraphDTO {

   List<Expense> expenseList;
   List<Income> incomeList;

}
