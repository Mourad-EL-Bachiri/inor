import { Validators, FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AccountService } from './../../services/account.service';
import { TokenService } from './../../services/token.service';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']

})

export class LoginComponent implements OnInit {

  loginForm = new FormGroup({
    email: new FormControl(null, [Validators.required, Validators.email]),
    password: new FormControl(null, [Validators.required, Validators.minLength(6)])
  })

  constructor(
    private authService: AuthService,
    private tokenService: TokenService,
    private accountService: AccountService,
    private router: Router) { }

  ngOnInit(): void {
  }

  login() {
    this.authService.login(this.loginForm.value).subscribe(res => this.handelResponse(res))
  }

  handelResponse(res: any) {
    this.tokenService.handle(res)
    this.accountService.changeStatus(true);
    this.router.navigateByUrl("/roles/getRoles")
  }

}
