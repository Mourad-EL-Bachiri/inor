import { JwtInterceptor } from './services/jwt.interceptor';
import { AuthGuard } from './guards/auth.guard';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddRolesComponent } from './components/add-roles/add-roles.component';
import { EditRolesComponent } from './components/edit-roles/edit-roles.component';
import { ListRolesComponent } from './components/list-roles/list-roles.component';
import { NavbarComponent } from './components/shared/navbar/navbar.component';
import { PageNotFoundComponent } from './components/shared/page-not-found/page-not-found.component';
import { LoginComponent } from './components/login/login.component';

import { ReactiveFormsModule } from "@angular/forms";
import { HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http";
import { HomeComponent } from './components/home/home.component';

@NgModule({
  declarations: [
    AppComponent,
    AddRolesComponent,
    EditRolesComponent,
    ListRolesComponent,
    NavbarComponent,
    PageNotFoundComponent,
    LoginComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    AuthGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
