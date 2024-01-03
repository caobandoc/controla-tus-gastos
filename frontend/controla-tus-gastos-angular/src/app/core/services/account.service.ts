import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Account, EUAccount} from "../models/account";

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  constructor(private http: HttpClient) { }

  public getAccounts(): Observable<Account[]> {
    return this.http.get<Account[]>('http://localhost:8080/api/v1/account');
  }

  createAccount(account: EUAccount): Observable<Account> {
    return this.http.post<Account>('http://localhost:8080/api/v1/account', account);
  }

  updateAccount(account: EUAccount): Observable<Account> {
    return this.http.put<Account>('http://localhost:8080/api/v1/account', account);
  }
}
