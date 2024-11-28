package com.example.service.expense;

// import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.ExpenseDTO;
import com.example.entity.Expense;
import com.example.repository.ExpenseRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpenseServiceimpl implements Expenseservice {

     @Autowired
     ExpenseRepository expenseRepository;
    private int Comparator;

    public Expense postExpense(ExpenseDTO expenseDTO){
        return saveorupdateexpense(new Expense(), expenseDTO);
    }

    public Expense saveorupdateexpense(Expense expense,ExpenseDTO expenseDTO){
         expense.setTitle(expenseDTO.getTitle());
        expense.setDate(expenseDTO.getDate());
        expense.setAmount(expenseDTO.getAmount());
        expense.setCategory(expenseDTO.getCategory());
        expense.setDescription(expenseDTO.getDescription());
        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses(){
        return expenseRepository.findAll().stream()
        .sorted(java.util.Comparator.comparing(Expense::getDate).reversed())
        .collect(Collectors.toList());
        
    }

    public Expense getExpenseByid(Long id){
        Optional<Expense> optionalexpense=this.expenseRepository.findById(id);
        if(optionalexpense.isPresent()){
            return optionalexpense.get();
        }else{
            throw new EntityNotFoundException("Expense is Not found By this id:"+id);
        }
    }

    public Expense updatExpenseByid(Long id,ExpenseDTO expenseDTO){
        Optional<Expense> optionalexpense=this.expenseRepository.findById(id);
        if(optionalexpense.isPresent()){
            return saveorupdateexpense(optionalexpense.get(), expenseDTO);
        }else{
            throw new EntityNotFoundException("Expense is not present int his id:"+id);
        }
    }

    public void deletebyid(Long id){
        Optional<Expense> optionalexpense=this.expenseRepository.findById(id);
        if(optionalexpense.isPresent()){
            this.expenseRepository.deleteById(id);
        }
        else{
            throw new EntityNotFoundException("Expense is not present int his id:"+id);
        }
    }

}
