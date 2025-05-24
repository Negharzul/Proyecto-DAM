import { Component } from '@angular/core';
import { BarraNavegacionProfesorComponent } from "../../componentes/barra-navegacion-profesor/barra-navegacion-profesor.component";
import { CommonModule } from '@angular/common';
import { ListaProfesoresComponent } from "../../componentes/lista-profesores/lista-profesores.component";
import { FormularioProfesorComponent } from "../../componentes/formulario-profesor/formulario-profesor.component";
import { FormularioProfesorModificacionComponent } from "../../componentes/formulario-profesor-modificacion/formulario-profesor-modificacion.component";

@Component({
  selector: 'app-profesor-admin',
  imports: [BarraNavegacionProfesorComponent, CommonModule, ListaProfesoresComponent, FormularioProfesorComponent, FormularioProfesorModificacionComponent],
  templateUrl: './profesor-admin.component.html',
  styleUrl: './profesor-admin.component.css'
})
export class ProfesorAdminComponent {

  mostrandoProfesores=true;
  mostrandoRegistro=false;
  mostrandoModificacion=false;

  idProfesorModificar?:number;

  mostrarRegistro(){
    this.todosFalso();
    this.mostrandoRegistro=true;
  }

  mostrarAdministracion(){
    this.todosFalso();
    this.mostrandoProfesores=true;
  }

  mostrarModificacion(idProfesor:number){
    this.todosFalso();
    this.mostrandoModificacion=true;
    this.idProfesorModificar=idProfesor;
  }


  todosFalso(){
    this.mostrandoProfesores=false;
    this.mostrandoRegistro=false;
    this.mostrandoModificacion=false;
  }
}
