import { Component } from '@angular/core';
import { BarraNavegacionProfesorComponent } from '../../componentes/barra-navegacion-profesor/barra-navegacion-profesor.component';
import { ListaEmpresasProfesorComponent } from "../../componentes/lista-empresas-profesor/lista-empresas-profesor.component";
import { FormularioEmpresaComponent } from "../../componentes/formulario-empresa/formulario-empresa.component";
import { CommonModule } from '@angular/common';
import { FormularioEmpresaModificacionComponent } from "../../componentes/formulario-empresa-modificacion/formulario-empresa-modificacion.component";


@Component({
  selector: 'app-empresa-profesor',
  imports: [BarraNavegacionProfesorComponent, CommonModule, ListaEmpresasProfesorComponent, FormularioEmpresaComponent, FormularioEmpresaModificacionComponent],
  templateUrl: './empresa-profesor.component.html',
  styleUrl: './empresa-profesor.component.css'
})
export class EmpresaProfesorComponent {

  mostrandoRegistro=false;
  mostrandoAdministracion=true;
  mostrandoModificacion=false;

  idEmpresaModificar?:number

  mostrarRegistro(){
    this.todosFalso();
    this.mostrandoRegistro=true;
  }

  mostrarAdministracion(){
    this.todosFalso();
    this.mostrandoAdministracion=true;
  }

  mostrarModificacion(id:number){
    this.idEmpresaModificar=id
    this.todosFalso();
    this.mostrandoModificacion=true;
    console.log("Id de Empresa:"+this.idEmpresaModificar)
  }

  todosFalso(){
    this.mostrandoAdministracion=false;
    this.mostrandoRegistro=false;
    this.mostrandoModificacion=false;
  }

}
