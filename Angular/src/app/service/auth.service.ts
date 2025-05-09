// auth.service.ts

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { User } from '../Model/User';
import { Router } from '@angular/router';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loggedIn$: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  private userData$: BehaviorSubject<User | null> = new BehaviorSubject<User | null>(null);

  constructor(private http: HttpClient, private router: Router) { }

  login(credentials: { email: string, password: string }): Observable<any> {
    // Example login request to your API
    return this.http.post<any>('http://localhost:8081/login', credentials).pipe(
      tap(response => {
        // Assuming your API returns user data upon successful login
        this.userData$.next(response);
        this.loggedIn$.next(true);
        // Optionally, store tokens securely (e.g., in localStorage or cookies)
        localStorage.setItem('token', response.token);
      })
    );
  }

  logout(): void {
    // Clear user data and login status
    this.userData$.next(null);
    this.loggedIn$.next(false);
    // Clear stored tokens
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }

  isLoggedIn(): Observable<boolean> {
    return this.loggedIn$.asObservable();
  }

  getUserData(): Observable<User | null> {
    return this.userData$.asObservable();
  }
}
