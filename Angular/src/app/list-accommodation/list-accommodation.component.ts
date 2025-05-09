import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { Accommodation } from '../Model/Accommodation';
import { AccommodationWithImage } from '../Model/AccommodationWithImage';
import { BookingService } from '../service/booking.service';

@Component({
  selector: 'app-list-accommodation',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './list-accommodation.component.html',
  styleUrl: './list-accommodation.component.css'
})
export class ListAccommodationComponent implements OnInit {
  @Input() accommodations!: AccommodationWithImage[] 

  constructor(private bookingService: BookingService, private router: Router) { }
  ngOnInit(): void {
    
  }
  getRandomImageUrl(): string {
    const imageNames = ['h2.jpg', 'h3.jpg', 'h4.jpg', 'h5.jpg', 'h6.jpg', 'h7.jpg', 'h8.jpg']; // List of image filenames
    const randomIndex = Math.floor(Math.random() * imageNames.length);
    return `/assets/hotels/${imageNames[randomIndex]}`;
  }

  bookHotel(index: number) {
    this.bookingService.setAccomodation(this.accommodations[index]);
    console.log(this.bookingService.getAccomodation())
    this.router.navigate(['/booking']);
  }
}
