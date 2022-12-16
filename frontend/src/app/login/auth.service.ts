import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  public username!: String;
  public password!: String;
  constructor(private http: HttpClient) {}
  authenticationService(username: String, password: String) {
    return this.http.get(`http://localhost:8080`, {
      headers: {
        authorization: this.createBasicAuthToken(username, password),
      },
      responseType: 'json',
      observe: 'response',
    });
  }
  createBasicAuthToken(username: String, password: String) {
    return 'Basic ' + window.btoa(username + ':' + password);
  }
  registerSuccessLogin(username: string) {
    sessionStorage.setItem('authenticatedUser', username);
  }
}
