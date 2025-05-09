import { Component, NgModule, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Accommodation } from '../Model/Accommodation';
import { BookingService } from '../service/booking.service';
import { AccommodationWithImage } from '../Model/AccommodationWithImage';
import { CommonModule } from '@angular/common';
import { PaymentService } from '../service/payment.service';
import { HttpClient } from '@angular/common/http';
import { Booking } from '../Model/Booking';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../service/auth.service';
import { User } from '../Model/User';
@Component({
  selector: 'app-book-accommodation',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './book-accommodation.component.html',
  styleUrl: './book-accommodation.component.css'
})
export class BookAccommodationComponent implements OnInit {
  userData!: User | null;
  isLoggedIn!: boolean;

  accommodationWithImage!: AccommodationWithImage;
  accommodation!: Accommodation;
  resbooking!: Booking;
  dateError: boolean=false;

  constructor(private router: Router, private bookingService: BookingService, private authService:AuthService,
    private paymentService: PaymentService, private http: HttpClient) { }

  ngOnInit() {

    // Subscribe to isLoggedIn and getUserData observables
    this.authService.isLoggedIn().subscribe(isLoggedIn => {
      this.isLoggedIn = isLoggedIn;

    });

    this.authService.getUserData().subscribe(userData => {
      this.userData = userData;

    });


    this.accommodationWithImage = this.bookingService.getAccomodation()
    this.accommodation = { ...this.accommodationWithImage }
  }
  validateDates(form: any) {
    const checkInDate = new Date(form.value.checkInDate);
    const checkOutDate = new Date(form.value.checkOutDate);

    if (checkInDate > checkOutDate) {
      this.dateError = true;
      form.controls['checkOutDate'].setErrors({ 'invalidDate': true });
    } else {
      this.dateError = false;
      form.controls['checkOutDate'].setErrors(null);
    }
  }
  payNow(form: any) {
    const bookingData = {
      userId: this.userData!.id, // accomodation.userid
      accommodationId: this.accommodation.accommodationId, // akash.accommodation.id
      checkInDate: form.value.checkInDate,
      checkOutDate: form.value.checkOutDate,
      totalPeople: form.value.totalPeople,
      //totalPrice: this.calculateTotalPrice(form.value.checkInDate, form.value.checkOutDate),
    };
    //  this.paymentService.setBooking(bookingData);

    this.http.post('http://localhost:8081/booking', bookingData).subscribe({
      next: (response: any) => {
        console.log("hello")
        this.resbooking = response;
        this.paymentService.setBooking(this.resbooking);
        this.router.navigate(['/payment']);
      },
      error: (error) => {
        console.error('Error booking accommodation', error);
      }
    });

    // this.router.navigate(['/payment']);
  }
  logout(): void {
    this.authService.logout();
    // Optionally, navigate to login page
    this.router.navigate(['/login']);
  }
}
