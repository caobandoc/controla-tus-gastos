import { Routes } from '@angular/router';

//components
import { HomeComponent } from './feature/home/home.component';
import { LoginComponent } from './feature/login/login.component';
import { AdminComponent } from './feature/admin/admin.component';
import { NotfoundComponent } from './feature/notfound/notfound.component';

//guards
import { authGuard } from './core/guards/auth.guard';
import { RegisterComponent } from './feature/register/register.component';

export const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'login',
    component: LoginComponent
    //loadChildren: () => import('./feature/login/login.routes').then(m => m.routes)
  },
  {
    path: 'register',
    component: RegisterComponent
    //loadChildren: () => import('./feature/register/register.routes').then(m => m.routes)
  },
  {
    path: 'app',
    component: AdminComponent,
    canActivate: [authGuard],
    //loadChildren: () => import('./feature/admin/admin.routes').then(m => m.routes)
  },
  {
    path: '**',
    component: NotfoundComponent
  }
];
