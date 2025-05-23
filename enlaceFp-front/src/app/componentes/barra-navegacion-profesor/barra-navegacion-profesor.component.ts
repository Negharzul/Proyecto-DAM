import { Component, OnInit } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { ProfesorService } from '../../servicios/Profesor.service';
import { Profesor } from '../../modelos/Profesor';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-barra-navegacion-profesor',
  imports: [
    RouterLink,
    RouterLinkActive,
    CommonModule,
    FormsModule],
  templateUrl: 'barra-navegacion-profesor.component.html',
  styleUrl: './barra-navegacion-profesor.component.css'
})
export class BarraNavegacionProfesorComponent implements OnInit{

  profesor:Profesor = {} as Profesor;

  constructor(private profesorService:ProfesorService){}

  ngOnInit(): void {
    this.obtenerProfesorPropio();
  }

  obtenerProfesorPropio(){
    this.profesorService.obtenerProfesorPropio().subscribe({
      next: value => {
        console.log("getProfesor: ",value)

        this.profesor=value;

      },
      error: error => {console.log(error)}
    })
  }

  menuAbierto = false;

  toggleMenu() {
    this.menuAbierto = !this.menuAbierto;
  }
}
