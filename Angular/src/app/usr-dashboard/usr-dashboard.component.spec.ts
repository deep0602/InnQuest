import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsrDashboardComponent } from './usr-dashboard.component';

describe('UsrDashboardComponent', () => {
  let component: UsrDashboardComponent;
  let fixture: ComponentFixture<UsrDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UsrDashboardComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UsrDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
