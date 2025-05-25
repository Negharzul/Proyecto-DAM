import { Component, EventEmitter, inject, OnInit, output, Output } from '@angular/core';
import { AlumnoService } from '../../servicios/alumno.service';
import { Alumno } from '../../modelos/Alumno';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';



@Component({
  selector: 'app-lista-alumnos',
  imports: [CommonModule,FormsModule],
  templateUrl: './lista-alumnos.component.html',
  styleUrl: './lista-alumnos.component.css',
})
export class ListaAlumnosComponent implements OnInit{

  alumnos?:Alumno[];
  alumnoService = inject(AlumnoService);
  alumnosOrdenados: Alumno[] = [];
  sortField: string = 'id';
  reverseSort: boolean = false;

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
        this.alumnosOrdenados = [...value];
        this.sortData(this.sortField);

      },
      error: error => {console.log(error)}
    })
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

  sortData(field: string) {
    if (!this.alumnos) return;

    this.sortField = field;
    this.alumnosOrdenados = [...this.alumnos].sort((a: any, b: any) => {


      if (a[field] < b[field]) return this.reverseSort ? 1 : -1;
      if (a[field] > b[field]) return this.reverseSort ? -1 : 1;
      return 0;
    });

    this.reverseSort = !this.reverseSort;
  }

}
