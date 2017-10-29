import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';

import { STREETS } from './fake-data';
import { Street } from './street';

@Injectable()
export class MapDataService {

  private streetsUrl = 'api/streets';  // URL to web api

  constructor(private http: Http) { }

  getStreets(): Promise<Street[]> {
    console.log(STREETS);
    return Promise.resolve(STREETS);

    // For real service
    // return this.http.get(this.streetsUrl)
    //            .toPromise()
    //            .then(response => response.json().data as Street[])
    //            .catch(this.handleError);
  }


  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}
