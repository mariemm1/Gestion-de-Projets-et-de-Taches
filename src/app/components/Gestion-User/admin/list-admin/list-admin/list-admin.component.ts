import { Component } from '@angular/core';
import { Admin } from '../../../../../models/Admin';
import { AdminService } from '../../../../../service/admin/admin.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-admin',
  imports: [CommonModule],
  templateUrl: './list-admin.component.html',
  styleUrl: './list-admin.component.css'
})
export class ListAdminComponent {
  admins: Admin[] = [];

  constructor(private adminService: AdminService,private router: Router) {}

  ngOnInit(): void {
    this.adminService.getAllAdmins().subscribe({
      next: (data) => {
        this.admins = data;
      },
      error: (err) => {
        console.error('Erreur lors du chargement des administrateurs :', err);
      }
    });
  }

  onAddAdmin(): void {
    this.router.navigate(['/add-admin']);
  }

  goToEdit(id: number): void {
    this.router.navigate(['edit-admin', id]);
  }
  

  supprimerAdmin(id?: number): void {
    if (id === undefined) return; // Ne rien faire si l'id est indéfini
  
    if (confirm('Êtes-vous sûr de vouloir supprimer cet admin ?')) {
      this.adminService.deleteAdmin(id).subscribe(() => {
        this.admins = this.admins.filter(admin => admin.id !== id);
      });
    }
  }
  
}
