import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Empresa } from '../../modelos/Empresa';
import { EmpresaService } from '../../servicios/Empresa.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-lista-empresas-profesor',
  imports: [CommonModule],
  templateUrl: './lista-empresas-profesor.component.html',
  styleUrl: './lista-empresas-profesor.component.css'
})
export class ListaEmpresasProfesorComponent implements OnInit{

  empresas?:Empresa[];

  constructor(private empresaService:EmpresaService){}

  @Output() activarFormulario = new EventEmitter<number>();

  emitirEvento(id:number) {
    this.activarFormulario.emit(id);
  }

  ngOnInit(): void {
    this.obtenerEmpresas();
  }


  private obtenerEmpresas(){
    this.empresaService.obtenerTodasLasEmpresas().subscribe({
      next: value => {
        this.empresas = value
        console.log(value)

      },
      error: error => {console.log(error)}
    })
  }

  borrarEmpresa(id:number){
    console.log("id recibida en metodo"+id)

    this.empresaService.deleteById(id).subscribe({
      next: () => {
        this.obtenerEmpresas();
      },
      error: (err) => console.log(err)
    });
  }

}
