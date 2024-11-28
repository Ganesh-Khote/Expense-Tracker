import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { ExpenseService } from 'src/app/Services/Ex/expenses/expense.service';
import { IncomeService } from 'src/app/Services/income/income.service';

@Component({
  selector: 'app-incomeupdate',
  templateUrl: './incomeupdate.component.html',
  styleUrls: ['./incomeupdate.component.css']
})
export class IncomeupdateComponent {

  incomeform!:FormGroup;
  income:any;
id:number=this.acivatedroute.snapshot.params['id'];
  categories:any[]=["Education", "Grocery", "Health","Subscription","Clothing","Travelling","Other"]; // Example categories

  constructor(private fb: FormBuilder,
    private incomeservice:IncomeService,
    private message:NzMessageService,
    private router:Router,
    private acivatedroute:ActivatedRoute

  ) {

  }

  ngOnInit(): void {
    this.getincomebyId();
    this.incomeform= this.fb.group({
      title: ['', [Validators.required]],
      description: ['', [Validators.required]],
      category: ['', [Validators.required]],
      date: [null, [Validators.required]],
      amount: [null, [Validators.required, Validators.min(0)]],
      // this.getexpensebyId();
    });
  }

  onSubmit(){
    this.incomeservice.updateIncomeById(this.id,this.incomeform.value).subscribe(res=>{
      this.message.success("Updated Successfully",{nzDuration:2000});
      this.router.navigateByUrl("/income");
    },error=>{
      this.message.error("Error while updating",{nzDuration:2000});
    })
  }

  getincomebyId(){
    this.incomeservice.getIncomeById(this.id).subscribe(res=>{
this.incomeform.patchValue(res);
    },
  error=>{
    this.message.error("error while Fetching ",{nzDuration:2000});
  })
  }

}
