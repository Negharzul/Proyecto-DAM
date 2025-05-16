import { CommonModule } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { BarraNavegacionProfesorComponent } from "../../componentes/barra-navegacion-profesor/barra-navegacion-profesor.component";
import { FormularioAlumnoComponent } from "../../componentes/formulario-alumno/formulario-alumno.component";
import { ListaAlumnosComponent } from "../../componentes/lista-alumnos/lista-alumnos.component";
import { FormularioAlumnoModificacionComponent } from "../../componentes/formulario-alumno-modificacion/formulario-alumno-modificacion.component";
import { FileService } from "../../servicios/File.service";
import { HttpErrorResponse } from "@angular/common/http";
import { DatosAlumnoComponent } from "../../componentes/datos-empleo/datos-alumno.component";

@Component({
  selector: 'app-profesor',
  imports: [CommonModule, BarraNavegacionProfesorComponent, FormularioAlumnoComponent, ListaAlumnosComponent, FormularioAlumnoModificacionComponent, DatosAlumnoComponent],
  templateUrl: './mainProfesor.component.html',
  styleUrl: './mainProfesor.component.css',
})

export class mainProfesorComponent {

  mostrandoRegistro=false;
  mostrandoAdministracion=true;
  mostrandoModificacion=false;
  mostrandoAlumno=false;

  idAlumnoMostrar?:number
  idAlumnoModificar?:number
  fileService:FileService
  archivoSeleccionado?:File;

  constructor(fileService:FileService){
    this.fileService=fileService
  }

  mostrarRegistro(){
    this.todosFalso();
    this.mostrandoRegistro=true;
  }

  mostrarAdministracion(){
    this.todosFalso();
    this.mostrandoAdministracion=true;
  }

  mostrarLista(id:number){
    this.idAlumnoMostrar=id
    this.todosFalso();
    this.mostrandoAlumno=true;
    console.log('Recibiendo ID:', this.idAlumnoMostrar);
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
    this.mostrandoAlumno=false;
  }

  seleccionarArchivo(event: Event){
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.archivoSeleccionado = input.files[0];  // Asignamos correctamente el archivo a la propiedad
    }

    // Verificamos si 'archivoSeleccionado' no es undefined
    if (this.archivoSeleccionado) {
      const formData = new FormData();
      formData.append('excel', this.archivoSeleccionado);  // Usamos la propiedad correctamente

      this.fileService.enviarExcel(formData).subscribe({
        next: value => alert('Excel procesado adecuadamente'),
        error: (error: HttpErrorResponse) => {
          console.error('Full error:', error);
          console.error('Error details:', error.error);
          console.error('Status:', error.status);
        }
      });
    } else {
      alert('No has seleccionado un archivo');
    }
  }

}
