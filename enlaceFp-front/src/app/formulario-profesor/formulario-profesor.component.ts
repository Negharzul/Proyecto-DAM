import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ProfesorService } from '../servicios/Profesor.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-formulario-profesor',
  imports: [CommonModule, FormsModule],
  templateUrl: './formulario-profesor.component.html',
  styleUrl: './formulario-profesor.component.css'
})
export class FormularioProfesorComponent{

  profesor = {
    nombre: '',
    apellidos: '',
    email: '',
    dni:'',
    telefono:''
  }

  idAlumnoCreado?:number;
  idsTitulos?:number[];


  constructor(private profesorService: ProfesorService) {}

  postProfesor(){
    this.insertarProfesor();
  }

  insertarProfesor(){
    this.profesorService.insertarProfesor(this.profesor).subscribe({
      next: (profesor) => {
        console.log('Profesor registrado exitosamente', this.profesor);
        alert('Profesor registrado con éxito!');
        this.idAlumnoCreado=profesor;
        this.resetForm();

      },
      error: (error: HttpErrorResponse) => {
        console.error('Full error:', error);
        console.error('Error details:', error.error);
        console.error('Status:', error.status);
        console.log('datos de alumno', this.profesor);
      }
    });
  }


  resetForm() {
    this.profesor = {
      nombre: '',
      apellidos: '',
      email: '',
      dni:'',
      telefono:''
      };
  }


}
