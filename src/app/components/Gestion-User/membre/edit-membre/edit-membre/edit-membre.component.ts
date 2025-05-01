import { Component } from '@angular/core';
import { Membre } from '../../../../../models/Membre';
import { PostMembre } from '../../../../../models/enum/PostMembre';
import { FormBuilder, FormGroup, NgModel, ReactiveFormsModule, Validators } from '@angular/forms';
import { Role } from '../../../../../models/enum/Role';
import { Equipe } from '../../../../../models/Equipe';
import { MembreService } from '../../../../../service/membre/membre.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-edit-membre',
  imports: [CommonModule,ReactiveFormsModule ],
  templateUrl: './edit-membre.component.html',
  styleUrl: './edit-membre.component.css'
})
export class EditMembreComponent {
  membreForm: FormGroup;
  membre!: Membre;
  equipes: Equipe[] = [];
  postes = Object.values(PostMembre);  // On récupère toutes les valeurs de PostMembre
  roles = Object.values(Role);  // Liste des rôles, si nécessaire
  submitted = false;  // Déclaration de la variable "submitted"

  constructor(
    private fb: FormBuilder,
    private membreService: MembreService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {
    // Initialisation du formulaire
    this.membreForm = this.fb.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      pwd: ['', Validators.required],
      postMembre: [null, Validators.required],
      equipe: [null, Validators.required]
    });
  }

  ngOnInit(): void {
    const membreId = this.activatedRoute.snapshot.params['id'];  // Récupère l'ID du membre depuis l'URL
    this.membreService.getMembreById(membreId).subscribe(
      (data: Membre) => {
        this.membre = data;  // Récupération des données du membre
        // Mise à jour du formulaire avec les données du membre
        this.membreForm.patchValue({
          nom: this.membre.nom,
          prenom: this.membre.prenom,
          email: this.membre.email,
          pwd: this.membre.pwd,
          postMembre: this.membre.postMembre,
          equipe: this.membre.equipe?.nom
        });
      },
      (error) => {
        console.error('Erreur lors de la récupération du membre:', error);
      }
    );

    // Récupération des équipes pour afficher dans le formulaire
    this.membreService.getAllEquipes().subscribe(
      (equipes) => {
        this.equipes = equipes;
      },
      (error) => {
        console.error('Erreur lors de la récupération des équipes:', error);
      }
    );
  }

  // Accès aux champs du formulaire
  get f() {
    return this.membreForm.controls;
  }

  // Méthode pour mettre à jour le membre
  onSubmit(): void {
    this.submitted = true;  // Marquer que le formulaire a été soumis

    if (this.membreForm.invalid) {
      return;  // Si le formulaire est invalide, on ne soumet pas
    }

    const updatedMembre: Membre = {
      ...this.membreForm.value,
      role: Role.MEMBRE,  // On garde toujours le rôle de membre
      equipe: { nom: this.membreForm.value.equipe }  // L'équipe est envoyée avec son nom
    };

    // Appel au service pour mettre à jour le membre
    this.membreService.updateMembre(this.membre.id!, updatedMembre).subscribe(
      (response) => {
        console.log('Membre mis à jour avec succès', response);
        this.router.navigate(['/list-membre']);  // Redirection vers la liste des membres
      },
      (error) => {
        console.error('Erreur lors de la mise à jour du membre', error);
      }
    );
  }
}
