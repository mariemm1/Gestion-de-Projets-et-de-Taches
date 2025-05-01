import { Component } from '@angular/core';
import { ChefDequipe } from '../../../../../models/ChefDequipe';
import { ChefService } from '../../../../../service/chef/chef.service';
import {  HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-chef',
  standalone: true,
  imports: [HttpClientModule, CommonModule,  ],
  
  templateUrl: './list-chef.component.html',
  styleUrl: './list-chef.component.css'
})

export class ListChefComponent {
  chefs: ChefDequipe[] = [];
  isLoading = true;

  constructor(private chefService: ChefService,private router: Router) {}

  ngOnInit(): void {
    this.loadChefs();
  }

  loadChefs(): void {
    this.chefService.getAllChefs().subscribe({
      next: (data) => {
        this.chefs = data;
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Erreur lors du chargement des chefs:', err);
        this.isLoading = false;
      }
    });
  }
  deleteChef(id: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer ce chef ?')) {
      console.log('ID du chef à supprimer:', id);  // Vérifie que l'ID est correct
      this.chefService.deleteChef(id).subscribe({
        next: () => {
          console.log('Chef supprimé avec succès');
          // Mettre à jour l'affichage pour ne plus afficher le chef supprimé
          this.chefs = this.chefs.filter(c => c.id !== id);
        },
        error: (err) => {
          console.error('Erreur lors de la suppression du chef:', err);
          alert('Une erreur est survenue lors de la suppression du chef.');
        }
      });
    }
  }
  
  goToEdit(id: number): void {
    this.router.navigate(['edit-chef', id]);
  }

}
