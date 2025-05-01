import { Component } from '@angular/core';
import { Admin } from '../../../../../models/Admin';
import { Role } from '../../../../../models/enum/Role';
import { AdminService } from '../../../../../service/admin/admin.service';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-admin',
  imports: [FormsModule],
  templateUrl: './add-admin.component.html',
  styleUrl: './add-admin.component.css'
})
export class AddAdminComponent {

  admin: Admin = {
    nom: '',
    prenom: '',
    email: '',
    pwd: '',
    role: Role.ADMIN // rôle par défaut
  };

  constructor(private adminService: AdminService,    private router: Router) {}

  onSubmit(): void {
    this.adminService.createAdmin(this.admin).subscribe({
      next: (data) => {
        console.log('Admin ajouté avec succès', data);
        alert("Admin ajouté !");
        this.router.navigate(['/list-admin']); // <== redirige ici
      },
      error: (err) => {
        console.error('Erreur lors de l\'ajout d\'admin', err);
      }
    });
  }
}
