import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { trigger, state, style, animate, transition, keyframes, AnimationEvent } from '@angular/animations';
import { ApiService } from '../api.service';

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.css'],
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
export class StartComponent {
  playerName: string = '';
  isShaking: boolean = false;
  gameState: string = 'inactive';

  constructor(private router: Router, private apiService: ApiService) {}

  startGame() {
    if (this.playerName) {
      this.isShaking = false;
      this.apiService.postName(this.playerName);
      this.gameState = 'active';
    } else {
      this.isShaking = true; // Trigger the shake animation
    }
  }

  onAnimationFinished(event: AnimationEvent) {
    if (event && this.gameState === 'active') {
      this.router.navigate(['/game']);
    }
  }

  onShakeFinished(event: AnimationEvent) {
    this.isShaking = false;
  }
}
