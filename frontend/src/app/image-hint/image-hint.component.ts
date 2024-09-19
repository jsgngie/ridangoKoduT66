import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api.service';

@Component({
  selector: 'app-image-hint',
  templateUrl: './image-hint.component.html',
  styleUrl: './image-hint.component.css'
})
export class ImageHintComponent implements OnInit{
  imageUrl: string = ""

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    this.apiService.getImage().subscribe({
      next: (data: string) => {
        this.imageUrl = data;
        console.log(data)
      },
      error: (error) => {
        console.error('Error fetching image:', error);
      }
    });
  }
}
