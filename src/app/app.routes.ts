import { Routes } from '@angular/router';
import { DashbordComponent } from './components/dashbord/dashbord.component';
import { LoginComponent } from './components/authentication/login/login.component';
import { PrivateLayoutComponent } from './components/layouts/private-layout/private-layout.component';
import { PublicLayoutComponent } from './components/layouts/public-layout/public-layout.component';
import { RegisterComponent } from './components/authentication/register/register.component';
import { AddEquipeComponent } from './components/Equipe/add-equipe/add-equipe.component';
import { AddChefComponent } from './components/Gestion-User/chef/add-chef/add-chef.component';
import { ListChefComponent } from './components/Gestion-User/chef/list-chef/list-chef/list-chef.component';
import { EditChefComponent } from './components/Gestion-User/chef/edit-chef/edit-chef/edit-chef.component';
import { AddMembreComponent } from './components/Gestion-User/membre/add-membre/add-membre/add-membre.component';
import { ListMembreComponent } from './components/Gestion-User/membre/list-membre/list-membre/list-membre.component';
import { EditMembreComponent } from './components/Gestion-User/membre/edit-membre/edit-membre/edit-membre.component';
import { AddAdminComponent } from './components/Gestion-User/admin/add-admin/add-admin/add-admin.component';
import { ListAdminComponent } from './components/Gestion-User/admin/list-admin/list-admin/list-admin.component';
import { EditAdminComponent } from './components/Gestion-User/admin/edit-admin/edit-admin/edit-admin.component';
import { ListEquipeComponent } from './components/Equipe/list-equipe/list-equipe.component';


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
      { path: 'list-equipe', component: ListEquipeComponent },


      { path: 'add-Chef',component: AddChefComponent  },  
      { path: 'edit-chef/:id', component: EditChefComponent },
      { path: 'chefs', component: ListChefComponent },


       {path:'add-Membre' , component:AddMembreComponent},
       {path :'list-membre', component:ListMembreComponent},
       {path:'edit-membre/:id',component:EditMembreComponent},

       { path: 'add-admin', component:AddAdminComponent},
       {path :'list-admin', component:ListAdminComponent},
       {path:'edit-admin/:id',component:EditAdminComponent},





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