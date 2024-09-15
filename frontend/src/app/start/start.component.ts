import { Component } from '@angular/core';

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrl: './start.component.css'
})
export class StartComponent {
  playerName: string = '';
  isShaking: boolean = false;

  startGame() {
    if (this.playerName) {
      console.log('Player name: ', this.playerName);
      this.isShaking = false;
      // start gameloop
    } else {
      this.isShaking = true;
    }
  }
}