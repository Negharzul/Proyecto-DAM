import { Component, OnInit } from '@angular/core';
import { AutenticacionService } from '../../servicios/Autenticacion.service';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AlumnoService } from '../../servicios/alumno.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Alumno } from '../../modelos/Alumno';

@Component({
  selector: 'app-barra-navegacion',
  imports: [RouterLink,RouterLinkActive,CommonModule, FormsModule],
  templateUrl: './barra-navegacion-alumno.component..html',
  styleUrl: './barra-navegacion-alumno.component.css'
})
export class BarraNavegacionAlumnoComponent implements OnInit{

  alumno: Alumno = {} as Alumno;
  menuAbierto = false;
  recibirNotificaciones = false;

  constructor(private autenticacionService:AutenticacionService,private alumnoService:AlumnoService) {}
  ngOnInit(): void {
    this.getAlumno();
  }

  getAlumno(){
    this.alumnoService.obtenerAlumnoPropio().subscribe({
      next: value => {
        console.log("getAlumno: ",value)

        this.alumno=value;value
        this.recibirNotificaciones=value.notificaciones!

      },
      error: error => {console.log(error)}
    })

  }

  toggleMenu() {
    this.menuAbierto = !this.menuAbierto;
  }


cambiarNotificaciones() {
  if (this.recibirNotificaciones) {
    this.alumnoService.cambiarNotificaciones(true).subscribe({
      next: (notificacion) => {
        alert('✅ Notificaciones activadas');

      },
      error: (error: HttpErrorResponse) => {
        console.error('Full error:', error);
        console.error('Error details:', error.error);
        console.error('Status:', error.status);
      }
    });


  } else {
        this.alumnoService.cambiarNotificaciones(false).subscribe({
      next: (notificacion) => {
        alert('❌ Notificaciones desactivadas');
      },
      error: (error: HttpErrorResponse) => {
        console.error('Full error:', error);
        console.error('Error details:', error.error);
        console.error('Status:', error.status);
      }
    });


  }
}

}
