import { BehaviorSubject } from 'rxjs';
import { TokenService } from './token.service';
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class AccountService {


  private loggedIn = new BehaviorSubject<boolean>(this.tokenService.loggedIn());

  authStatus = this.loggedIn.asObservable();

  constructor(private tokenService: TokenService) { }

  changeStatus(value: boolean) {
    this.loggedIn.next(value)
    console.log('loggedIn', this.tokenService.loggedIn())
  }

}
