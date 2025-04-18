import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Alumno } from '../modelos/Alumno';

const baseUrl = 'http://localhost/8081/alumno';

@Injectable({
  providedIn: 'root'
})
export class AlumnoServiceService {

  constructor(private http: HttpClient) { }

  obtenerAlumnoPorId(id:number): Observable<Alumno>{
    return this.http.get('${baseUrl}/{id}');
  }
}
