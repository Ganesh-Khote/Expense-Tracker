package com.example.service.income;

import java.util.List;

// import com.example.dto.ExpenseDTO;
import com.example.dto.IncomeDTO;
// import com.example.entity.Expense;
import com.example.entity.Income;

public interface IncomeService {
 public Income postIncome(IncomeDTO incomeDTO);
    public List<Income> getAllIncome();
    public Income getIncomeByid(Long id);
    public Income updatIncomeByid(Long id,IncomeDTO incomeDTO);
    public void deletebyid(Long id);
}
