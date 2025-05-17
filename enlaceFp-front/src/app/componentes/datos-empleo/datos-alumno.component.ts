import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Empleo } from '../../modelos/Empleo';
import { EmpleoService } from '../../servicios/Empleo.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-datos-alumno',
  imports: [CommonModule],
  templateUrl: './datos-alumno.component.html',
  styleUrl: './datos-alumno.component.css'
})
export class DatosAlumnoComponent implements OnInit{


  empleos?:Empleo[]

  constructor(private empleoService:EmpleoService){}


  ngOnInit(): void {

    this.obtenerEmpleos();
    console.log(this.empleos)
  }
  private obtenerEmpleos(){
  this.empleoService.obtenerPosiblesEmpleosAlumno().subscribe({
    next: value => {
      this.empleos = value
      console.log(value)

    },
      error: (error: HttpErrorResponse) => {
        console.error('Full error:', error);
        console.error('Error details:', error.error);
        console.error('Status:', error.status);
      }
    })
  }

  interesEmpleo(idEmpleo:number,interesado:boolean){
    this.empleoService.elegirEmpleo(idEmpleo,interesado).subscribe({
    next: value => {
      console.log(value)
      this.obtenerEmpleos()

    },
          error: (error: HttpErrorResponse) => {
        console.error('Full error:', error);
        console.error('Error details:', error.error);
        console.error('Status:', error.status);
      }
    })
  }




}
