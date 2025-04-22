import { Component, inject } from '@angular/core';
import { AlumnoService } from '../../servicios/alumno.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { SeleccionTituloComponent } from "../seleccion-titulo/seleccion-titulo.component";
import { TituloService } from '../../servicios/titulo.service';
import { Titulo } from '../../modelos/Titulo';
import { Alumno } from '../../modelos/Alumno';

@Component({
  selector: 'app-formulario-alumno',
  imports: [CommonModule, FormsModule, SeleccionTituloComponent],
  templateUrl: './formulario-alumno.component.html',
  styleUrl: './formulario-alumno.component.css'
})
export class FormularioAlumnoComponent {
  alumno = {
    nombre: '',
    apellidos: '',
    email: '',
    telefono: '',
    direccion: '',
    dni: ''
  }

  idAlumnoCreado?:number;
  idTitulo?:number;


  constructor(private alumnoService: AlumnoService,private tituloService:TituloService) {}

  postAlumno(){
    this.insertarAlumno();
  }

  insertarAlumno(){
    this.alumnoService.insertarAlumno(this.alumno).subscribe({
      next: (alumno) => {
        console.log('Alumno creado exitosamente', alumno);
        alert('Alumno registrado con éxito!');
         this.idAlumnoCreado=alumno;
         console.log('ID del alumno creado:', this.idAlumnoCreado);
         this.insertarRelacion();
         this.resetForm();

      },
      error: (error: HttpErrorResponse) => {
        console.error('Full error:', error);
        console.error('Error details:', error.error);
        console.error('Status:', error.status);
      }
    });
  }

  insertarRelacion(){
    console.log('Ejecutando insertarRelacion con:', this.idAlumnoCreado, this.idTitulo);
    if (this.idAlumnoCreado && this.idTitulo) {
      this.alumnoService.insertarRelacion(this.idAlumnoCreado, this.idTitulo).subscribe({
        next: (relacion) => {
          console.log('Relación creada exitosamente', relacion);
        },
        error: (err) => {
          console.error('Error al crear la relación:', err);
        }
      });
    } else {
      console.warn('Faltan datos: idAlumno o idTitulo');
    }
  }

  resetForm() {
    this.alumno = {
      nombre: '',
      apellidos: '',
      email: '',
      telefono: '',
      direccion: '',
      dni: ''
    };

  }

  recibirId(id:number){
    this.idTitulo= id
  }
}
