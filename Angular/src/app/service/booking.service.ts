import { Injectable } from '@angular/core';
import { AccommodationWithImage } from '../Model/AccommodationWithImage';

@Injectable({
  providedIn: 'root'
})
export class BookingService {

  private accomodation!: AccommodationWithImage

  constructor() { }

  setAccomodation(accommodation: AccommodationWithImage){
    this.accomodation = accommodation 
  }
  getAccomodation(){
    return this.accomodation
  }




}
