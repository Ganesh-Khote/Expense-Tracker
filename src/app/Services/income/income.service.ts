import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class IncomeService {

  // constructor() { }

  BASIC_URL="http://localhost:9090/";

  constructor(private http:HttpClient) { }

  postIncome(incomeDTO:any):Observable<any>{
    return this.http.post(this.BASIC_URL+"api/income",incomeDTO);
  }

  getAllIncome():Observable<any>{
    return this.http.get(this.BASIC_URL+"api/income/all");
  }


  deleteIncome(id: number): Observable<any> {
    return this.http.delete(this.BASIC_URL + `api/income/${id}` );
  }

  getIncomeById(id:number):Observable<any>{
    return this.http.get(this.BASIC_URL+`api/income/${id}`);
  }


  updateIncomeById(id:number,incomeDTO:any):Observable<any>{
    return this.http.put(this.BASIC_URL+`api/income/${id}`,incomeDTO);
  }
}
