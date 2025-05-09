import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../Model/User';
import { LoginRequest } from '../Model/LoginRequest';
import { catchError, map, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {

  private apiUrl = 'http://localhost:8081';

  constructor(private http: HttpClient) { }

  createUser(user: User): void {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });

    this.http.post<User>(this.apiUrl + '/register', user, { headers }).subscribe(response => {
      console.log(response);
    }
    );
  }

  getUser(userId: number){
    this.http.get<User>(this.apiUrl + `/${userId}`).subscribe(response => {
      console.log(response);
    }
    );
  }

  updateUser(user: User, userId: number|null): void {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });

    this.http.put<User>(this.apiUrl + `/${userId}`, user, { headers }).subscribe(response => {
      console.log(response);
    }
    );
  }

  loginUser(loginRequest: LoginRequest): Observable<User> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.post<User>(this.apiUrl + '/login', loginRequest, { headers })
  }

  getUserByEmail(email: string): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/check-email/${email}`)
  }
}
