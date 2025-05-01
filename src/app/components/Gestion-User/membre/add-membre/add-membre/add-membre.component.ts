import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { PostMembre } from '../../../../../models/enum/PostMembre';
import { Role } from '../../../../../models/enum/Role';
import { MembreService } from '../../../../../service/membre/membre.service';
import { Router } from '@angular/router';
import { Membre } from '../../../../../models/Membre';
import { CommonModule } from '@angular/common';
import { Equipe } from '../../../../../models/Equipe';

@Component({
  selector: 'app-add-membre',
  imports: [ReactiveFormsModule,CommonModule],
  templateUrl: './add-membre.component.html',
  styleUrl: './add-membre.component.css'
})
export class AddMembreComponent {

  membreForm: FormGroup;  // Formulaire
  equipes: Equipe[] = [];  // Liste des équipes
  postes = Object.values(PostMembre);  // Liste des postes
  roles = Object.values(Role);  // Liste des rôles
  submitted = false;  // Flag pour suivre si le formulaire a été soumis

  constructor(
    private fb: FormBuilder,
    private membreService: MembreService,
    private router: Router
  ) {
    // Création du formulaire avec validations
    this.membreForm = this.fb.group({
      nom: ['', Validators.required],  // Nom requis
      prenom: ['', Validators.required],  // Prénom requis
      email: ['', [Validators.required, Validators.email]],  // Email requis et format valide
      pwd: ['', Validators.required],  // Mot de passe requis
      postMembre: [null],  // Poste non sélectionné par défaut
      equipe: [null, Validators.required]  // L'équipe est requise
    });
  }

  ngOnInit(): void {
    // Récupération des équipes
    this.membreService.getAllEquipes().subscribe(
      (equipes) => {
        this.equipes = equipes;
      },
      (error) => {
        console.error('Erreur lors de la récupération des équipes:', error);
        alert('Impossible de charger les équipes. Veuillez vérifier le serveur.');
      }
    );
  }

  // Accès aux champs du formulaire pour simplifier la validation
  get f() {
    return this.membreForm.controls;
  }

  // Méthode pour soumettre le formulaire
  onSubmit(): void {
    this.submitted = true;

    // Si le formulaire est invalide, nous n'arrêtont pas l'exécution mais affichons simplement les erreurs
    // Cette partie a été supprimée, car nous voulons laisser soumettre même avec des erreurs


  
  // Création de l'objet membre avec l'équipe (en envoyant le nom de l'équipe)
  const membre: Membre = {
    ...this.membreForm.value,
    role: Role.MEMBRE,  // Rôle toujours membre
    equipe: { nom: this.membreForm.value.equipe } // Envoi du nom de l'équipe
  };

    // Appel au service pour créer le membre
    this.membreService.createMembre(membre).subscribe(response => {
      console.log('Membre ajouté avec succès', response);
      this.router.navigate(['/list-membre']);  // Rediriger vers la liste des membres
    }, error => {
      console.error('Erreur lors de l\'ajout du membre', error);
    });
    
  } 
  
}
