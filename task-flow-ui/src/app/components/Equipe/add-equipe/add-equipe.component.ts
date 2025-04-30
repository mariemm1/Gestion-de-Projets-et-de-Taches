import { Component } from '@angular/core';
import { Equipe } from '../../../models/Equipe';
import { Router, RouterModule, RouterOutlet } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { EquipeServiceService } from '../../../service/equipe/equipe-service.service';
import {HttpClientModule} from '@angular/common/http';

@Component({
  selector: 'app-add-equipe',
  standalone: true,
  imports: [
    FormsModule,
    HttpClientModule,
    RouterModule  // <-- Ajout obligatoire
  ],
  templateUrl: './add-equipe.component.html',
  styleUrls: ['./add-equipe.component.css']
})
export class AddEquipeComponent {
  equipe: Equipe = {
    nom: '',
    description: ''
  };

  constructor(private equipeService: EquipeServiceService, private router: Router) {}

  onSubmit(): void {
    this.equipeService.createEquipe(this.equipe).subscribe({
      next: (data) => {
        console.log('Equipe créée avec succès', data);
        this.router.navigate(['/equipes']);
      },
      error: (error) => {
        console.error('Erreur lors de la création de l\'équipe', error);
      }
    });
  }
}
