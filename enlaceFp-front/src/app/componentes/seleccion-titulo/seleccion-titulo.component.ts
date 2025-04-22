import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Titulo } from '../../modelos/Titulo';
import { TituloService } from '../../servicios/titulo.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-seleccion-titulo',
  imports: [CommonModule],
  templateUrl: './seleccion-titulo.component.html',
  styleUrl: './seleccion-titulo.component.css'
})
export class SeleccionTituloComponent implements OnInit{

  titulos?:Titulo[];
  tituloService :TituloService;
  tituloSeleccionado?:Titulo;

  @Output() idSeleccionado = new EventEmitter<number>();

  constructor(tituloService:TituloService){
    this.tituloService=tituloService;
  }

  ngOnInit(): void {
    this.obtenerTodosLosTitulos()
  }

  obtenerTodosLosTitulos(){
    this.tituloService.obtenerTodosLosTitulos().subscribe({
      next: value => {
        this.titulos = value
        console.log(value)
      },
      error: error => {console.log(error)}
    })
  }

  emitirId(id: string) {
    const idNumber = Number(id);
    if (!isNaN(idNumber)) {
      this.idSeleccionado.emit(idNumber);
    }
  }
}
