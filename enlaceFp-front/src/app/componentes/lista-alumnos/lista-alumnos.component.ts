import { Component, EventEmitter, inject, OnInit, Output } from '@angular/core';
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

  //idAlumnoSeleccionado: number | null = null;

  @Output() activarFormulario = new EventEmitter<number>();

  emitirEvento(id:number) {
    this.activarFormulario.emit(id);
  }

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
