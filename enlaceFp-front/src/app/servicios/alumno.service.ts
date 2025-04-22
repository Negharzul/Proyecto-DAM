import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Alumno } from '../modelos/Alumno';

const baseUrl = 'http://localhost:8081/alumno';

@Injectable({
  providedIn: 'root'
})
export class AlumnoService {

  constructor(private http: HttpClient) { }

  obtenerAlumnoPorId(id: number): Observable<Alumno> {
    return this.http.get<Alumno>(`${baseUrl}/${id}`);
  }

  obtenerTodosLosAlumnos(): Observable<Alumno[]> {
    return this.http.get<Alumno[]>(baseUrl);
  }

  insertarAlumno(alumno: Alumno): Observable<any> {
    return this.http.post(baseUrl, alumno);
  }

  insertarRelacion(idAlumno:number,idTitulo:number): Observable<any> {
    return this.http.post(`${baseUrl}/titulo/${idAlumno}/${idTitulo}`,null);
  }
//"/titulo/{idAlumno]/{idTitulacion}"


  patchAlumno(id: number, alumno: Alumno): Observable<any> {
    return this.http.patch(`${baseUrl}/${id}`, alumno);
  }

  deleteById(id: number): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }
}
