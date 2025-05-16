import { CommonModule } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { BarraNavegacionAlumnoComponent } from "../../componentes/barra-navegacion-alumno/barra-navegacion-alumno.component.";
import { DatosAlumnoComponent } from "../../componentes/datos-empleo/datos-alumno.component";

@Component({
  selector: 'app-alumno',
  imports: [CommonModule, BarraNavegacionAlumnoComponent, DatosAlumnoComponent],
  templateUrl: './mainAlumno.component.html',
  styleUrl: './mainAlumno.component.css',
})

export class mainAlumnoComponent {


}

