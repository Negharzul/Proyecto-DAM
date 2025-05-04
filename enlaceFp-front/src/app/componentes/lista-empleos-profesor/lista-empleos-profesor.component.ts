import { Component, EventEmitter, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Empleo } from '../../modelos/Empleo';
import { EmpleoService } from '../../servicios/Empleo.service';

@Component({
  selector: 'app-lista-empleos-profesor',
  imports: [CommonModule],
  templateUrl: './lista-empleos-profesor.component.html',
  styleUrl: './lista-empleos-profesor.component.css'
})
export class ListaEmpleosProfesorComponent {

  empleos?:Empleo[]


  constructor(private empleoService:EmpleoService){}

  @Output() activarFormulario = new EventEmitter<number>();

  emitirEvento(id:number) {
    this.activarFormulario.emit(id);
  }

  ngOnInit(): void {
    this.obtenerEmpleos();
  }


  private obtenerEmpleos(){
    this.empleoService.obtenerTodosLosEmpleos().subscribe({
      next: value => {
        this.empleos = value
        console.log(value)

      },
      error: error => {console.log(error)}
    })
  }

  borrarEmpleo(id:number){
    console.log("id recibida en metodo"+id)

    this.empleoService.deleteEmpleoById(id).subscribe({
      next: () => {
        this.obtenerEmpleos();
      },
      error: (err) => console.log(err)
    });
  }



}
