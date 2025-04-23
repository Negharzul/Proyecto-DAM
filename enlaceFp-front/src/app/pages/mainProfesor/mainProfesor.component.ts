import { CommonModule } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { BarraNavegacionProfesorComponent } from "../../componentes/barra-navegacion-profesor/barra-navegacion-profesor.component";
import { FormularioAlumnoComponent } from "../../componentes/formulario-alumno/formulario-alumno.component";
import { ListaAlumnosComponent } from "../../componentes/lista-alumnos/lista-alumnos.component";
import { FormularioAlumnoModificacionComponent } from "../../componentes/formulario-alumno-modificacion/formulario-alumno-modificacion.component";

@Component({
  selector: 'app-profesor',
  imports: [CommonModule, BarraNavegacionProfesorComponent, FormularioAlumnoComponent, ListaAlumnosComponent, FormularioAlumnoModificacionComponent],
  templateUrl: './mainProfesor.component.html',
  styleUrl: './mainProfesor.component.css',
})

export class mainProfesorComponent {

  mostrandoRegistro=false;
  mostrandoAdministracion=true;
  mostrandoModificacion=false;

  idAlumnoModificar?:number

  mostrarRegistro(){
    this.todosFalso();
    this.mostrandoRegistro=true;
  }

  mostrarAdministracion(){
    this.todosFalso();
    this.mostrandoAdministracion=true;
  }

  mostrarModificacion(id:number){
    this.idAlumnoModificar=id
    this.todosFalso();
    this.mostrandoModificacion=true;
    console.log("Id de alumno:"+this.idAlumnoModificar)
  }

  todosFalso(){
    this.mostrandoAdministracion=false;
    this.mostrandoRegistro=false;
    this.mostrandoModificacion=false;
  }
}
