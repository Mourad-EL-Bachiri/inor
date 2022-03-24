import { logging } from 'protractor';
import { AccountService } from './../services/account.service';
import { TokenService } from './../services/token.service';
import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot } from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {


  constructor(
    private tokenService: TokenService,
    private accountService: AccountService,
    private router: Router) { }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {
    
    if (!this.tokenService.loggedIn()) {
      console.log('from guarde', this.tokenService.loggedIn())
      this.tokenService.remove();
      this.accountService.changeStatus(false);
      this.router.navigateByUrl("/login")
      return false
    }
    return true;
  }
  
}
