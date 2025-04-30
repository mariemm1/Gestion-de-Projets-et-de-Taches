import { Injectable } from '@angular/core';
import { ChefDequipe } from '../../models/ChefDequipe';
import { HttpClient } from '@angular/common/http';
import { Observable, switchMap } from 'rxjs';
import { Equipe } from '../../models/Equipe';
import { EquipeServiceService } from '../equipe/equipe-service.service';


@Injectable({
  providedIn: 'root'
})
export class ChefService {
  private baseUrl = 'http://localhost:8080/chefDequipe';

  constructor(private http: HttpClient, private equipeService: EquipeServiceService) {}

  getAll(): Observable<ChefDequipe[]> {
    return this.http.get<ChefDequipe[]>(`${this.baseUrl}/all`);
  }

  getById(id: number): Observable<ChefDequipe> {
    return this.http.get<ChefDequipe>(`${this.baseUrl}/${id}`);
  }

  // Créer le chef et associer à l'équipe
  create(chefDequipe: ChefDequipe, equipeData: Equipe): Observable<ChefDequipe> {
    // Créer l'équipe d'abord
    return this.equipeService.createEquipe(equipeData).pipe(
      // Une fois l'équipe créée, associer l'ID de l'équipe au chef
      switchMap((equipe) => {
        chefDequipe.equipe = equipe; // Associer l'ID de l'équipe au chef
        return this.http.post<ChefDequipe>(`${this.baseUrl}/create`, chefDequipe);
      })
    );
  }

  update(id: number, chefDequipe: ChefDequipe): Observable<ChefDequipe> {
    return this.http.put<ChefDequipe>(`${this.baseUrl}/update/${id}`, chefDequipe);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/delete/${id}`);
  }
}