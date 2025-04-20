import { Component, inject } from '@angular/core';
import { AlumnoService } from '../../servicios/alumno.service';
import { Alumno } from '../../modelos/Alumno';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-formulario-alumno',
  imports: [CommonModule, FormsModule],
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

  constructor(private alumnoService: AlumnoService) {}

  postAlumno(){
    console.log('Enviando alumno:', this.alumno);

    this.alumnoService.insertarAlumno(this.alumno).subscribe({
      next: (response) => {
        console.log('Alumno creado exitosamente', response);
        alert('Alumno registrado con éxito!');
        this.resetForm();
      },
      error: (error: HttpErrorResponse) => {
        console.error('Full error:', error);
        console.error('Error details:', error.error);
        console.error('Status:', error.status);
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
      dni: ''
    };
  }
}
