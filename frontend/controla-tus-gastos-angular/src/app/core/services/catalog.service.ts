import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Catalog} from "../models/catalog";

@Injectable({
  providedIn: 'root'
})
export class CatalogService {

  constructor(private http: HttpClient) { }

  public getCatalogAccounts(): Observable<Catalog[]> {
    return this.http.get<Catalog[]>('http://localhost:8080/api/v1/catalog/ACCOUNT');
  }

  public getCatalogCurrencies(): Observable<Catalog[]> {
    return this.http.get<Catalog[]>('http://localhost:8080/api/v1/catalog/CURRENCY');
  }
}
