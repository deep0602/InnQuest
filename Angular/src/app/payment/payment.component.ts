import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PaymentService } from '../service/payment.service';
import { HttpClient } from '@angular/common/http';
import { Booking } from '../Model/Booking';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from '../service/auth.service';
import { User } from '../Model/User';

@Component({
  selector: 'app-payment',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './payment.component.html',
  styleUrl: './payment.component.css'
})
export class PaymentComponent implements OnInit {
  userData!: User | null;
  isLoggedIn!: boolean;
  customerfName!: string;
  customerlName!: string;
  booking: Booking | undefined;
  paymentTypes = ['creditCard', 'debitCard', 'paypal'];
  isPaid: string = "progress"

  constructor(private router: Router, private paymentService: PaymentService, private authService: AuthService,
    private http: HttpClient) { }
  ngOnInit(): void {

    // Subscribe to isLoggedIn and getUserData observables
    this.authService.isLoggedIn().subscribe(isLoggedIn => {
      this.isLoggedIn = isLoggedIn;

    });

    this.authService.getUserData().subscribe(userData => {
      this.userData = userData;
      this.customerfName = userData!.firstName
      this.customerfName = userData!.lastName

    });

    this.booking = this.paymentService.getBooking();
    console.log(this.booking);
    this.getName();
  }
  paymentType: any;
  processPayment() {
    const paymentData = {
      bookingId: this.booking?.bookingId, // akash.accommodation.id
      userId: this.booking?.userId,
      amount: this.booking?.totalPrice,
      paymentMethod: this.paymentType,
      paymentStatus: "success"
    };
    console.log(paymentData);
    this.http.post('http://localhost:8081/payments', paymentData).subscribe({
      next: (response: any) => {
        this.isPaid="success"
      },
      error: (error) => {
        console.error('Error booking accommodation', error);
        this.isPaid="failed"
        // Handle errors appropriately
      }
    });
  }


  async getName(): Promise<void> {
    try {
      let id = this.booking?.userId;
      const response: any = await this.http.get(`http://localhost:8081/${id}`).toPromise();
      this.customerfName = response.firstName;
      this.customerlName = response.lastName;
    } catch (error: any) {
      console.error(error);
    }
    console.log(this.booking?.totalPrice);
  }

  logout(): void {
    this.authService.logout();
    // Optionally, navigate to login page
    this.router.navigate(['/login']);
  }

  goHome(): void {
    this.router.navigate(['/dashboard']);
  }

}
