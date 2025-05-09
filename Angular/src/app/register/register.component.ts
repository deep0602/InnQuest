import { Component, OnInit, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule, RouterOutlet, RouterLink, RouterLinkActive } from '@angular/router';
import { User } from '../Model/User';
import { UserService } from '../service/user.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule, RouterOutlet, RouterLink, RouterLinkActive],
  providers: [UserService, HttpClient],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  signUpForm = {
    firstName: '',
    lastName: '',
    email: '',
    phone: '',
    password: '',
    roles: [],
    hotelName: '',
    hotelAddress: '',
    hotelLicense: ''
  };

  @ViewChild('hotelName', { static: false }) hotelName: any;
  @ViewChild('hotelAddress', { static: false }) hotelAddress: any;
  @ViewChild('hotelLicense', { static: false }) hotelLicense: any;

  result: boolean = false;
  user!: User;

  isHotelOwner = false;

  invalidPattern = /[^\w!@#$%^&*]/;
  whiteSpacePattern = /\s/;
  phoneNumberRegex = /^\(?(\d{3})\)?[-]?(\d{3})[-]?(\d{4})$/;
  emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

  constructor(private userService: UserService) { }

  ngOnInit(): void { }

  toggleHotelOwner() {
    this.isHotelOwner = !this.isHotelOwner;
  }

  onSubmit() {
    const role = this.isHotelOwner? "ROLE_HOTEL_OWNER":"ROLE_USER"
    const { firstName, lastName, password, email, phone} = this.signUpForm;
    this.userService.getUserByEmail(email).subscribe(response => {
      this.user = response;
      if (response == null) {
        this.userService.createUser(new User(firstName, lastName, password,
          email, phone, [role]));
      }
      else{
        if(!response.roles.includes(role)){
          response.roles.push(role)
          this.userService.createUser(response)
        }
        else{
          console.log(`User ${response.email} with role already exists`)
        }
      }
    })

      ;
  }
}
