import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { ExpenseService } from 'src/app/Services/Ex/expenses/expense.service';
import { IncomeService } from 'src/app/Services/income/income.service';

@Component({
  selector: 'app-income',
  templateUrl: './income.component.html',
  styleUrls: ['./income.component.css']
})
export class IncomeComponent implements OnInit{

  incomeform!:FormGroup;
  income:any;

  categories:any[]=["Education", "Grocery", "Health","Subscription","Clothing","Travelling","Other"]; // Example categories

  constructor(private fb: FormBuilder,private incomeservice:IncomeService,private message:NzMessageService,private router:Router) {

  }

  ngOnInit(): void {
    this.getAllincome();
    this.incomeform= this.fb.group({
      title: ['', [Validators.required]],
      description: ['', [Validators.required]],
      category: ['', [Validators.required]],
      date: [null, [Validators.required]],
      amount: [null, [Validators.required, Validators.min(0)]],
    });
  }

  onSubmit() {
    if (this.incomeform.valid) {
      this.incomeservice.postIncome(this.incomeform.value).subscribe(
        res => {
          console.log('Server Response:', res);
          this.message.success("Income posted successfully", { nzDuration: 2000 });
        },
        error => {
          console.error('Error Response:', error);
          this.message.error("Error while processing Income", { nzDuration: 2000 });
        }
      );
    } else {
      console.log('Form is invalid');
    }
  }


  getAllincome(){
    this.incomeservice.getAllIncome().subscribe(
      res=>{
      this.income=res;
    },error=>{
      this.message.error("error while Fetching",{nzDuration:2000});
    })
  }

  onEdit(id:number) {
    // Implement your edit logic here
this.router.navigateByUrl(`income/${id}/edit`);
    // this.message.info('Edit functionality to be implemented', { nzDuration: 2000 });
  }

  onDelete(income: any) {
    // Implement delete logic and call service to delete the expense
    this.incomeservice.deleteIncome(income.id).subscribe(
      (res) => {
        this.message.success('Expense deleted successfully', { nzDuration: 2000 });
        this.getAllincome(); // Refresh the list after deletion
      },
      (error) => {
        this.message.error('Error while deleting income', { nzDuration: 2000 });
      }
    );
  }

}
