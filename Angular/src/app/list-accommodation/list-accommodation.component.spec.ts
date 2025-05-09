import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListAccommodationComponent } from './list-accommodation.component';

describe('ListAccommodationComponent', () => {
  let component: ListAccommodationComponent;
  let fixture: ComponentFixture<ListAccommodationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListAccommodationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListAccommodationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
