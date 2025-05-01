import { Component } from '@angular/core';
import { SidbarComponent } from '../../sidbar/sidbar.component';
import { HeaderComponent } from '../../header/header.component';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-private-layout',
  standalone: true,
  imports: [CommonModule,RouterOutlet,SidbarComponent, HeaderComponent],
  templateUrl: './private-layout.component.html',
  styleUrls: ['./private-layout.component.css']
})
export class PrivateLayoutComponent {

}
