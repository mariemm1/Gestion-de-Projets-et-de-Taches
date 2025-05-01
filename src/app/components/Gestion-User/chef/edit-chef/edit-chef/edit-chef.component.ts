import { Component } from '@angular/core';
import { ChefDequipe } from '../../../../../models/ChefDequipe';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ChefService } from '../../../../../service/chef/chef.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Role } from '../../../../../models/enum/Role';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-edit-chef',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule,FormsModule ],
    templateUrl: './edit-chef.component.html',
  styleUrl: './edit-chef.component.css'
})
export class EditChefComponent {
  chef: ChefDequipe = {
    nom: '',
    prenom: '',
    email: '',
    pwd: '',
    role: Role.CHEF_EQUIPE, 
  };

  constructor(
    private chefService: ChefService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const chefId = this.activatedRoute.snapshot.params['id'];
    this.chefService.getChefById(chefId).subscribe(
      (data: ChefDequipe) => {
        this.chef = { ...data, role: Role.CHEF_EQUIPE }; // rôle forcé
      },
      (error) => {
        console.error('Erreur lors de la récupération du chef:', error);
      }
    );
  }

  editChef(): void {
    this.chef.role = Role.CHEF_EQUIPE; // s’assurer qu’il n’a pas changé
    this.chefService.updateChef(this.chef.id!, this.chef).subscribe(
      (response) => {
        console.log("Chef mis à jour:", response);
        this.router.navigate(['/chefs']);
      },
      (error) => {
        console.error('Erreur lors de la mise à jour du chef:', error);
      }
    );
  
  }
}
