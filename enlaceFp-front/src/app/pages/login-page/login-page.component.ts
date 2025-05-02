import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { SeleccionTituloComponent } from '../../componentes/seleccion-titulo/seleccion-titulo.component';
import { AutenticacionService } from '../../servicios/Autenticacion.service';
import { Router } from '@angular/router';
import { Credenciales } from '../../modelos/Credenciales';

@Component({
  selector: 'app-login-page',
  imports: [CommonModule, FormsModule],
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.css'
})
export class LoginPageComponent {

  credenciales: Credenciales = {
    usuario: '',
    password: ''
  };
  role?:string

  constructor(private autenticacionService:AutenticacionService,private router: Router){}

  login(){
    this.autenticacionService.login(this.credenciales!).subscribe({
      next: data => {
        console.log('Acceso autorizado:', data)
        this.role!=data

        this.router.navigate(['/profesor']);
      }
      ,

      error: err => console.error('Acceso denegado:', err)
    });
  }
}
