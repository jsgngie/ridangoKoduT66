import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IngredientHintComponent } from './ingredient-hint.component';

describe('IngredientHintComponent', () => {
  let component: IngredientHintComponent;
  let fixture: ComponentFixture<IngredientHintComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [IngredientHintComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IngredientHintComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
