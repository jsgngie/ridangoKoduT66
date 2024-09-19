import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-highscores',
  templateUrl: './highscores.component.html',
  styleUrl: './highscores.component.css'
})
export class HighscoresComponent {
  constructor(private router: Router) {}

  public routeToMain() {
    this.router.navigate(["/"]);
  }
}
