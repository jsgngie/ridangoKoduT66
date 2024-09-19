import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Ingredient } from './ingredient';
import { Highscore} from './highscore'

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
          console.log("Setup was a success...");
        } else {
          console.log("Setup failed...");
        }
      },
      error: (error) => {
        console.error('Setup error:', error);
        console.log('If you have started backend try reloading the page.');
      }}
    );
  }

  getHiddenString(): Observable<string> {
    return this.http.get<string>(`${this.backendUrl}/hidden`, {responseType: 'text' as 'json'});
  }

  postGuess(guess: string): Observable<boolean> {
    return this.http.post<boolean>(`${this.backendUrl}/guess/`+guess, guess);
  }

  getRemainingGuesses(): Observable<string> {
    return this.http.get<string>(`${this.backendUrl}/guess/remaining`, {responseType: 'text' as 'json'});
  }

  getHighScore(): Observable<number> {
    return this.http.get<number>(`${this.backendUrl}/highscore`, {responseType: 'text' as 'json'});
  }

  postName(name: string): void {
    this.http.post<void>(`${this.backendUrl}/setup/${name}`, null).subscribe({
      next: () => {
        console.log('Name set successfully');
      },
      error: (error) => {
        console.error('Error setting name:', error);
      }
    });
  }

  getInstructions(): Observable<string> {
    return this.http.get<string>(`${this.backendUrl}/instructions`, {responseType: 'text' as 'json'});
  }

  endGame(): void {
    this.http.post<void>(`${this.backendUrl}/end`, null).subscribe({
      next: () => {
        console.log('Game ended successfully');
      },
      error: (error) => {
        console.error('Error ending game:', error);
      }
    });
  }

  getIngredients(): Observable<Ingredient[]> {
    return this.http.get<Ingredient[]>(`${this.backendUrl}/ingredients`)
  }

  getImage(): Observable<string> {
    return this.http.get<string>(`${this.backendUrl}/image`, {responseType: 'text' as 'json'})
  }

  getHighscores(): Observable<Highscore[]> {
    return this.http.get<Highscore[]>(`${this.backendUrl}/all_highscores`)
  }
}
