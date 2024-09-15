import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { trigger, state, style, animate, transition, AnimationEvent} from '@angular/animations';

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrl: './start.component.css',
  animations: [
    trigger('hideInput', [
      state('inactive', style({
        opacity: 1,
        transform: 'scale(1)'
      })),
      state('active', style({
        opacity: 0,
        transform: 'scale(0.8) translateX(-50vh)'
      })),
      transition('inactive => active', [
        animate('500ms ease-in')
      ])
    ]),
    trigger('hideSplash', [
      state('inactive', style({
        opacity: 1,
        transform: 'scale(1)'
      })),
      state('active', style({
        opacity: 0,
        transform: 'translateX(-50vh)'
      })),
      transition('inactive => active', [
        animate('500ms ease-in')
      ])
    ])
  ]
})
export class StartComponent {
  playerName: string = '';
  isShaking: boolean = false;
  gameState: string = 'inactive';

  constructor(private router: Router) {}

  startGame() {
    if (this.playerName) {
      this.isShaking = false;
      this.gameState = 'active'
    } else {
      this.isShaking = true;
    }
  }

  onAnimationFinished(event: AnimationEvent) {
    if (event && this.gameState == 'active') {
      this.router.navigate(['/game']);
    }
  }
}