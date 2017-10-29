import { MapBlockComponent } from './map-block/map-block.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule }    from '@angular/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { StreetDetailsComponent } from './street-details/street-details.component';
import { ListBlockComponent } from './list-block/list-block.component';
import { MainPageComponent } from './main-page/main-page.component';

import { MapDataService } from './map-data/map-data.service';

@NgModule({
  declarations: [
    AppComponent,
    StreetDetailsComponent,
    MapBlockComponent,
    ListBlockComponent,
    MainPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpModule,
    NgbModule.forRoot()
  ],
  providers: [MapDataService],
  bootstrap: [AppComponent]
})
export class AppModule { }
