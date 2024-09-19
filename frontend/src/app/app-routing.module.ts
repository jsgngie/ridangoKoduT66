import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StartComponent } from './start/start.component';
import { GameComponent } from './game/game.component';
import { HighscoresComponent } from './highscores/highscores.component';

const routes: Routes = [
  { path: '', component: StartComponent },
  { path: 'game', component: GameComponent },
  { path: 'end', component: HighscoresComponent},
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }