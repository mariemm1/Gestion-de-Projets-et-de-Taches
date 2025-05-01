import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Equipe } from '../../models/Equipe';
import { ChefDequipe } from '../../models/ChefDequipe';


@Injectable({
  providedIn: 'root'
})
export class EquipeService {
  
  private baseUrl = 'http://localhost:8081/equipe'; 
  private apiUrl = 'http://localhost:8081/chefDequipe';



  constructor(private http: HttpClient) { }

  getAllEquipes(): Observable<Equipe[]> {
    return this.http.get<Equipe[]>(this.baseUrl);
  }

  getEquipeById(id: number): Observable<Equipe> {
    return this.http.get<Equipe>(`${this.baseUrl}/${id}`);
  }

  // Création avec nom du chef
  // Créer une nouvelle équipe sans en-têtes personnalisés

  creerEquipe(equipe: Equipe): Observable<Equipe> {
    const headers = { 'Content-Type': 'application/json' };
    return this.http.post<Equipe>(`${this.baseUrl}/create`, JSON.stringify(equipe), { headers });
  }
  


  updateEquipe(id: number, equipe: Equipe): Observable<Equipe> {
    return this.http.put<Equipe>(`${this.baseUrl}/${id}`, equipe);
  }

  deleteEquipe(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
  getAllChefs(): Observable<ChefDequipe[]> {
      return this.http.get<ChefDequipe[]>(`${this.apiUrl}/all`);
    }
}