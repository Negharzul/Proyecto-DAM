import { Component, EventEmitter, inject, OnInit, output, Output } from '@angular/core';
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

  @Output() mostrarAlumno = new EventEmitter<number>();

  @Output() activarFormulario = new EventEmitter<number>();

  @Output() emailCorreo =new EventEmitter<string>();


  emitirEvento(id:number){
    this.activarFormulario.emit(id);
  }

  emitirEventoDatos(id:number) {
    this.mostrarAlumno.emit(id);
    console.log('Emitiendo ID:', id);
  }

  mandarCorreo(direccion:string){
    this.emailCorreo.emit(direccion)
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

  borrarAlumno(id:number){
    console.log("id recibida en metodo"+id)

    this.alumnoService.deleteById(id).subscribe({
      next: () => {
        this.obtenerAlumnos();
      },
      error: (err) => console.log(err)
    });
  }

}
