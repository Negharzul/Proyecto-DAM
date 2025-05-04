import { Component } from '@angular/core';
import { BarraNavegacionProfesorComponent } from "../../componentes/barra-navegacion-profesor/barra-navegacion-profesor.component";
import { ListaEmpleosProfesorComponent } from "../../componentes/lista-empleos-profesor/lista-empleos-profesor.component";
import { CommonModule } from '@angular/common';
import { FormularioEmpleoComponent } from "../../componentes/formulario-empleo/formulario-empleo.component";

@Component({
  selector: 'app-empleos-profesor',
  imports: [BarraNavegacionProfesorComponent, ListaEmpleosProfesorComponent, CommonModule, FormularioEmpleoComponent],
  templateUrl: './empleos-profesor.component.html',
  styleUrl: './empleos-profesor.component.css'
})
export class EmpleosProfesorComponent {

  mostrandoRegistro=false;
  mostrandoEmpleos=true;
  mostrandoModificacion=false;

  idEmpresaModificar?:number

  mostrarRegistro(){
    this.todosFalso();
    this.mostrandoRegistro=true;
  }

  mostrarAdministracion(){
    this.todosFalso();
    this.mostrandoEmpleos=true;
  }

  mostrarModificacion(id:number){
    this.idEmpresaModificar=id
    this.todosFalso();
    this.mostrandoModificacion=true;
    console.log("Id de Empresa:"+this.idEmpresaModificar)
  }

  todosFalso(){
    this.mostrandoEmpleos=false;
    this.mostrandoRegistro=false;
    this.mostrandoModificacion=false;
  }

}
