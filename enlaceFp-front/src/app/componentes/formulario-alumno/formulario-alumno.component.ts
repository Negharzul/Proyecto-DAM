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
    dni: '',
    titulos:[] as number[]
  }

  idAlumnoCreado?:number;
  idsTitulos?:number[];


  constructor(private alumnoService: AlumnoService,private tituloService:TituloService) {}

  postAlumno(){
    this.insertarAlumno();
  }

  insertarAlumno(){
    this.alumnoService.insertarAlumno(this.alumno).subscribe({
      next: (alumno) => {
        console.log('Alumno creado exitosamente', this.alumno);
        alert('Alumno registrado con éxito!');
        this.idAlumnoCreado=alumno;
        console.log('ID del alumno creado:', this.idAlumnoCreado);
        this.insertarRelaciones();
        this.resetForm();

      },
      error: (error: HttpErrorResponse) => {
        console.error('Full error:', error);
        console.error('Error details:', error.error);
        console.error('Status:', error.status);
        console.log('datos de alumno', this.alumno);
      }
    });
  }

  insertarRelaciones(){
    console.log('Ejecutando insertarRelacion con:', this.idAlumnoCreado, this.alumno.titulos);
      this.alumnoService.insertarRelaciones(this.idAlumnoCreado!, this.alumno.titulos).subscribe({
        next: (relacion) => {
          console.log('Relación creada exitosamente', relacion);
        },
        error: (err) => {
          console.error('Error al crear la relación:', err);
          console.log("Datos: "+this.idAlumnoCreado+this.alumno.titulos)
        }
      });

  }

  resetForm() {
    this.alumno = {
      nombre: '',
      apellidos: '',
      email: '',
      telefono: '',
      direccion: '',
      dni: '',
    titulos:[]
    };

  }

  recibirIds(ids:number[]){
    this.alumno.titulos = ids
  }
}
