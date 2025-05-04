import { Component } from '@angular/core';
import { Empresa } from '../../modelos/Empresa';
import { EmpresaService } from '../../servicios/Empresa.service';
import { HttpErrorResponse } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-formulario-empresa',
  imports: [CommonModule, FormsModule],
  templateUrl: './formulario-empresa.component.html',
  styleUrl: './formulario-empresa.component.css'
})
export class FormularioEmpresaComponent {
  empresa = {
    nombre: '',
    email: '',
    descripcion: ''
  }

  idEmpresaCreada?:number

  constructor(private empresaService:EmpresaService){}

  postEmpresa(){
    this.insertarEmpresa();
  }

  insertarEmpresa(){
    this.empresaService.insertarEmpresa(this.empresa!).subscribe({
      next: (empresa) => {
        console.log('Empresa creado exitosamente', empresa);
        alert('Empresa registrada con éxito!');
        this.idEmpresaCreada=empresa;
        console.log('ID del empresa creado:', this.idEmpresaCreada);
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
    this.empresa = {
      nombre: '',
      email: '',
      descripcion: ''
    };
  }
}
