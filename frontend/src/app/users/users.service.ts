import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { User } from './user.model';

@Injectable({
  providedIn: 'root',
})
export class UsersService {
  constructor(private http: HttpClient) {}
  getUser(pgno: number, pgsize: number): Observable<User[]> {
    return this.http.get<User[]>(`http://localhost:8080/userpage`, {
      params: new HttpParams().set('pgno', pgno).set('pgsize', pgsize),
    });
  }
  getTotalUsers(): Observable<number> {
    return this.http.get<number>(`http://localhost:8080/totalusers`);
  }
  postUser(u: User) {
    return this.http.post(`http://localhost:8080/saveuser`, u, {
      headers: {},
      responseType: 'text',
    });
  }
  deleteUser(username: string) {
    return this.http.delete(`http://localhost:8080/deleteuser`, {
      responseType: 'text',
      params: new HttpParams().set('username', username),
    });
  }
  updateUser(id: number, u: User) {
    return this.http.put(`http://localhost:8080/updateuser`, u, {
      params: new HttpParams().set('id', id),
      responseType: 'text',
    });
  }
}
