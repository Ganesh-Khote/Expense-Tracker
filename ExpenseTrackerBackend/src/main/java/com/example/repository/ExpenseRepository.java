package com.example.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Expense;
// import com.example.entity.Income;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {
    
    List<Expense> findByDateBetween(@Param("startDate")LocalDate startDate,@Param("endDate")LocalDate endDate);

       @Query("SELECT SUM(e.amount) FROM Expense e")
    Double sumAllAmount();

    Optional<Expense> findFirstByOrderByDateDesc();
}
