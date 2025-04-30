import { HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, Validators } from '@angular/forms';
import { Role } from '../../../../models/enum/Role';
import { ChefService } from '../../../../service/chef/chef.service';
import { EquipeServiceService } from '../../../../service/equipe/equipe-service.service';
import { Router } from '@angular/router';
import { Equipe } from '../../../../models/Equipe';
import { ChefDequipe } from '../../../../models/ChefDequipe';

@Component({
  selector: 'app-add-chef',
  imports: [],
  templateUrl: './add-chef.component.html',
  styleUrl: './add-chef.component.css'
})
export class AddChefComponent {

}
