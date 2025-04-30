import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Equipe } from '../../models/Equipe';


@Injectable({
  providedIn: 'root'
})
export class EquipeServiceService {
  private apiUrl = 'http://localhost:8081/equipe';

  constructor(private http: HttpClient) { }

    // Récupérer toutes les équipes
    getAllEquipes(): Observable<Equipe[]> {
      return this.http.get<Equipe[]>(this.apiUrl);
    }
  
    // Récupérer une équipe par ID
    getEquipeById(id: number): Observable<Equipe> {
      return this.http.get<Equipe>(`${this.apiUrl}/${id}`);
    }
  
    // Créer une nouvelle équipe
    createEquipe(equipe: Equipe): Observable<Equipe> {
      return this.http.post<Equipe>(`${this.apiUrl}/create`, equipe);
    }
  
    // Mettre à jour une équipe existante
    updateEquipe(id: number, equipe: Equipe): Observable<Equipe> {
      return this.http.put<Equipe>(`${this.apiUrl}/${id}`, equipe);
    }
  
    // Supprimer une équipe
    deleteEquipe(id: number): Observable<void> {
      return this.http.delete<void>(`${this.apiUrl}/${id}`);
    }
}
