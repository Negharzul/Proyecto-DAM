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
  titulosSeleccionados?:Titulo[];

  @Output() idSeleccionados = new EventEmitter<number[]>();

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

/*
    emitirIds(ids: number[]) {
    this.idSeleccionados.emit(ids!);
  }
    */


  emitirIds(event: Event): void {
  const select = event.target as HTMLSelectElement;

  // Obtener los IDs seleccionados
  const selectedIds = Array.from(select.selectedOptions).map(
    option => Number(option.value)
  );

  this.idSeleccionados.emit(selectedIds)

}

}
