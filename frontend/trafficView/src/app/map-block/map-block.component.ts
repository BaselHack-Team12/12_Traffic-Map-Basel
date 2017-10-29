import { Component, OnInit } from '@angular/core';

import { MapDataService } from '../map-data/map-data.service';
import { Street } from '../map-data/street';


@Component({
  selector: 'app-map-block',
  templateUrl: './map-block.component.html',
  styleUrls: ['./map-block.component.css']
})
export class MapBlockComponent implements OnInit {

  streets: Street[] = [];
  
  constructor(private mapDataService: MapDataService) { }

  getStreets(): void {
    console.log("request ...");

    this.mapDataService.getStreets().then(streets => this.streets = streets);
  }

  ngOnInit() {
    this.getStreets();

  }

}
