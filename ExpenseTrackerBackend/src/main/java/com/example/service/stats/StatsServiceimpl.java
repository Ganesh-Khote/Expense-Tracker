package com.example.service.stats;

import java.time.LocalDate;
import java.util.Optional;
import java.util.OptionalDouble;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.GraphDTO;
import com.example.dto.StatsDTO;
import com.example.entity.Expense;
import com.example.entity.Income;
import com.example.repository.ExpenseRepository;
import com.example.repository.IncomeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatsServiceimpl implements StatsService{

    @Autowired
    IncomeRepository incomeRepository;
    @Autowired
    ExpenseRepository expenseRepository;

    public GraphDTO getChartData(){
        LocalDate endDate=LocalDate.now();
        LocalDate startDate=endDate.minusDays(60);
        GraphDTO graphDTO=new GraphDTO();
        // graphDTO.setExpenseList(expenseRepository.findByDateBetween(startDate, endDate));
        // graphDTO.setIncomeList(incomeRepository.findByDateBetween(startDate, endDate));
        graphDTO.setExpenseList(expenseRepository.findByDateBetween(startDate, endDate));
        graphDTO.setIncomeList(incomeRepository.findByDateBetween(startDate, endDate));
        return graphDTO;

    }

    public StatsDTO getStats(){
        Double totalIncome=incomeRepository.sumAllAmount();
        Double totalExpense=expenseRepository.sumAllAmount();

        Optional<Income> optionalIncome=incomeRepository.findFirstByOrderByDateDesc();
        Optional<Expense> optionalExpense=expenseRepository.findFirstByOrderByDateDesc();

        StatsDTO statsDTO=new StatsDTO();
        statsDTO.setIncome(totalIncome);
        statsDTO.setExpense(totalExpense);

        optionalIncome.ifPresent(statsDTO::setLatestIncome);
        optionalExpense.ifPresent(statsDTO::setLatestExpense);

        statsDTO.setBalance(totalIncome-totalExpense);

        java.util.List<Income> incomeList=incomeRepository.findAll();
        java.util.List<Expense> expenseList=expenseRepository.findAll();

        OptionalDouble minIncome=incomeList.stream().mapToDouble(Income::getAmount).min();
        OptionalDouble maxIncome=incomeList.stream().mapToDouble(Income::getAmount).max();


        OptionalDouble minExpense=expenseList.stream().mapToDouble(Expense::getAmount).min();
        OptionalDouble maxExpense=expenseList.stream().mapToDouble(Expense::getAmount).max();

        statsDTO.setMaxExpense(maxExpense.isPresent() ? maxExpense.getAsDouble():null);
        statsDTO.setMinExpense(minExpense.isPresent() ? minExpense.getAsDouble():null);

        statsDTO.setMinIncome(minIncome.isPresent() ? minIncome.getAsDouble():null);
        statsDTO.setMaxIncome(maxIncome.isPresent() ? maxIncome.getAsDouble():null);
        

        return statsDTO;
    }

}
