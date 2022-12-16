import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from './auth.service';
import { Router } from '@angular/router';
import { ɵparseCookieValue } from '@angular/common';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  username!: String;
  password!: String;
  result!: String;
  constructor(
    private router: Router,
    private auth: AuthenticationService,
    private cookie: CookieService
  ) {}
  ngOnInit(): void {
    //  this.http
    //    .get('http://localhost:8080/csrftoken', {
    //      observe: 'response',
    //      responseType: 'json',
    //    })
    //    .subscribe((res) => {
    //      this.token = res.headers.get('X-XSRF-TOKEN');
    //    });
  }
  //   ngOnInit(): void {
  //     //  this.form = this.formBuilder.group({
  //     //    username: [''],
  //     //    password: [''],
  //     //  });
  //     throw new Error('Method not implemented.');
  //   }
  handleLogin(): void {
    //  console.log(this.username + ' ' + this.password);
    this.auth
      .authenticationService(this.username, this.password)
      .subscribe((result) => {
        this.router.navigate(['/users']);
        //   console.log(this.cookie.getAll());
        //   this.router.navigateByUrl('/users').then(
        //     (nav) => {
        //       console.log('nav ' + nav);
        //     },
        //     (err) => {
        //       console.log('err ' + err);
        //     }
        //   );
        //   console.log(result);
      });
  }
  //  this.http.post('http://localhost:8080/login', this.form);
}
