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

  nuevaPassword?:string
  credencialesIncorrectos=false;
  mostrandoLogin=true;
  mostrandoPassword=false;


  credenciales: Credenciales = {
    usuario: '',
    password: ''
  };

  constructor(private autenticacionService:AutenticacionService,private router: Router){}


  cambiarEstado(){
    console.log("click")
    this.mostrandoPassword=!this.mostrandoPassword;
    this.mostrandoLogin=!this.mostrandoLogin;
  }


  login(){
    this.autenticacionService.login(this.credenciales!).subscribe({
      next: data => {
        console.log('Acceso autorizado:', data)

        const role = data?.nombreRole;


        if(role === 'ROLE_Profesor'){
          this.router.navigate(['/profesor']);
        }else{
          this.router.navigate(['/alumno']);
        }

      }
      ,

      error: err => {
        console.error('Acceso denegado:', err);
        this.credenciales.password='';
        this.credenciales.usuario='';
        this.credencialesIncorrectos=true;
      }
    });
  }


  cambiarPassword(){
    this.autenticacionService.cambiarPassword(this.credenciales,this.nuevaPassword!).subscribe({
      next: data => {
        console.log('Acceso autorizado:', data)
        alert('Contraseña cambiada')
        this.cambiarEstado();

      }
      ,

      error: err => {
        console.error('Acceso denegado:', err);
        this.credenciales.password='';
        this.credenciales.usuario='';
        this.nuevaPassword='';
        alert('Datos incorrectos')
      }
    });

  }
}
