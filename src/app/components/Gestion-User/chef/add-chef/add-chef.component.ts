import { HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Role } from '../../../../models/enum/Role';
import { ChefService } from '../../../../service/chef/chef.service';
import { Router } from '@angular/router';
import { ChefDequipe } from '../../../../models/ChefDequipe';

@Component({
  selector: 'app-add-chef',
  imports: [FormsModule,HttpClientModule,ReactiveFormsModule ],
  templateUrl: './add-chef.component.html',
  styleUrl: './add-chef.component.css'
})
export class AddChefComponent {
  
  chefForm: FormGroup;
  submitted = false;

  constructor(
    private fb: FormBuilder,
    private chefService: ChefService,
    private router: Router
  ) {
    this.chefForm = this.fb.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      pwd: ['', Validators.required]
    });
  }

  onSubmit(): void {
    this.submitted = true;
    if (this.chefForm.invalid) {
      return;
    }

    const chef: ChefDequipe = {
      ...this.chefForm.value,
      role: Role.CHEF_EQUIPE
    };

    this.chefService.createChef(chef).subscribe(() => {
      alert('Chef d\'équipe ajouté avec succès');
      this.router.navigate(['/chefs']); // redirection après ajout, adapte le chemin
    });
  }
}
