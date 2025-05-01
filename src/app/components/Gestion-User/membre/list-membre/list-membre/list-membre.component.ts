import { Component } from '@angular/core';
import { Membre } from '../../../../../models/Membre';
import { MembreService } from '../../../../../service/membre/membre.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-list-membre',
  imports: [CommonModule],
  templateUrl: './list-membre.component.html',
  styleUrl: './list-membre.component.css'
})
export class ListMembreComponent {
membre: Membre[] = [];
  isLoading = true;

  constructor(private membreService: MembreService,private router: Router) {}

  ngOnInit(): void {
    this.loadMembre();
  }

  loadMembre(): void {
    this.membreService.getAllMembres().subscribe({
      next: (data) => {
        this.membre = data;
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Erreur lors du chargement des membre:', err);
        this.isLoading = false;
      }
    });
  }

  onAddMembre(): void {
    this.router.navigate(['/add-Membre']);
  }

  deleteMembre(id: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer ce membre ?')) {
      console.log('ID du membre à supprimer:', id);
      this.membreService.deleteMembre(id).subscribe({
        next: () => {
          console.log('Membre supprimé avec succès');
          this.membre = this.membre.filter(m => m.id !== id);
          this.router.navigate(['/list-membre']); // Redirection
        },
        error: (err) => {
          console.error('Erreur lors de la suppression du membre:', err);
          alert('Une erreur est survenue lors de la suppression du membre.');
        }
      });
    }
  }

  goToEdit(id: number): void {
    this.router.navigate(['edit-membre', id]);
  }
  
}
