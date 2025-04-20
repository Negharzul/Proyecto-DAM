import { Component, inject, OnInit } from '@angular/core';
import { AlumnoService } from '../../servicios/alumno.service';
import { Alumno } from '../../modelos/Alumno';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-lista-alumnos',
  imports: [CommonModule],
  templateUrl: './lista-alumnos.component.html',
  styleUrl: './lista-alumnos.component.css',
})
export class ListaAlumnosComponent implements OnInit{

  alumnos?:Alumno[];
  alumnoService = inject(AlumnoService);


  ngOnInit(): void{
    this.obtenerAlumnos();
  }

  private obtenerAlumnos(){
    this.alumnoService.obtenerTodosLosAlumnos().subscribe({
      next: value => {
        this.alumnos = value
        console.log(value)

      },
      error: error => {console.log(error)}
    })
  }

  mostrarTitulos(alumno:Alumno){
    return alumno.titulos;
  }


}
