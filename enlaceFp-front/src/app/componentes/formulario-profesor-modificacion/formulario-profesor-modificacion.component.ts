import { CommonModule } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Profesor } from '../../modelos/Profesor';
import { ProfesorService } from '../../servicios/Profesor.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-formulario-profesor-modificacion',
  imports: [CommonModule, FormsModule],
  templateUrl: './formulario-profesor-modificacion.component.html',
  styleUrl: './formulario-profesor-modificacion.component.css'
})
export class FormularioProfesorModificacionComponent implements OnInit{

    profesor!:Profesor

  idProfesorCreado?:number;

  @Input() idProfesor!: number;

  constructor(private profesorService: ProfesorService) {}

  ngOnInit(): void {
    this.obtenerProfesorModificable();

  }

  patchProfesor(){
    this.actualizarProfesor();
  }

  obtenerProfesorModificable(){
    this.profesorService.obtenerProfesorPorId(this.idProfesor).subscribe({
      next: (profesor) => {
        this.profesor=profesor;
      },
      error: (error: HttpErrorResponse) => {
        console.error('Full error:', error);
        console.error('Error details:', error.error);
        console.error('Status:', error.status);
      }
      });
  }

  actualizarProfesor(){
    console.log("Datos varios: ",this.idProfesor,this.profesor)
    this.profesorService.patchProfesor(this.idProfesor,this.profesor).subscribe({
      next: (alumno) => {
        console.log('Profesor modificado exitosamente', alumno);
        alert('Profesor modificado con éxito!');

      },
      error: (error: HttpErrorResponse) => {
        console.error('Full error:', error);
        console.error('Error details:', error.error);
        console.error('Status:', error.status);
      }
    });
  }

}
