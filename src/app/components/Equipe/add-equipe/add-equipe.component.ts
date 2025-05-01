import { Component } from '@angular/core';
import { Equipe } from '../../../models/Equipe';
import { Router, RouterModule, RouterOutlet } from '@angular/router';
import { FormsModule, NgModel,  } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { EquipeService } from '../../../service/equipe/equipe-service.service';
import { ChefDequipe } from '../../../models/ChefDequipe';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-add-equipe',
  standalone: true,
  imports: [
    FormsModule,
    HttpClientModule,
    RouterModule ,
    CommonModule 
  ],
  templateUrl: './add-equipe.component.html',
  styleUrls: ['./add-equipe.component.css']
})
export class AddEquipeComponent {
  equipe: Equipe = {
    nom: '',
    description: '',
  };

  chefs: ChefDequipe[] = [];

  constructor(
    private equipeService: EquipeService,
    private chefService: EquipeService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.chefService.getAllChefs().subscribe(data => {
      this.chefs = data;
    });
  }
  onSubmit(): void {
    this.equipeService.creerEquipe(this.equipe).subscribe(() => {
      alert("Équipe créée avec succès !");
      this.router.navigate(['/equipes']); // redirige après création
    });
  }
  }
