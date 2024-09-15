import { Component, ViewChild, ElementRef, AfterViewInit } from '@angular/core';
import { ApiService } from './api.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  message: string = '';
  @ViewChild('backgroundMusic') backgroundMusic!: ElementRef<HTMLAudioElement>;
  isSpinning: boolean = true;
  
  constructor(private apiService: ApiService) {}

  ngAfterViewInit() {
    this.backgroundMusic.nativeElement.play();
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