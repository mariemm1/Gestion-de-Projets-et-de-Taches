import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Admin } from '../../../../../models/Admin';
import { AdminService } from '../../../../../service/admin/admin.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-edit-admin',
  imports: [ReactiveFormsModule,CommonModule],
  templateUrl: './edit-admin.component.html',
  styleUrl: './edit-admin.component.css'
})
export class EditAdminComponent {
  adminForm: FormGroup;
  admin!: Admin;
  submitted = false;

  constructor(
    private fb: FormBuilder,
    private adminService: AdminService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.adminForm = this.fb.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      pwd: ['', Validators.required],
      role: [{ value: 'ADMIN', disabled: true }]  // Fixé comme dans le backend
    });
  }

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    this.adminService.getAdminById(id).subscribe(
      (data: Admin) => {
        this.admin = data;
        this.adminForm.patchValue({
          nom: this.admin.nom,
          prenom: this.admin.prenom,
          email: this.admin.email,
          pwd: this.admin.pwd,
          role: this.admin.role
        });
      },
      (error) => {
        console.error('Erreur lors de la récupération de l’admin', error);
      }
    );
  }

  get f() {
    return this.adminForm.controls;
  }

  onSubmit(): void {
    this.submitted = true;

    if (this.adminForm.invalid) {
      return;
    }

    const updatedAdmin: Admin = {
      ...this.adminForm.getRawValue(),  // Inclut les champs désactivés (comme role)
      id: this.admin.id
    };

    this.adminService.updateAdmin(this.admin.id!, updatedAdmin).subscribe(
      () => {
        console.log('Admin mis à jour avec succès');
        this.router.navigate(['/list-admin']);  // Redirige vers la liste des admins
      },
      (error) => {
        console.error('Erreur lors de la mise à jour de l’admin', error);
      }
    );
  }
}
