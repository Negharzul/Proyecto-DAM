import { Component } from '@angular/core';
import { EmpleoService } from '../../servicios/Empleo.service';
import { HttpErrorResponse } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-formulario-empleo',
  imports: [CommonModule, FormsModule],
  templateUrl: './formulario-empleo.component.html',
  styleUrl: './formulario-empleo.component.css'
})
export class FormularioEmpleoComponent {

  empleo = {
    nombreEmpleo: '',
    empresa: '',
  }


  idEmpleoCreado?:number

  constructor(private empleoService:EmpleoService){}

  postEmpresa(){
    this.insertarEmpleo();
  }

  insertarEmpleo(){
    this.empleoService.insertarEmpleo(this.empleo!).subscribe({
      next: (empleo) => {
        console.log('Empresa creado exitosamente', empleo);
        alert('Empleo registrada con éxito!');
        this.idEmpleoCreado=empleo;
        console.log('ID del Empleo creado:', this.idEmpleoCreado);
        this.resetForm();

      },
      error: (error: HttpErrorResponse) => {
        console.error('Full error:', error);
        console.error('Error details:', error.error);
        console.error('Status:', error.status);
      }
    });
  }

  resetForm() {
    this.empleo = {
      nombreEmpleo: '',
      empresa: ''
    };
  }
}
