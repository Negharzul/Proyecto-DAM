import { CommonModule } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { BarraNavegacionProfesorComponent } from "../../componentes/barra-navegacion-profesor/barra-navegacion-profesor.component";
import { FormularioAlumnoComponent } from "../../componentes/formulario-alumno/formulario-alumno.component";

@Component({
  selector: 'app-profesor',
  imports: [CommonModule, BarraNavegacionProfesorComponent, FormularioAlumnoComponent],
  templateUrl: './mainProfesor.component.html',
  styleUrl: './mainProfesor.component.css',
})

export class mainProfesorComponent {





}

