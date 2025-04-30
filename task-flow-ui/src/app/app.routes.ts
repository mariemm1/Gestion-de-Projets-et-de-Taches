import { Routes } from '@angular/router';
import { DashbordComponent } from './components/dashbord/dashbord.component';
import { LoginComponent } from './components/authentication/login/login.component';
import { PrivateLayoutComponent } from './components/layouts/private-layout/private-layout.component';
import { PublicLayoutComponent } from './components/layouts/public-layout/public-layout.component';
import { RegisterComponent } from './components/authentication/register/register.component';
import { AddEquipeComponent } from './components/Equipe/add-equipe/add-equipe.component';


export const routes: Routes = [
  {
    path: '',
    component: PrivateLayoutComponent,
    children: [
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
      { path: 'dashboard', component: DashbordComponent },
      {
        path: 'add-Equipe',
        component: AddEquipeComponent  
      },  
          // zed houni ay pages bel sidbar w header
    ]
  },
  {
    path: '',
    component: PublicLayoutComponent,
    children: [
      { path: 'login', component: LoginComponent },
      { path: 'register', component: RegisterComponent },     ]
  },
];