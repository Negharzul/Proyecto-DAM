import { CommonModule } from '@angular/common';
import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { FormsModule } from '@angular/forms';

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

  @Input() direccion!:string;

    ngOnChanges(changes: SimpleChanges): void {
      console.log("onChanges: ",this.direccion)
    this.correo.para = changes['direccion'].currentValue;
  }

  enviarCorreo() {
  console.log('Correo enviado:', this.correo);

}

}
