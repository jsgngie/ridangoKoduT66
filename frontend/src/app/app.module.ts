import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { provideHttpClient, withFetch } from '@angular/common/http';
import { StartComponent } from './start/start.component';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { GameComponent } from './game/game.component';
import { ApiService } from './api.service';
import { HighscoresComponent } from './highscores/highscores.component';
import { IngredientHintComponent } from './ingredient-hint/ingredient-hint.component';
import { ImageHintComponent } from './image-hint/image-hint.component';

@NgModule({
  declarations: [
    AppComponent,
    StartComponent,
    GameComponent,
    HighscoresComponent,
    IngredientHintComponent,
    ImageHintComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    BrowserAnimationsModule
  ],
  providers: [
    provideClientHydration(),
    provideHttpClient(withFetch())
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
