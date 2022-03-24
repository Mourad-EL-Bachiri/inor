import { AfterAuthGuard } from './guards/after-auth.guard';
import { AuthGuard } from './guards/auth.guard';
import { HomeComponent } from './components/home/home.component';
import { AddRolesComponent } from './components/add-roles/add-roles.component';
import { PageNotFoundComponent } from './components/shared/page-not-found/page-not-found.component';
import { LoginComponent } from './components/login/login.component';
import { EditRolesComponent } from './components/edit-roles/edit-roles.component';
import { ListRolesComponent } from './components/list-roles/list-roles.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: "", redirectTo: '/home', pathMatch: 'full'},
  {
    path: "roles", children: [
      { path: "getRoles", component: ListRolesComponent, canActivate: [AuthGuard] },
      { path: "createRoles", component: AddRolesComponent, canActivate: [AuthGuard]},
      { path: "editRoles/:id", component: EditRolesComponent, canActivate: [AuthGuard]}
    ],
  },
  { path: "login", component: LoginComponent , canActivate: [AfterAuthGuard] },
  { path: "home", component: HomeComponent },  
  { path: "**", component: PageNotFoundComponent }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
