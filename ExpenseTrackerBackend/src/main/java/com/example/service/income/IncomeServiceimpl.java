package com.example.service.income;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import com.example.dto.ExpenseDTO;
import com.example.dto.IncomeDTO;
// import com.example.entity.Expense;
import com.example.entity.Income;
// import com.example.repository.ExpenseRepository;
import com.example.repository.IncomeRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IncomeServiceimpl implements IncomeService {

     @Autowired
     IncomeRepository incomeRepository;
    // private int Comparator;

    public Income postIncome(IncomeDTO incomeDTO){
        return saveorupdateincome(new Income(), incomeDTO);
    }

    public Income saveorupdateincome(Income income,IncomeDTO incomeDTO){
         income.setTitle(incomeDTO.getTitle());
        income.setDate(incomeDTO.getDate());
        income.setAmount(incomeDTO.getAmount());
        income.setCategory(incomeDTO.getCategory());
        income.setDescription(incomeDTO.getDescription());
        return incomeRepository.save(income);
    }
    
    
    public List<Income> getAllIncome(){
        return incomeRepository.findAll().stream()
        .sorted(java.util.Comparator.comparing(Income::getDate).reversed())
        // .map(Income::getIncomeDTO)
        .collect(Collectors.toList());
        
    }

    public Income getIncomeByid(Long id){
        Optional<Income> optionalincome=this.incomeRepository.findById(id);
        if(optionalincome.isPresent()){
            return optionalincome.get();
        }else{
            throw new EntityNotFoundException("Income is Not found By this id:"+id);
        }
    }

    public Income updatIncomeByid(Long id,IncomeDTO incomeDTO){
        Optional<Income> optionalincome=this.incomeRepository.findById(id);
        if(optionalincome.isPresent()){
            return saveorupdateincome(optionalincome.get(), incomeDTO);
        }else{
            throw new EntityNotFoundException("Income is not present int his id:"+id);
        }
    }

    public void deletebyid(Long id){
        Optional<Income> optionalincome=this.incomeRepository.findById(id);
        if(optionalincome.isPresent()){
            this.incomeRepository.deleteById(id);
        }
        else{
            throw new EntityNotFoundException("Income is not present int his id:"+id);
        }
    }

}
