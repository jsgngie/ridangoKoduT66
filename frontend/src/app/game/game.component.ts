import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api.service';
import { trigger, transition, style, animate } from '@angular/animations';

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
    ])
  ]
})
export class GameComponent implements OnInit {

  hiddenString: string | null = null;

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
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
}
