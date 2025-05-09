import { Injectable } from '@angular/core';
import { Accommodation } from '../Model/Accommodation';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccommodationService {

  private apiUrl = 'http://localhost:8081/accommodations';

  constructor(private http: HttpClient) { }

  createAccomodation(accommodation: Accommodation): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });

    console.log(accommodation)
    return this.http.post<Accommodation>(this.apiUrl, accommodation, { headers })

  }

  getAccommodation(accId: number){
    return this.http.get<Accommodation>(this.apiUrl + `/${accId}`)
  }

  updateAccomodation(accommodation: Accommodation, accommodationId: number|null): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    console.log(accommodation)
    return this.http.put<Accommodation>(this.apiUrl + `/${accommodationId}`, accommodation, { headers })
  }

  deleteAccomodation(accommodationId: number|null): Observable<any> {
    return this.http.delete<Accommodation>(this.apiUrl + `/${accommodationId}`)
  }

  searchAccomodation(checkInDate: Date, checkOutDate: Date){
    console.log(checkInDate, checkOutDate)

    let params = new HttpParams()
      .set('checkinDate', new Date(checkInDate).toISOString().slice(0,10))
      .set('checkoutDate', new Date(checkOutDate).toISOString().slice(0,10))

    return this.http.get<Accommodation[]>(this.apiUrl+'/search',{params})
  }
}
