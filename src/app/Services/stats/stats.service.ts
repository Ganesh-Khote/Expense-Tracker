import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StatsService {

  BASIC_URL="http://localhost:9090/"
  constructor(private http:HttpClient) { }

  getstats():Observable<any>{
    return this.http.get(this.BASIC_URL+"api/stats");
  }

  getcharts():Observable<any>{
    return this.http.get(this.BASIC_URL+"api/stats/chart");
  }
}
