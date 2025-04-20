import { Component } from '@angular/core';

@Component({
  selector: 'app-barra-navegacion',
  imports: [],
  templateUrl: './barra-navegacion-alumno.component..html',
  styleUrl: './barra-navegacion-alumno.component.css'
})
export class BarraNavegacionAlumnoComponent {

  menuAbierto = false;

  toggleMenu() {
    this.menuAbierto = !this.menuAbierto;
  }
}
