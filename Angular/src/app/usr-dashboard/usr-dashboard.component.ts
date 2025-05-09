import { Component, OnInit } from '@angular/core';
import { User } from '../Model/User';
import { AuthService } from '../service/auth.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormGroup, FormControl, Validators, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { UserService } from '../service/user.service';
import { ListAccommodationComponent } from '../list-accommodation/list-accommodation.component';
import { Accommodation } from '../Model/Accommodation';
import { AccommodationService } from '../service/accommodation.service';
import { AccommodationWithImage } from '../Model/AccommodationWithImage';
import { Booking } from '../Model/Booking';


@Component({
  selector: 'app-usr-dashboard',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule, ListAccommodationComponent],
  templateUrl: './usr-dashboard.component.html',
  styleUrl: './usr-dashboard.component.css'
})
export class UsrDashboardComponent implements OnInit {

  userData!: User | null;
  isLoggedIn!: boolean;
  currentForm!: string;
  isOwner: boolean = false;
  isCustomer: boolean = false;
  initialFormValues: any;

  bookingForm!: FormGroup;
  profileForm!: FormGroup;
  bookingHistory: any;

  checkInDate!: Date;
  checkOutDate!: Date;
  sortBy!: string;

  searchAccommodations!: AccommodationWithImage[];

  constructor(private authService: AuthService, private router: Router, 
    private http: HttpClient, private userService: UserService, private accommodationService: AccommodationService) { }

  ngOnInit(): void {
    // Subscribe to isLoggedIn and getUserData observables
    this.authService.isLoggedIn().subscribe(isLoggedIn => {
      this.isLoggedIn = isLoggedIn;
      console.log(isLoggedIn)
    });

    this.authService.getUserData().subscribe(userData => {
      this.userData = userData;
      console.log(userData)
      this.isCustomer = userData?.roles.includes("ROLE_USER")?true:false
      this.isOwner = userData?.roles.includes("ROLE_HOTEL_OWNER")?true:false
    });

    this.bookingForm = new FormGroup({
      id: new FormControl('', Validators.required),
      name: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required),
      city: new FormControl('', Validators.required),
      state: new FormControl('', Validators.required),
      address: new FormControl('', Validators.required),
      price: new FormControl('', Validators.required)
    });

    this.profileForm = new FormGroup({
      firstName: new FormControl(this.userData?.firstName, Validators.required),
      lastName: new FormControl(this.userData?.lastName),
      email: new FormControl(this.userData?.email, [
        Validators.email, // Validates if the input is a valid email format
        Validators.pattern(/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/) // Optional: Custom pattern validator
      ]),
      mobile: new FormControl(this.userData?.phoneNumber, [
        Validators.pattern( /^\(?(\d{3})\)?[-]?(\d{3})[-]?(\d{4})$/) // Optional: Custom pattern validator
      ]),
     
    });
    this.initialFormValues = { ...this.profileForm.value };
  }

  showForm(form: string): void {
    this.currentForm = form;
  }

  resultsFound:boolean = true
  searchAccommodation(){
    this.accommodationService.searchAccomodation(this.checkInDate, this.checkOutDate).subscribe((response) =>{
      console.log(response)
      if(response.length==0){
        this.resultsFound=false
        console.log(this.resultsFound)
      }
      if(response){
        this.searchAccommodations=response.map((accommodation)=>{
          return {...accommodation, imagePath: this.getRandomImageUrl()}
      })
        
        this.sortAccommodations()
      }
    })
  }

  submitBooking(): void {
    console.log(this.bookingForm.value);
    // Add your logic here to submit the booking request to the server
  }

  updateProfile(): void {
    console.log(this.profileForm.value['firstName']);
    const updatedUser: User = new User(this.profileForm.value['firstName'], this.profileForm.value['lastName'],
      this.userData!.password, this.profileForm.value['email'], this.profileForm.value['mobile'], this.userData!.roles)
    this.userService.updateUser(updatedUser, this.userData!.id)
    // Add your logic here to update the customer's profile information on the server
  }

  accommodationDetails!: string

  fetchBookingHistory(): void {
    this.http.get(`http://localhost:8081/booking/${this.userData!.id}/history`)
      .subscribe(data => {
        this.bookingHistory = data;
        console.log(this.bookingHistory)
        for(let booking of this.bookingHistory){
           this.accommodationService.getAccommodation(booking.accommodationId).subscribe(data => {
            const accommodation: Accommodation = data
            booking.accommodationDetails = accommodation.accommodationName+" - "+accommodation.location
          });
        }
      });
  }

  switchToAdminDashboard(){
    this.router.navigate(['/admin-dashboard']);
  }

  logout(): void {
    this.authService.logout();
    // Optionally, navigate to login page
    this.router.navigate(['/login']);
  }

  isProfileFormUnchanged(): boolean {
    // Check if current form values are the same as initial values
    return JSON.stringify(this.profileForm.value) === JSON.stringify(this.initialFormValues);
  }

  sortAccommodations() {
    if (this.sortBy === 'price') {
      this.searchAccommodations.sort((a, b) => a.pricePerNight - b.pricePerNight);
    } else if (this.sortBy === 'rating') {
      this.searchAccommodations.sort((a, b) => b.rating - a.rating); // sort in descending order
    } else if (this.sortBy === 'distance') {
      this.searchAccommodations.sort((a, b) => a.distance - b.distance);
    }
  }

  getRandomImageUrl(): string {
    const imageNames = ['h2.jpg', 'h3.jpg', 'h4.jpg', 'h5.jpg', 'h6.jpg', 'h7.jpg', 'h8.jpg']; // List of image filenames
    const randomIndex = Math.floor(Math.random() * imageNames.length);
    return `/assets/hotels/${imageNames[randomIndex]}`;
  }



}
