package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ExpenseDTO;
import com.example.dto.IncomeDTO;
import com.example.entity.Expense;
import com.example.entity.Income;
import com.example.service.income.IncomeService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/income")
@CrossOrigin("*")
@RequiredArgsConstructor
public class IncomeController {

    @Autowired
    IncomeService incomeService;

      @PostMapping("")
    public ResponseEntity<?> postIncome(@RequestBody IncomeDTO incomeDTO){
     Income createdIncome= this.incomeService.postIncome(incomeDTO);

      if(createdIncome!=null){
        return ResponseEntity.status(HttpStatus.CREATED).body("CreatedIncome");
      }else{
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
      }

    }


    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
      return ResponseEntity.ok(this.incomeService.getAllIncome());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getincomeByid(@PathVariable("id") Long id){
      try{
        return ResponseEntity.ok(this.incomeService.getIncomeByid(id));
      }catch(EntityNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
      }catch(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
      }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateExpenseByid(@PathVariable("id") Long id,IncomeDTO incomeDTO){
      try{

        return ResponseEntity.ok(this.incomeService.updatIncomeByid(id, incomeDTO));
      }catch(Exception e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());  
      }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExpenseByid(@PathVariable("id") Long id){
      try{
        this.incomeService.deletebyid(id);
        return ResponseEntity.ok().body(null);
        
      }catch(Exception e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());  
      }
    }

}
