import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Alumno } from '../modelos/Alumno';

const baseUrl = 'http://localhost:8081/alumno';

@Injectable({
  providedIn: 'root'
})
export class AlumnoService {

  constructor(private http: HttpClient) { }

  obtenerAlumnoPorId(id:number): Observable<Alumno>{
    return this.http.get<Alumno>('${baseUrl}/{id}');
  }

  obtenerTodosLosAlumnos(): Observable<Alumno[]>{
    return this.http.get<Alumno[]>(baseUrl);
  }

  insertarAlumno(alumno:Alumno):Observable<any>{
    return this.http.post(baseUrl,alumno);
  }

  patchAlumno(alumno:Alumno):Observable<any>{
    return this.http.patch(`${baseUrl}/{id}`,alumno);
  }

  deleteById(id: any): Observable<any>{
    return this.http.delete(`${baseUrl}/{id}`)
  }
}
