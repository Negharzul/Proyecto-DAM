import { Component } from '@angular/core';
import { BarraNavegacionProfesorComponent } from "../../componentes/barra-navegacion-profesor/barra-navegacion-profesor.component";
import { CommonModule } from '@angular/common';
import { ListaTitulosProfesorComponent } from "../../componentes/lista-titulos-profesor/lista-titulos-profesor.component";
import { FormularioTituloComponent } from "../../componentes/formulario-titulo/formulario-titulo.component";

@Component({
  selector: 'app-titulos-profesor',
  imports: [BarraNavegacionProfesorComponent, CommonModule, ListaTitulosProfesorComponent, FormularioTituloComponent],
  templateUrl: './titulos-profesor.component.html',
  styleUrl: './titulos-profesor.component.css'
})
export class TitulosProfesorComponent {

  mostrandoRegistro=false;
  mostrandoTitulos=true;
  mostrandoModificacion=false;

  idEmpresaModificar?:number

  mostrarRegistro(){
    this.todosFalso();
    this.mostrandoRegistro=true;
  }

  mostrarAdministracion(){
    this.todosFalso();
    this.mostrandoTitulos=true;
  }

  mostrarModificacion(id:number){
    this.idEmpresaModificar=id
    this.todosFalso();
    this.mostrandoModificacion=true;
    console.log("Id de Empresa:"+this.idEmpresaModificar)
  }

  todosFalso(){
    this.mostrandoTitulos=false;
    this.mostrandoRegistro=false;
    this.mostrandoModificacion=false;
  }

}
