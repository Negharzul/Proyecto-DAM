import { Component } from '@angular/core';
import { AutenticacionService } from '../../servicios/Autenticacion.service';

@Component({
  selector: 'app-barra-navegacion',
  imports: [],
  templateUrl: './barra-navegacion-alumno.component..html',
  styleUrl: './barra-navegacion-alumno.component.css'
})
export class BarraNavegacionAlumnoComponent {

  menuAbierto = false;

  constructor(private autenticacionService:AutenticacionService) {}

  toggleMenu() {
    this.menuAbierto = !this.menuAbierto;
  }

  Logout(){
    this.autenticacionService.logout().subscribe({
      next: value => {
        console.log(value)

      },
      error: error => {console.log(error)}
    })
  }

}
