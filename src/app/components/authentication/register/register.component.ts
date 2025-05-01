import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,  // Déclarer le composant comme standalone
  imports: [CommonModule, RouterModule],  // Importer les modules nécessaires
  templateUrl: './register.component.html', 
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

}
