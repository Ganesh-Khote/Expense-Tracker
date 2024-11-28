import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { ExpenseService } from 'src/app/Services/Ex/expenses/expense.service';

@Component({
  selector: 'app-expense',
  templateUrl: './expense.component.html',
  styleUrls: ['./expense.component.css']
})
export class ExpenseComponent implements OnInit {

  expenseform!:FormGroup;
  expense:any;

  categories:any[]=["Education", "Grocery", "Health","Subscription","Clothing","Travelling","Other"]; // Example categories

  constructor(private fb: FormBuilder,private expenseservice:ExpenseService,private message:NzMessageService,private router:Router) {

  }

  ngOnInit(): void {
    this.getAllExpenses();
    this.expenseform= this.fb.group({
      title: ['', [Validators.required]],
      description: ['', [Validators.required]],
      category: ['', [Validators.required]],
      date: [null, [Validators.required]],
      amount: [null, [Validators.required, Validators.min(0)]],
    });
  }

  onSubmit() {
    if (this.expenseform.valid) {
      this.expenseservice.postExpense(this.expenseform.value).subscribe(
        res => {
          console.log('Server Response:', res);
          this.message.success("Expense posted successfully", { nzDuration: 2000 });
        },
        error => {
          console.error('Error Response:', error);
          this.message.error("Error while processing Expense", { nzDuration: 2000 });
        }
      );
    } else {
      console.log('Form is invalid');
    }
  }


  getAllExpenses(){
    this.expenseservice.getAllExpense().subscribe(res=>{
      this.expense=res;
    },error=>{
      this.message.error("error while Fetching",{nzDuration:2000});
    })
  }

  onEdit(id:number) {
    // Implement your edit logic here
this.router.navigateByUrl(`expense/${id}/edit`);
    // this.message.info('Edit functionality to be implemented', { nzDuration: 2000 });
  }

  onDelete(expense: any) {
    // Implement delete logic and call service to delete the expense
    this.expenseservice.deleteExpense(expense.id).subscribe(
      (res) => {
        this.message.success('Expense deleted successfully', { nzDuration: 2000 });
        this.getAllExpenses(); // Refresh the list after deletion
      },
      (error) => {
        this.message.error('Error while deleting expense', { nzDuration: 2000 });
      }
    );
  }

}
