import { Component } from '@angular/core';
import { TituloService } from '../../servicios/titulo.service';
import { HttpErrorResponse } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-formulario-titulo',
  imports: [CommonModule, FormsModule],
  templateUrl: './formulario-titulo.component.html',
  styleUrl: './formulario-titulo.component.css'
})

export class FormularioTituloComponent {

  titulo = {
    nombre: '',
  }

  idTituloCreado?:number

  constructor(private tituloService:TituloService){}

  postEmpresa(){
    this.insertarEmpresa();
  }

  insertarEmpresa(){
    this.tituloService.insertarTitulo(this.titulo!).subscribe({
      next: (titulo) => {
        console.log('Titulo creado exitosamente', titulo);
        alert('Titulo registrada con éxito!');
        this.idTituloCreado=titulo;
        console.log('ID del Titulo creado:', this.idTituloCreado);
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
    this.titulo = {
      nombre: '',
    };
  }
}
