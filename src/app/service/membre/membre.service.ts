import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Membre } from '../../models/Membre';
import { Equipe } from '../../models/Equipe';

@Injectable({
  providedIn: 'root'
})
export class MembreService {

  private baseUrl = 'http://localhost:8081/membre';
  private apiUrl = 'http://localhost:8081/equipe'

  constructor(private http: HttpClient) { }

  // Obtenir tous les membres
  getAllMembres(): Observable<Membre[]> {
    return this.http.get<Membre[]>(`${this.baseUrl}/all`);
  }

  // Obtenir un membre par ID
  getMembreById(id: number): Observable<Membre> {
    return this.http.get<Membre>(`${this.baseUrl}/${id}`);
  }

  // Créer un membre
  createMembre(membre: Membre): Observable<Membre> {
    return this.http.post<Membre>(`${this.baseUrl}/create`, membre);
  }

  // Mettre à jour un membre
  updateMembre(id: number, membre: Membre): Observable<Membre> {
    return this.http.put<Membre>(`${this.baseUrl}/update/${id}`, membre);
  }

  // Supprimer un membre
  deleteMembre(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/delete/${id}`);
  }
  getAllEquipes(): Observable<Equipe[]> {
    return this.http.get<Equipe[]>(`${this.apiUrl}`);  
  }
}  
