import { TokenService } from './../services/token.service';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class AfterAuthGuard implements CanActivate {
  constructor(
    private tokenService: TokenService,
    private router: Router) { }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {

    if (this.tokenService.loggedIn()) {
      this.router.navigateByUrl("/")
      return false
    }
    return true;
  }
}
