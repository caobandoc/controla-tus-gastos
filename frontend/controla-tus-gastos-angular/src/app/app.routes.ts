import {Routes} from '@angular/router';

//components
import {AdminComponent} from './feature/admin/admin.component';
import {HomeComponent} from './feature/home/home.component';
import {LoginComponent} from './feature/auth/pages/login/login.component';
import {NotfoundComponent} from './feature/notfound/notfound.component';
import {RegisterComponent} from './feature/auth/pages/register/register.component';

//guards
import {authGuard} from './core/guards/auth.guard';
import {loginGuard} from "./core/guards/login.guard";

export const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'login',
    canActivate: [loginGuard],
    component: LoginComponent
  },
  {
    path: 'register',
    canActivate: [loginGuard],
    component: RegisterComponent
  },
  {
    path: 'app',
    component: AdminComponent,
    canActivate: [authGuard],
    loadChildren: () => import('./feature/admin/admin.routes').then(m => m.routes)
  },
  {
    path: '**',
    component: NotfoundComponent
  }
];
