import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ExpenseService {
 BASIC_URL="http://localhost:9090/";

  constructor(private http:HttpClient) { }

  postExpense(expenseDTO:any):Observable<any>{
    return this.http.post(this.BASIC_URL+"api/expense",expenseDTO);
  }

  getAllExpense():Observable<any>{
    return this.http.get(this.BASIC_URL+"api/expense/all");
  }


  deleteExpense(id: number): Observable<any> {
    return this.http.delete(this.BASIC_URL + `api/expense/${id}` );
  }

  getExpenseById(id:number):Observable<any>{
    return this.http.get(this.BASIC_URL+`api/expense/${id}`);
  }


  updateExpenseById(id:number,expenseDTO:any):Observable<any>{
    return this.http.put(this.BASIC_URL+`api/expense/${id}`,expenseDTO);
  }
}
