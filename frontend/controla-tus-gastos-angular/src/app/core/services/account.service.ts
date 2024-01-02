import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Account} from "../models/account";

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  constructor(private http: HttpClient) { }

  public getAccounts(): Observable<Account[]> {
    return this.http.get<Account[]>('http://localhost:8080/api/v1/account');
  }
}
