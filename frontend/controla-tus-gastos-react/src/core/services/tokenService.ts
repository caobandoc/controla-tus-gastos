//variable global
import { jwtDecode } from 'jwt-decode';


export const validToken = (): boolean => {
  const token = getToken();
  if (!token) {
    return false;
  }

  const { exp } = jwtDecode(token);
  if (exp) {
    const expirationTime = exp * 1000;
    const isExpired = Date.now() > expirationTime;
    return !isExpired;
  }

  return false;
};

export const getToken = (): string | null => {
  return localStorage.getItem('token');
};

export const setToken = (token: string): void => {
  localStorage.setItem('token', token);
}