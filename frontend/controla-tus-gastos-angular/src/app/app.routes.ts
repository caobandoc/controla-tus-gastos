import { Routes } from '@angular/router';
import { HomeComponent } from './feature/home/pages/home.component';
import { LoginComponent } from './feature/login/login.component';
import { AdminComponent } from './feature/admin/admin.component';
import { NotfoundComponent } from './feature/notfound/notfound.component';

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
    path: 'admin',
    component: AdminComponent
    //loadChildren: () => import('./feature/admin/admin.routes').then(m => m.routes)
  },
  {
    path: '**',
    component: NotfoundComponent
  }
];
