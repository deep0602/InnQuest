import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookAccommodationComponent } from './book-accommodation.component';

describe('BookAccommodationComponent', () => {
  let component: BookAccommodationComponent;
  let fixture: ComponentFixture<BookAccommodationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BookAccommodationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BookAccommodationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
