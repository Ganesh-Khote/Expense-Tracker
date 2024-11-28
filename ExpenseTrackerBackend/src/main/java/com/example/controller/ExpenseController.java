package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
import com.example.entity.Expense;
import com.example.service.expense.Expenseservice;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/expense")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ExpenseController {

    @Autowired
    private final Expenseservice expenseservice;

    @PostMapping("")
    public ResponseEntity<?> postExpense(@RequestBody ExpenseDTO expenseDTO) {
        Expense createdExpense = this.expenseservice.postExpense(expenseDTO);
    
        if (createdExpense != null) {
            // Return a JSON response with status 201 Created
            return ResponseEntity.status(HttpStatus.CREATED).body(createdExpense);  // Return the created expense object or any other meaningful JSON object
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Expense creation failed");
        }
    }
    
@GetMapping("/all")
    public ResponseEntity<?> getAll(){
      return ResponseEntity.ok(this.expenseservice.getAllExpenses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getexpenseByid(@PathVariable("id") Long id){
      try{
        return ResponseEntity.ok(this.expenseservice.getExpenseByid(id));
      }catch(EntityNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
      }catch(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
      }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateExpenseByid(@PathVariable("id") Long id,ExpenseDTO expenseDTO){
      try{

        return ResponseEntity.ok(this.expenseservice.updatExpenseByid(id, expenseDTO));
      }catch(Exception e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());  
      }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExpenseByid(@PathVariable("id") Long id){
      try{
        this.expenseservice.deletebyid(id);
        return ResponseEntity.ok().body(null);
        
      }catch(Exception e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());  
      }
    }

}
