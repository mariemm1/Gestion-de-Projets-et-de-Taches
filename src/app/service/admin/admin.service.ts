import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Admin } from '../../models/Admin';


@Injectable({
  providedIn: 'root'
})
export class AdminService {
  private baseUrl = 'http://localhost:8081/admin';

  constructor(private http: HttpClient) { }

  getAllAdmins(): Observable<Admin[]> {
    return this.http.get<Admin[]>(`${this.baseUrl}/all`);
  }

  getAdminById(id: number): Observable<Admin> {
    return this.http.get<Admin>(`${this.baseUrl}/${id}`);
  }

  createAdmin(admin: Admin): Observable<Admin> {
    return this.http.post<Admin>(`${this.baseUrl}/create`, admin);
  }

  updateAdmin(id: number, admin: Admin): Observable<Admin> {
    return this.http.put<Admin>(`${this.baseUrl}/update/${id}`, admin);
  }

  deleteAdmin(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/delete/${id}`);
  }
}