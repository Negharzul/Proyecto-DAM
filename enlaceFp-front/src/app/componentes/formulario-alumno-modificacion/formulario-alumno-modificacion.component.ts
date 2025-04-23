import { Component, inject, Input, OnInit } from '@angular/core';
import { AlumnoService } from '../../servicios/alumno.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { SeleccionTituloComponent } from "../seleccion-titulo/seleccion-titulo.component";
import { TituloService } from '../../servicios/titulo.service';
import { Titulo } from '../../modelos/Titulo';
import { Alumno } from '../../modelos/Alumno';

@Component({
  selector: 'app-formulario-modificar-alumno',
  imports: [CommonModule, FormsModule],
  templateUrl: './formulario-alumno-modificacion.component.html',
  styleUrl: './formulario-alumno-modificacion.component.css'
})
export class FormularioAlumnoModificacionComponent implements OnInit{
  alumno!:Alumno

  idAlumnoCreado?:number;

  @Input() idAlumno!: number;

  constructor(private alumnoService: AlumnoService,private tituloService:TituloService) {}

  ngOnInit(): void {
    this.obtenerAlumnoModificable();

  }

  patchAlumno(){
    this.actualizarAlumno();
  }

  obtenerAlumnoModificable(){
    this.alumnoService.obtenerAlumnoPorId(this.idAlumno).subscribe({
      next: (alumno) => {
        this.alumno=alumno;
      },
      error: (error: HttpErrorResponse) => {
        console.error('Full error:', error);
        console.error('Error details:', error.error);
        console.error('Status:', error.status);
      }
      });
  }

  actualizarAlumno(){
    this.alumnoService.patchAlumno(this.idAlumno,this.alumno).subscribe({
      next: (alumno) => {
        console.log('Alumno modificado exitosamente', alumno);
        alert('Alumno modificado con éxito!');

      },
      error: (error: HttpErrorResponse) => {
        console.error('Full error:', error);
        console.error('Error details:', error.error);
        console.error('Status:', error.status);
      }
    });
  }
}
