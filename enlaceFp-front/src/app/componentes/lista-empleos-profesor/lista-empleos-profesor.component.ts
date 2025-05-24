import { Component, EventEmitter, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Empleo } from '../../modelos/Empleo';
import { EmpleoService } from '../../servicios/Empleo.service';
import { MailService } from '../../servicios/mail.Service';
import { AlumnoService } from '../../servicios/alumno.service';
import { Alumno } from '../../modelos/Alumno';

@Component({
  selector: 'app-lista-empleos-profesor',
  imports: [CommonModule],
  templateUrl: './lista-empleos-profesor.component.html',
  styleUrl: './lista-empleos-profesor.component.css'
})
export class ListaEmpleosProfesorComponent {

  empleos?:Empleo[]



  constructor(private empleoService:EmpleoService,private mailService:MailService,private alumnoService:AlumnoService){}

  @Output() activarFormulario = new EventEmitter<number>();

  @Output() idEmpleo = new EventEmitter<number>();

  emitirEvento(id:number) {
    this.activarFormulario.emit(id);
    console.log("Acabo de emitir la id:",id)
  }

  ngOnInit(): void {
    this.obtenerEmpleos();
  }


  private obtenerEmpleos(){
    this.empleoService.obtenerTodosLosEmpleos().subscribe({
      next: value => {
        this.empleos = value
        console.log(value)

      },
      error: error => {console.log(error)}
    })
  }

  borrarEmpleo(id:number){
    console.log("id recibida en metodo"+id)

    this.empleoService.deleteEmpleoById(id).subscribe({
      next: () => {
        this.obtenerEmpleos();
      },
      error: (err) => console.log(err)
    });
  }

  mandarCorreo(idEmpleo:number){
    this.mailService.correoEmpresa(idEmpleo).subscribe({
      next: () => {
        alert("Correos enviados con exito");
      },
      error: (err) => console.log(err)
    });

  }

  mostrarAlumnosInteresados(idEmpleo:number){
    this.idEmpleo.emit(idEmpleo);
  }



}
