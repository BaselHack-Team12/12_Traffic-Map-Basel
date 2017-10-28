import { MainPageComponent } from './main-page/main-page.component';
import { ListBlockComponent } from './list-block/list-block.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { StreetDetailsComponent } from './street-details/street-details.component';
import { MapBlockComponent } from './map-block/map-block.component';

const routes: Routes = [
  { path: 'detail', component: StreetDetailsComponent },
  { path: '',      component: MainPageComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
