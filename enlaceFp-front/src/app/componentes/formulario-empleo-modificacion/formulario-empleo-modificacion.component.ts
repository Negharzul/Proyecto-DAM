import { CommonModule } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { SeleccionTituloComponent } from '../seleccion-titulo/seleccion-titulo.component';
import { Empleo } from '../../modelos/Empleo';
import { EmpleoService } from '../../servicios/Empleo.service';
import { TituloService } from '../../servicios/titulo.service';
import { HttpErrorResponse } from '@angular/common/http';
import { SelecionEmpresaComponent } from "../selecion-empresa/selecion-empresa.component";

@Component({
  selector: 'app-formulario-empleo-modificacion',
  imports: [CommonModule, FormsModule, SeleccionTituloComponent, SelecionEmpresaComponent],
  templateUrl: './formulario-empleo-modificacion.component.html',
  styleUrl: './formulario-empleo-modificacion.component.css'
})
export class FormularioEmpleoModificacionComponent implements OnInit{

  empleo!:Empleo

  idEmpleoCreado?:number;
  idEmpresa?:number;

  @Input() idEmpleo!: number;

  constructor(private empleoService: EmpleoService,private tituloService:TituloService) {}

  ngOnInit(): void {
    this.obtenerEmpleoModificable();

  }

  patchEmpleo(){
    this.actualizarEmpleo();
  }

  obtenerEmpleoModificable(){
    console.log("Datos varios obtenerEmpleo: ",this.idEmpleo,this.empleo)
    this.empleoService.obtenerEmpleoPorId(this.idEmpleo).subscribe({
      next: (empleo) => {
        this.empleo=empleo;
      },
      error: (error: HttpErrorResponse) => {
        console.error('Full error:', error);
        console.error('Error details:', error.error);
        console.error('Status:', error.status);
      }
      });
  }

  actualizarEmpleo(){
    console.log("Datos varios: patch",this.idEmpleo,this.empleo)
    this.empleoService.patchEmpleo(this.idEmpleo,this.empleo).subscribe({
      next: (empleo) => {
        console.log('Empleo modificado exitosamente', empleo);
        alert('Empleo modificado con éxito!');

      },
      error: (error: HttpErrorResponse) => {
        console.error('Full error:', error);
        console.error('Error details:', error.error);
        console.error('Status:', error.status);
      }
    });
  }

    recibirIds(ids:number[]){
    this.empleo.titulacionesExigidas = ids
  }

  recibirIdEmpresa(id:number){
    this.empleo.empresaId=id;
  }
}
