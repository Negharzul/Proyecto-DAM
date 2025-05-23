import { Component } from '@angular/core';
import { EmpleoService } from '../../servicios/Empleo.service';
import { HttpErrorResponse } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SelecionEmpresaComponent } from "../selecion-empresa/selecion-empresa.component";
import { SeleccionTituloComponent } from "../seleccion-titulo/seleccion-titulo.component";

@Component({
  selector: 'app-formulario-empleo',
  imports: [CommonModule, FormsModule, SelecionEmpresaComponent, SeleccionTituloComponent],
  templateUrl: './formulario-empleo.component.html',
  styleUrl: './formulario-empleo.component.css'
})
export class FormularioEmpleoComponent {

  empleo = {

    nombreEmpleo: '',
    descripcion:'',
    empresaId: 0,
    empresaNombre:'' ,
    titulacionesExigidas: [] as number[]


  }

  Titulos?:number[]

  idEmpleoCreado?:number
  idEmpresa?:number

  constructor(private empleoService:EmpleoService){}

  postEmpleo(){
    this.insertarEmpleo();
  }

  insertarEmpleo(){
    this.empleoService.insertarEmpleo(this.empleo!).subscribe({
      next: (empleo) => {
        console.log('Empleo creado exitosamente', empleo);
        console.log('Datos: ', this.empleo);
        alert('Empleo registrado con éxito!');
        this.idEmpleoCreado=empleo.id;
        this.Titulos=empleo.titulacionesExigidas;
        console.log('ID del Empleo creado:', this.idEmpleoCreado);
        this.insertarRelacionesTitulos()
        this.resetForm();

      },
      error: (error: HttpErrorResponse) => {
        console.log('Datos: ', this.empleo);
        console.error('Full error:', error);
        console.error('Error details:', error.error);
        console.error('Status:', error.status);
      }
    });
  }

  insertarRelacionesTitulos(){
    console.log('Ejecutando insertar Relacion con:', this.idEmpleoCreado, this.empleo.titulacionesExigidas)
      this.empleoService.insertarRelacionesTitulaciones(this.idEmpleoCreado!, this.empleo.titulacionesExigidas).subscribe({
        next: (relacion) => {
          console.log('Relación creada exitosamente', relacion);
        },
        error: (err) => {
          console.error('Error al crear la relación:', err);
        }
      });

  }


  resetForm() {
    this.empleo = {
      nombreEmpleo: '',
      descripcion:'',
      empresaId: 0,
      empresaNombre:'',
      titulacionesExigidas: this.empleo.titulacionesExigidas
    };
  }
  recibirIdEmpresa(id :number){
    this.empleo.empresaId= id
  }

  recibirIds(ids:number[]){
    this.empleo.titulacionesExigidas = ids
  }
}
