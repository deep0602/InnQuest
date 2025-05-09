import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule} from '@angular/forms';
import { RouterModule, RouterOutlet, RouterLink, RouterLinkActive, ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../service/user.service';
import { LoginRequest } from '../Model/LoginRequest';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule, RouterOutlet, RouterLink, RouterLinkActive],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  signInForm = {
    email: '',
    password: '',
    role: ''
  }

  invalidPattern = new RegExp('[^\\w!@#$%^&*]')
  whiteSpacePattern = new RegExp('/\s/')
  emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

  constructor(private router: Router, private userService: UserService, private authService: AuthService) { }


  onSubmit(): void {
    const {email, password} = this.signInForm;
    this.authService.login(new LoginRequest(email, password)).subscribe(response =>{
      console.log(response)
      this.goToDashboard()
    }, error => console.log(error));
  }

  goToDashboard(){
    this.router.navigate(['/dashboard']);
  }




}
