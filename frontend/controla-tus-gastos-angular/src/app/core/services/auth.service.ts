import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserLogin } from '../models/user';
import { Observable } from 'rxjs';
import { Token } from '../models/token';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  public login(login:UserLogin): Observable<Token> {
    return this.http.post<Token>('http://localhost:8080/api/v1/auth/login', login);
  }
}
