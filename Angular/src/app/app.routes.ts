import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router'
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { RegisterComponent } from './register/register.component';
import { UsrDashboardComponent } from './usr-dashboard/usr-dashboard.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { BookAccommodationComponent } from './book-accommodation/book-accommodation.component';
import { PaymentComponent } from './payment/payment.component';

export const routes: Routes = [
    { path: '', redirectTo: '/home', pathMatch: 'full' },
    { path: 'home', component: HomeComponent },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'dashboard', component: UsrDashboardComponent},
    { path: 'admin-dashboard', component: AdminDashboardComponent},
    {path:'booking', component: BookAccommodationComponent},
    {path:'payment', component: PaymentComponent},
    { path: '**', component: NotFoundComponent },
  ];

  @NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }
