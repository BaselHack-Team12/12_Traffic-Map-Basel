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

      this.getStreets();
      console.log(this.streets);
    
      // max dangerosity
      let maxDangerosiy: number = 0;

      for (let street of this.streets) {
        if (maxDangerosiy < street.dangerosity) maxDangerosiy = street.dangerosity
      }
      console.log('maxDangerosiy: ' +maxDangerosiy);

      // Data formatting
      /* following this example
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

      // Data sorting
      function compare(a,b) {
        if (a[1] < b[1])
          return 1;
        if (a[1] > b[1])
          return -1;
        return 0;
      }
      console.log("sorted streets");
      this.chartData.sort(compare);
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
      this.streets = this.mapDataService.getStreets(); // getting object without promise
    }
  
}
