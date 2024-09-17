import { Component, ViewChild, ElementRef, AfterViewInit } from '@angular/core';
import { ApiService } from './api.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  @ViewChild('backgroundMusic') backgroundMusic!: ElementRef<HTMLAudioElement>;
  isSpinning: boolean = false;
  
  constructor(private apiService: ApiService) {}

  ngOnInit() {
    this.apiService.setupGame();
  }

  playMusic() {
    if (this.backgroundMusic.nativeElement.paused) {
      this.backgroundMusic.nativeElement.play();
      this.isSpinning = true;
    } else {
      this.backgroundMusic.nativeElement.pause();
      this.isSpinning = false;
    }
  }
  

}