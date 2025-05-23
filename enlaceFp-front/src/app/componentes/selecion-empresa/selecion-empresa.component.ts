import { CommonModule } from '@angular/common';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Empresa } from '../../modelos/Empresa';
import { EmpresaService } from '../../servicios/Empresa.service';

@Component({
  selector: 'app-selecion-empresa',
  imports: [CommonModule],
  templateUrl: './selecion-empresa.component.html',
  styleUrl: './selecion-empresa.component.css'
})
export class SelecionEmpresaComponent implements OnInit{

  empresas?:Empresa[];
  empresaService :EmpresaService;


  @Output() idSeleccionado = new EventEmitter<number>();

  constructor(empresaService:EmpresaService){
    this.empresaService=empresaService;
  }

  ngOnInit(): void {
    this.obtenerTodasLasEmpresas()
  }

  obtenerTodasLasEmpresas(){
    this.empresaService.obtenerTodasLasEmpresas().subscribe({
      next: value => {
        this.empresas = value
        console.log(value)
      },
      error: error => {console.log(error)}
    })
  }

  emitirId(event: Event) {
  const selectElement = event.target as HTMLSelectElement;
  const id = Number(selectElement.value);
  console.log("seleccion-empresa emitiendo: ", id);
  this.idSeleccionado.emit(id);
  }
}
