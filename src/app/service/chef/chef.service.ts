import { Injectable } from '@angular/core';
import { ChefDequipe } from '../../models/ChefDequipe';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ChefService {
 
  private baseUrl = 'http://localhost:8081/chefDequipe';
  
  constructor(private http: HttpClient) { }

  getAllChefs(): Observable<ChefDequipe[]> {
    return this.http.get<ChefDequipe[]>(`${this.baseUrl}/all`);
  }

  getChefById(id: number): Observable<ChefDequipe> {
    return this.http.get<ChefDequipe>(`${this.baseUrl}/${id}`);
  }

  createChef(chef: ChefDequipe): Observable<ChefDequipe> {
    return this.http.post<ChefDequipe>(`${this.baseUrl}/create`, chef);
  }

  updateChef(id: number, chef: ChefDequipe): Observable<ChefDequipe> {
    return this.http.put<ChefDequipe>(`${this.baseUrl}/update/${id}`, chef);
  }

  deleteChef(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/delete/${id}`);
  }
}