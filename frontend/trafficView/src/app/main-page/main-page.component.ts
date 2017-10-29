import { Component, OnInit } from '@angular/core';

import { MapDataService } from '../map-data/map-data.service';
import { Street } from '../map-data/street';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {


  private chartData: Array<any>;
  streets: Street[] = [];
  
  
    constructor(private mapDataService: MapDataService) {}
  
    ngOnInit() {


      this.generateData();

      // // give everything a chance to get loaded before starting the animation to reduce choppiness
      // setTimeout(() => {
      //   this.generateData();
  
      //   // change the data periodically
      //   setInterval(() => this.generateData(), 3000);
      // }, 1000);



      this.getStreets();
      console.log(this.streets);
    
      // this.streets = [
      //   { id: 11, name: 'etwasStrasse', dangerosity: 2, coordinates: ''},
      //   { id: 12, name: 'andereStrasse', dangerosity: 3, coordinates: ''}
      //   ];

      //   this.chartData = this.streets;


      // max dangerosity
      let maxDangerosiy: number = 0;

for (let street of this.streets) {
  if (maxDangerosiy < street.dangerosity) maxDangerosiy = street.dangerosity
}
console.log('maxDangerosiy: ' +maxDangerosiy);

      // transform
/*
0:"Index 0"
1:93
length:2
*/
this.chartData = [];
for (let street of this.streets) {
  console.log(street); 
  this.chartData.push([
    street.name,
    street.dangerosity / maxDangerosiy * 100
  ]);
}

console.log("formated streets");
console.log(this.chartData);
    }
  
    generateData() {
      this.chartData = [];
      for (let i = 0; i < (8 + Math.floor(Math.random() * 10)); i++) {
        this.chartData.push([
          `Index ${i}`,
          Math.floor(Math.random() * 100)
        ]);
      }
      console.log(this.chartData);
    }

    getStreets(): void {
      console.log("request ... from main page");
  
      //this.mapDataService.getStreetsPromise().then(streets => this.streets = streets);
      this.streets = this.mapDataService.getStreets();
    }
  
}
