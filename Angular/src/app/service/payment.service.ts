import { Injectable } from '@angular/core';
import { Booking } from '../Model/Booking';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  private booking!: Booking

  constructor() { }

  setBooking(booking:any){
    this.booking = booking 
  }
  getBooking(){
    return this.booking
  }
}
