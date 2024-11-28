import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { ExpenseService } from 'src/app/Services/Ex/expenses/expense.service';

@Component({
  selector: 'app-update-expense',
  templateUrl: './update-expense.component.html',
  styleUrls: ['./update-expense.component.css']
})
export class UpdateExpenseComponent {

  expenseform!:FormGroup;
  expense:any;
id:number=this.acivatedroute.snapshot.params['id'];
  categories:any[]=["Education", "Grocery", "Health","Subscription","Clothing","Travelling","Other"]; // Example categories

  constructor(private fb: FormBuilder,
    private expenseservice:ExpenseService,
    private message:NzMessageService,
    private router:Router,
    private acivatedroute:ActivatedRoute

  ) {

  }

  ngOnInit(): void {
    this.getexpensebyId();
    this.expenseform= this.fb.group({
      title: ['', [Validators.required]],
      description: ['', [Validators.required]],
      category: ['', [Validators.required]],
      date: [null, [Validators.required]],
      amount: [null, [Validators.required, Validators.min(0)]],
      // this.getexpensebyId();
    });
  }

  onSubmit(){
    this.expenseservice.updateExpenseById(this.id,this.expenseform.value).subscribe(res=>{
      this.message.success("Updated Successfully",{nzDuration:2000});
      this.router.navigateByUrl("/expense");
    },error=>{
      this.message.error("Error while updating",{nzDuration:2000});
    })
  }

  getexpensebyId(){
    this.expenseservice.getExpenseById(this.id).subscribe(res=>{
this.expenseform.patchValue(res);
    },
  error=>{
    this.message.error("error while Fetching ",{nzDuration:2000});
  })
  }

}
