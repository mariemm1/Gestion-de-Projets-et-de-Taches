import { Component } from '@angular/core';
import { EquipeService } from '../../../service/equipe/equipe-service.service';
import { Equipe } from '../../../models/Equipe';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-equipe',
  imports: [CommonModule],
  templateUrl: './list-equipe.component.html',
  styleUrl: './list-equipe.component.css'
})
export class ListEquipeComponent {

  equipes: Equipe[] = [];

  constructor(private equipeService: EquipeService, private router:Router) { }

  ngOnInit(): void {
    this.loadEquipes();
  }

  loadEquipes(): void {
    this.equipeService.getAllEquipes().subscribe((data: Equipe[]) => {
      this.equipes = data;
    });
  }

  onAddEquipe(): void {
    this.router.navigate(['/add-Equipe']);
  }
}
