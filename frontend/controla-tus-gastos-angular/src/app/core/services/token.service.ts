import { Injectable } from '@angular/core';

//jwt angular
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class TokenService {
  private readonly TOKEN_KEY = 'auth_token';
  private jwtHelper = new JwtHelperService();

  setToken(token: string) {
    localStorage.setItem(this.TOKEN_KEY, token);
  }

  getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  removeToken() {
    localStorage.removeItem(this.TOKEN_KEY);
  }

  hasToken(): boolean {
    const token = this.getToken();
    if (!token) {
      return false;
    }
    if (this.jwtHelper.isTokenExpired(token)) {
      this.removeToken();
      return false;
    }
    return true;
  }

  getId(): string | null{
    const token = this.getToken();
    if (!token) {
      return null;
    }
    const tokenDecoded = this.jwtHelper.decodeToken(token);
    return tokenDecoded.id;
  }
}
