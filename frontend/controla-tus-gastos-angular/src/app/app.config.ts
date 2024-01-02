import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideAnimations } from '@angular/platform-browser/animations';
import {provideHttpClient, withInterceptors} from '@angular/common/http';

import { routes } from './app.routes';

//interceptors
import {tokenInterceptor} from "./core/interceptors/token.interceptor";

export const appConfig: ApplicationConfig = {
  providers: [provideRouter(routes),
    provideHttpClient(
      withInterceptors([tokenInterceptor])
    ),
    provideAnimations()]
};
