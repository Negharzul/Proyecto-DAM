import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { BarraNavegacionComponent } from "./componentes/barra-navegacion/barra-navegacion.component";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, BarraNavegacionComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'enlaceFp-front';
}
