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
    return this.http.get<Alumno>(`${baseUrl}/${id}`,{withCredentials: true});
  }

  obtenerAlumnoPropio(): Observable<Alumno>{
    return this.http.get<Alumno>(`${baseUrl}/alumnoPropio`,{withCredentials: true});
  }

  obtenerAlumnosPorIdEmpleo(idEmpleo:number): Observable<Alumno[]>{
    return this.http.get<Alumno[]>(`${baseUrl}/alumnosInteresados/${idEmpleo}`,{withCredentials: true});
  }

  obtenerTodosLosAlumnos(): Observable<Alumno[]> {
    return this.http.get<Alumno[]>(baseUrl,{withCredentials: true});
  }

  insertarAlumno(alumno: Alumno): Observable<any> {
    return this.http.post(baseUrl, alumno,{withCredentials: true});
  }

  insertarRelaciones(idAlumno:number,idsTitulos:number[]): Observable<any> {
    return this.http.post(`${baseUrl}/titulo/${idAlumno}`,idsTitulos,{withCredentials: true});
  }
//"/titulo/{idAlumno]/{idTitulacion}"


  patchAlumno(idAlumno: number, alumno: Alumno): Observable<any> {
    return this.http.patch(`${baseUrl}/Modificar/${idAlumno}`, alumno,{withCredentials: true});
  }

  cambiarNotificaciones(cambiar:boolean): Observable<any> {
    return this.http.patch(`${baseUrl}/notificaciones?activar=${cambiar}`, null,{withCredentials: true});
  }

  deleteById(id: number): Observable<any> {
    return this.http.delete(`${baseUrl}/Borrar/${id}`,{withCredentials: true});
  }
}
