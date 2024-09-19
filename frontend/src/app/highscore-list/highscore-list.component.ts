import { AfterViewInit, Component } from '@angular/core';
import { ApiService } from '../api.service';
import { Highscore } from '../highscore';

@Component({
  selector: 'app-highscore-list',
  templateUrl: './highscore-list.component.html',
  styleUrl: './highscore-list.component.css'
})
export class HighscoreListComponent implements AfterViewInit{
  highscores: Highscore[] | null = null;

  constructor(private apiService: ApiService) {}

  ngAfterViewInit(): void {
    this.apiService.getHighscores().subscribe({
      next: (data: Highscore[]) => {
        this.sortHighscores(data);
        this.highscores = data;
      },
      error: (error) => {
        console.error('Error fetching highscores:', error);
      }
    });
  }

  sortHighscores(data: Highscore[]): void {
    data.sort((a, b) => b.score - a.score);
  }
}
