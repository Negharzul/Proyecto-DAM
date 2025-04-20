import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-barra-navegacion-profesor',
  imports: [
    RouterLink,
    RouterLinkActive],
  templateUrl: 'barra-navegacion-profesor.component.html',
  styleUrl: './barra-navegacion-profesor.component.css'
})
export class BarraNavegacionProfesorComponent {

  menuAbierto = false;

  toggleMenu() {
    this.menuAbierto = !this.menuAbierto;
  }
}
