import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ExpenseComponent } from './components/expense/expense.component';
import { UpdateExpenseComponent } from './components/expense/update-expense/update-expense.component';
import { IncomeComponent } from './components/income/income/income.component';
import { IncomeupdateComponent } from './components/income/incomeupdate/incomeupdate.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';

const routes: Routes = [
  {path:"",component:DashboardComponent},
  {path:"expense",component:ExpenseComponent},
  {path:"expense/:id/edit",component:UpdateExpenseComponent},
  {path:"income",component:IncomeComponent},
  {path:"income/:id/edit",component:IncomeupdateComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
