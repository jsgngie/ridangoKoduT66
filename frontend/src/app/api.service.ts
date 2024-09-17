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
    
    setupGame(): void {
      
      this.http.get<boolean>(`${this.backendUrl}/setup`).subscribe({
        next: (response: boolean) => {
          if (response) {
            console.log("Setup was a success...")
          } else {
            console.log("Setup failed...")
          }
        },
        error: (error) => {
          console.error('API call error:', error);
        }}
      );
  }

  getHiddenString(): Observable<string> {
    return this.http.get<string>(`${this.backendUrl}/hidden`, {responseType: 'text' as 'json'})
  }
}
