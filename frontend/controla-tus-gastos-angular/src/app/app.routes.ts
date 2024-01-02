import { Routes } from '@angular/router';

//components
import { HomeComponent } from './feature/home/home.component';
import { LoginComponent } from './feature/login/login.component';
import { AdminComponent } from './feature/admin/admin.component';
import { NotfoundComponent } from './feature/notfound/notfound.component';

//guards
import { authGuard } from './core/guards/auth.guard';
import { RegisterComponent } from './feature/register/register.component';
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
