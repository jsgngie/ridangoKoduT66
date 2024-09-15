import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private backendUrl = 'http://localhost:8080/api'
  constructor(
    private http: HttpClient
  ) { }

  getHello(): Observable<string> {
    return this.http.get<string>(this.backendUrl+'/hello', { responseType: 'text' as 'json' })
  }
}
