import { Component, OnInit } from '@angular/core';
import { User } from '../Model/User';
import { AuthService } from '../service/auth.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormGroup, FormControl, Validators, ReactiveFormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { AccommodationService } from '../service/accommodation.service';
import { Accommodation } from '../Model/Accommodation';

@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.css'
})
export class AdminDashboardComponent implements OnInit {

  userData!: User | null;
  isLoggedIn!: boolean;
  updateAccommodation!: Accommodation

  showAccommodations = false;
  showUpdateAccommodationForm = false;
  showAddNewRoomForm = false;

  accommodations!: any;
  updateAccommodationForm !: FormGroup;
  addNewAccomodationForm !: FormGroup;
  initialUpdateFormValues: any;

  constructor(private authService: AuthService, private router: Router,
    private http: HttpClient, private accommodationService: AccommodationService) { }

  ngOnInit(): void {
    // Subscribe to isLoggedIn and getUserData observables
    this.authService.isLoggedIn().subscribe(isLoggedIn => {
      this.isLoggedIn = isLoggedIn;
        
    });

    this.authService.getUserData().subscribe(userData => {
      this.userData = userData;
      console.log(userData)
      if(userData)
        this.fetchAccommodations()
      
    });

    this.addNewAccomodationForm = new FormGroup({
      accommodationName: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required),
      location: new FormControl('', Validators.required),
      checkInDate: new FormControl('', Validators.required),
      checkOutDate: new FormControl('', Validators.required),
      amenities: new FormControl('', Validators.required),
      price: new FormControl('', Validators.required),
      distance: new FormControl('', Validators.required),
    });

  }

  fetchAccommodations() {
    this.http.get('http://localhost:8081/accommodations/owner/' + this.userData?.id)
      .subscribe(data => {
        this.accommodations=data;
      });
  }

  setUpdateForm(){
    this.updateAccommodationForm = new FormGroup({
      accommodationName: new FormControl(this.updateAccommodation.accommodationName, Validators.required),
      description: new FormControl(this.updateAccommodation.description, Validators.required),
      location: new FormControl(this.updateAccommodation.location, Validators.required),
      checkInDate: new FormControl(this.updateAccommodation.checkInDate, Validators.required),
      checkOutDate: new FormControl(this.updateAccommodation.checkOutDate, Validators.required),
      amenities: new FormControl(this.updateAccommodation.amenities, Validators.required),
      price: new FormControl(this.updateAccommodation.pricePerNight, Validators.required),
      distance: new FormControl(this.updateAccommodation.distance, Validators.required),
    });
    this.initialUpdateFormValues = {...this.updateAccommodationForm}.value

  }


  updateAccommodationDescription() {
    console.log(this.updateAccommodationForm.value);
    const { accommodationName, description, location, checkInDate, checkOutDate, amenities, price, distance} = this.updateAccommodationForm.value;
    const updatedAccommodation:Accommodation = new Accommodation(accommodationName,
      description, location, checkInDate, checkOutDate, amenities, price, distance, this.updateAccommodation.rating, this.userData!.id
    )
    console.log(updatedAccommodation)
    console.log(this.updateAccommodation.accommodationId)
    this.accommodationService.updateAccomodation(updatedAccommodation, this.updateAccommodation.accommodationId).subscribe((response: Accommodation) => {
      const index = this.accommodations.findIndex((acc:Accommodation) => acc.accommodationId === this.updateAccommodation.accommodationId);
      if (index !== -1) {
        this.accommodations[index] = response;
      }
      this.showUpdateAccommodationForm = false;
      this.showAccommodations=true;
    })
  }

  isUpdationFormUnchanged(){
    // Check if current form values are the same as initial values
    return JSON.stringify(this.updateAccommodationForm.value) === JSON.stringify(this.initialUpdateFormValues);
  }

  addAccommodation() {
    console.log(this.addNewAccomodationForm.value);
    const { accommodationName, description, location, checkInDate, checkOutDate, amenities, price, distance} = this.addNewAccomodationForm.value;
    const createAccommodation = new Accommodation(accommodationName,
      description, location, checkInDate, checkOutDate, amenities, price, distance, Math.round((4.0 + Math.random() * (5.0 - 4.0))), this.userData!.id
    )
    this.accommodationService.createAccomodation(createAccommodation).subscribe((response:Accommodation) => {
      createAccommodation.accommodationId = response.accommodationId
      this.accommodations.push(createAccommodation);
      this.showAddNewRoomForm = false;
      this.showAccommodations=true;
    })
  }

 deleteAccommodation() {
  
  console.log(this.updateAccommodation)
    this.accommodationService.deleteAccomodation(this.updateAccommodation.accommodationId).subscribe(() => {
      this.accommodations = this.accommodations.filter((accommodation: Accommodation) => accommodation !== this.updateAccommodation);
      this.showAccommodations=true;
    })
    
    
  }

  logout(): void {
    this.authService.logout();
    // Optionally, navigate to login page
    this.router.navigate(['/login']);
  }




}
function sleep(arg0: number) {
  throw new Error('Function not implemented.');
}

