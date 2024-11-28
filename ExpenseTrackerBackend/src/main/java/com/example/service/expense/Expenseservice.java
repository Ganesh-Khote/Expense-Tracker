package com.example.service.expense;

import java.util.List;

//import org.springframework.stereotype.Service;

import com.example.dto.ExpenseDTO;
import com.example.entity.Expense;


public interface Expenseservice {
    public Expense postExpense(ExpenseDTO expenseDTO);
    public List<Expense> getAllExpenses();
    public Expense getExpenseByid(Long id);
    public Expense updatExpenseByid(Long id,ExpenseDTO expenseDTO);
    public void deletebyid(Long id);

}
