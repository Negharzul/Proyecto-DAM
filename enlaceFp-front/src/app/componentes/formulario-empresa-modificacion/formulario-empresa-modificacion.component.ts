import { Component, Input, OnInit } from '@angular/core';
import { Empresa } from '../../modelos/Empresa';
import { EmpresaService } from '../../servicios/Empresa.service';
import { HttpErrorResponse } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-formulario-empresa-modificacion',
  imports: [CommonModule, FormsModule],
  templateUrl: './formulario-empresa-modificacion.component.html',
  styleUrl: './formulario-empresa-modificacion.component.css'
})
export class FormularioEmpresaModificacionComponent implements OnInit{

    empresa!:Empresa
    idEmpresaCreada?:number

    @Input() idEmpresa!: number;

    constructor(private empresaService:EmpresaService){}

  ngOnInit(): void {
    this.obtenerEmpresaModificable();
  }

  patchEmpresa(){
    this.actualizarEmpresa();

  }

  obtenerEmpresaModificable(){
    this.empresaService.obtenerEmpresaPorId(this.idEmpresa).subscribe({
      next: (empresa) => {
        this.empresa=empresa;
        console.log(empresa)
      },
      error: (error: HttpErrorResponse) => {
        console.error('Full error:', error);
        console.error('Error details:', error.error);
        console.error('Status:', error.status);
      }
      });
  }

  actualizarEmpresa(){
    this.empresaService.patchEmpresa(this.idEmpresa,this.empresa).subscribe({
      next: (empresa) => {
        console.log('Empresa modificada exitosamente', empresa);
        alert('Empresa modificada con éxito!');

      },
      error: (error: HttpErrorResponse) => {
        console.error('Full error:', error);
        console.error('Error details:', error.error);
        console.error('Status:', error.status);
      }
    });
  }

}
