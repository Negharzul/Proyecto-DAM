import { Component, Input, OnInit } from '@angular/core';
import { AlumnoService } from '../../servicios/alumno.service';
import { Alumno } from '../../modelos/Alumno';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-lista-alumnos-interesados',
  imports: [CommonModule],
  templateUrl: './lista-alumnos-interesados.component.html',
  styleUrl: './lista-alumnos-interesados.component.css'
})
export class ListaAlumnosInteresadosComponent implements OnInit{

  alumnosInteresados?:Alumno[];

  @Input() idEmpleo!:number;

  constructor(private alumnoService:AlumnoService){}

  ngOnInit(): void {
    this.obtenerAlumnosInteresados();
  }

  obtenerAlumnosInteresados(){
    this.alumnoService.obtenerAlumnosPorIdEmpleo(this.idEmpleo).subscribe({
      next: value => {
        console.log("Alumnos interesados: ",value)
        this.alumnosInteresados=value
      },
      error: (err) => console.log(err)
    })
  }

}
