import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { SidbarComponent } from "./components/sidbar/sidbar.component";
import { HeaderComponent } from "./components/header/header.component";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Gestion-Taches-FrontEnd';
}
