import { Component, EventEmitter, Output } from '@angular/core';
import { Titulo } from '../../modelos/Titulo';
import { TituloService } from '../../servicios/titulo.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-lista-titulos-profesor',
  imports: [CommonModule],
  templateUrl: './lista-titulos-profesor.component.html',
  styleUrl: './lista-titulos-profesor.component.css'
})
export class ListaTitulosProfesorComponent {

  titulos?:Titulo[];

  constructor(private tituloService:TituloService){}

  @Output() activarFormulario = new EventEmitter<number>();

  emitirEvento(id:number) {
    this.activarFormulario.emit(id);
  }

  ngOnInit(): void {
    this.obtenerTitulos();
  }


  private obtenerTitulos(){
    this.tituloService.obtenerTodosLosTitulos().subscribe({
      next: value => {
        this.titulos = value
        console.log(value)

      },
      error: error => {console.log(error)}
    })
  }

  borrarTitulo(id:number){
    console.log("id recibida en metodo"+id)

    this.tituloService.deleteTituloById(id).subscribe({
      next: () => {
        this.obtenerTitulos();
      },
      error: (err) => console.log(err)
    });
  }

}
