import { HttpInterceptorFn } from '@angular/common/http';
import {inject} from "@angular/core";
import {TokenService} from "../services/token.service";

export const tokenInterceptor: HttpInterceptorFn = (req, next) => {
  const authToken = inject(TokenService).getToken();
  if (req.url.includes('api/v1/account') && authToken) {
    const newReq = req.clone({
      headers: req.headers.set('Authorization', `Bearer ${authToken}`)
    });
    return next(newReq);
  }
  return next(req);
};
