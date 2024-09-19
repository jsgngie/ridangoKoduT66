import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api.service';
import { Ingredient } from '../ingredient';

@Component({
  selector: 'app-ingredient-list',
  templateUrl: './ingredient-hint.component.html',
  styleUrl: './ingredient-hint.component.css'
})
export class IngredientHintComponent implements OnInit {
  ingredients: Ingredient[] | null = null;

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    this.apiService.getIngredients().subscribe({
      next: (data: Ingredient[]) => {
        this.ingredients = data;
      },
      error: (error) => {
        console.error('Error fetching ingredients:', error);
      }
    });
  }
}