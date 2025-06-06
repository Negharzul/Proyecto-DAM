import { Component } from '@angular/core';
import { BarraNavegacionProfesorComponent } from "../../componentes/barra-navegacion-profesor/barra-navegacion-profesor.component";
import { ListaEmpleosProfesorComponent } from "../../componentes/lista-empleos-profesor/lista-empleos-profesor.component";
import { CommonModule } from '@angular/common';
import { FormularioEmpleoComponent } from "../../componentes/formulario-empleo/formulario-empleo.component";
import { FormularioEmpleoModificacionComponent } from "../../componentes/formulario-empleo-modificacion/formulario-empleo-modificacion.component";
import { ListaAlumnosInteresadosComponent } from "../../componentes/lista-alumnos-interesados/lista-alumnos-interesados.component";

@Component({
  selector: 'app-empleos-profesor',
  imports: [BarraNavegacionProfesorComponent, ListaEmpleosProfesorComponent, CommonModule, FormularioEmpleoComponent, FormularioEmpleoModificacionComponent, ListaAlumnosInteresadosComponent],
  templateUrl: './empleos-profesor.component.html',
  styleUrl: './empleos-profesor.component.css'
})
export class EmpleosProfesorComponent {

  mostrandoRegistro=false;
  mostrandoEmpleos=true;
  mostrandoModificacion=false;
  mostrandoAlumnoInteresados=false;


  idEmpleoModificar?:number;
  idEmpleoBuscarAlumnos?:number;

  mostrarRegistro(){
    this.todosFalso();
    this.mostrandoRegistro=true;
  }

  mostrarAdministracion(){
    this.todosFalso();
    this.mostrandoEmpleos=true;
  }

  mostrarModificacion(id:number){
    console.log("click");
    this.idEmpleoModificar=id
    this.todosFalso();
    this.mostrandoModificacion=true;
    console.log("Id de Empresa:"+this.idEmpleoModificar)
  }

  todosFalso(){
    this.mostrandoEmpleos=false;
    this.mostrandoRegistro=false;
    this.mostrandoModificacion=false;
    this.mostrandoAlumnoInteresados=false;
  }

  mostrarAlumnosInteresados(idEmpleo:number){
    this.idEmpleoBuscarAlumnos=idEmpleo;
    this.todosFalso();
    this.mostrandoAlumnoInteresados=true;
  }

}
