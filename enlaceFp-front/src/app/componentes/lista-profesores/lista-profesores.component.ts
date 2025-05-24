import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Output } from '@angular/core';
import { ProfesorService } from '../../servicios/Profesor.service';
import { Profesor } from '../../modelos/Profesor';

@Component({
  selector: 'app-lista-profesores',
  imports: [CommonModule],
  templateUrl: './lista-profesores.component.html',
  styleUrl: './lista-profesores.component.css'
})
export class ListaProfesoresComponent {

  profesores?:Profesor[];

  constructor(private profesorService:ProfesorService){}


  @Output() activarFormulario = new EventEmitter<number>();


  emitirEvento(id:number){
    this.activarFormulario.emit(id);
  }

  ngOnInit(): void{
    this.obtenerProfesores();
  }

  private obtenerProfesores(){
    this.profesorService.obtenerTodosLosProfesores().subscribe({
      next: value => {
        this.profesores = value
        console.log(value)

      },
      error: error => {console.log(error)}
    })
  }


  borrarProfesor(id:number){
    console.log("id recibida en metodo"+id)

    this.profesorService.deleteById(id).subscribe({
      next: () => {
        this.obtenerProfesores();
      },
      error: (err) => console.log(err)
    });
  }

}
