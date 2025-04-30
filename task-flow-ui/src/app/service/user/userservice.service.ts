import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Membre } from '../../models/Membre';
import { ChefDequipe } from '../../models/ChefDequipe';
import { Equipe } from '../../models/Equipe';

@Injectable({
  providedIn: 'root'
})
export class UserserviceService {
  private membreUrl ='http://localhost:8081/membre/create';
  private chefUrl ='http://localhost:8081/chefDequipe/create';
  private equipeUrl = 'http://localhost:8081/equipe';

  constructor(private http:HttpClient) { }

  createMembre(membre: Membre): Observable<Membre> {
    return this.http.post<Membre>(this.membreUrl, membre);
  }

  createChef(chef: ChefDequipe): Observable<ChefDequipe> {
    return this.http.post<ChefDequipe>(this.chefUrl, chef);
  }

  getAllEquipes(): Observable<Equipe[]> {
    return this.http.get<Equipe[]>(this.equipeUrl);
  }
}
