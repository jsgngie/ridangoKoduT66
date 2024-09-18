import { AfterViewChecked, AfterViewInit, Component, OnInit } from '@angular/core';
import { ApiService } from '../api.service';
import { trigger, transition, style, animate, keyframes, AnimationEvent} from '@angular/animations';
import { Router } from '@angular/router';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrl: './game.component.css',
  animations: [
    trigger('fadeInAnimation', [
      transition(':enter', [
        style({ opacity: 0 }),
        animate('750ms', style({ opacity: 1 }))
      ]),
    ]),
    trigger('shakingAnimation', [
      transition('false => true', [
        animate('500ms ease-in-out', keyframes([
          style({ transform: 'translateX(-10px)', offset: 0.1 }),
          style({ transform: 'translateX(10px)', offset: 0.2 }),
          style({ transform: 'translateX(-10px)', offset: 0.3 }),
          style({ transform: 'translateX(10px)', offset: 0.4 }),
          style({ transform: 'translateX(-10px)', offset: 0.5 }),
          style({ transform: 'translateX(10px)', offset: 0.6 }),
          style({ transform: 'translateX(-10px)', offset: 0.7 }),
          style({ transform: 'translateX(10px)', offset: 0.8 }),
          style({ transform: 'translateX(-5px)', offset: 0.9 }),
          style({ transform: 'translateX(0)', offset: 1.0 })
        ]))
      ])
    ])
  ]
})
export class GameComponent implements AfterViewInit {

  hiddenString: string | null = null;
  remainingGuesses: number | null = null;
  highScore: number | null = null;
  isShaking: boolean = false;
  instructions: string | null = null;

  constructor(private apiService: ApiService, private router: Router) {}

  ngAfterViewInit(): void {
    this.getHiddenString();
    this.getRemainingGuesses();
    this.getHighscore();
    this.getInstructions();
  }

  getHiddenString(): void {
    this.apiService.getHiddenString().subscribe({
      next: (response: string) => {
        this.hiddenString = response;
        console.log('Received hidden string:', this.hiddenString);
      },
      error: (error) => {
        console.error('Error fetching hidden string:', error);
      }
    });
  }
  
  getRemainingGuesses(): void {
    this.apiService.getRemainingGuesses().subscribe({
      next: (response: string) => {
        this.remainingGuesses = parseInt(response);
        console.log('Received remaining guesses:', this.remainingGuesses);

        if (this.remainingGuesses === 0) {
          console.log("Uh oh... game over!")
          this.endGame(); 
        }
      },
      error: (error) => {
        console.error('Error fetching remaining guesses:', error);
      }
    });
  }
  
  getHighscore(): void {
    this.apiService.getHighScore().subscribe({
      next: (response: number) => {
        this.highScore = response;
        console.log('Received remaining guesses:', this.highScore);
      },
      error: (error) => {
        console.error('Error fetching remaining guesses:', error);
      }
    });
  }

  getInstructions(): void {
    this.apiService.getInstructions().subscribe({
      next: (response: string) => {
        this.instructions = response;
        console.log('Received instructions:', this.instructions);
      },
      error: (error) => {
        console.error('Error fetching instructions:', error);
      }
    });
  }

  guess(guess: string, drinkInput: HTMLInputElement): void {
    if (!guess) {
      this.isShaking = true;
      return
    }
    this.apiService.postGuess(guess).subscribe({
      next: (response: boolean) => {
        if (response == true) {

          drinkInput.value = '';
          this.getHiddenString();
          this.getRemainingGuesses();
          this.getHighscore();
          this.getInstructions();


        } else {

          this.getRemainingGuesses();
          drinkInput.value = '';
          this.getHiddenString();
        }
      },
      error: (error) => {
        console.error('Error fetching feedback:', error);
      }
    });
  }

  onShakeFinished(event: AnimationEvent) {
    this.isShaking = false;
  }

  endGame() {
    this.apiService.endGame();
    this.router.navigate(['/']);
  }
}
