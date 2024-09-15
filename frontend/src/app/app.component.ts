import { Component, OnInit } from '@angular/core';
import { ApiService } from './api.service';

@Component({
  selector: 'app-root',   // Adjust the selector as needed
  templateUrl: './app.component.html',  // Your HTML template file
  styleUrls: ['./app.component.css']     // Your CSS file
})
export class AppComponent {
  public message: string = '';  // Property to hold the fetched string

  constructor(private apiService: ApiService) {}
}