import { CommonModule } from '@angular/common';
import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MailService } from '../../servicios/mail.Service';

@Component({
  selector: 'app-profesor-correo-alumno',
  imports: [CommonModule, FormsModule],
  templateUrl: './profesor-correo-alumno.component.html',
  styleUrl: './profesor-correo-alumno.component.css'
})
export class ProfesorCorreoAlumnoComponent implements OnChanges{


  correo = {
  para: '',
  asunto: '',
  mensaje: ''
  };

  constructor(private mailService:MailService){}

  @Input() direccion!:string;

    ngOnChanges(changes: SimpleChanges): void {
      console.log("onChanges: ",this.direccion)
    this.correo.para = changes['direccion'].currentValue;
  }

  enviarCorreo() {
  console.log('Correo enviado:', this.correo);
  this.mailService.correoAlumno(this.correo.para,this.correo.asunto,this.correo.mensaje).subscribe({
      next: value => {
        console.log("enviar correo datos: ", value)
        alert('Correo enviado');
        this.resetForm()
      },
      error: error => {console.log(error)}
    })

  }

  resetForm() {
    this.correo = {
    para: this.correo.para,
    asunto: '',
    mensaje: ''
    };

  }

}
