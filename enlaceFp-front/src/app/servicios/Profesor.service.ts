import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Profesor } from '../modelos/Profesor';


const baseUrl = 'http://localhost:8081/Profesor';

@Injectable({
  providedIn: 'root'
})
export class ProfesorService {

  constructor(private http: HttpClient) {}

  obtenerProfesorPorId(id: number): Observable<Profesor> {
    return this.http.get<Profesor>(`${baseUrl}/${id}`,{withCredentials: true});
  }

  obtenerProfesorPropio(): Observable<Profesor>{
    return this.http.get<Profesor>(`${baseUrl}/profesorPropio`,{withCredentials: true});
  }

  obtenerTodosLosProfesores(): Observable<Profesor[]> {
    return this.http.get<Profesor[]>(baseUrl,{withCredentials: true});
  }

  insertarProfesor(profesor: Profesor): Observable<any> {
    return this.http.post(`${baseUrl}/NuevoProfesor`, profesor,{withCredentials: true});
  }

  patchProfesor(idProfesor: number, profesor: Profesor): Observable<any> {
    return this.http.patch(`${baseUrl}/Modificar/${idProfesor}`, profesor,{withCredentials: true});
   }

  deleteById(id: number): Observable<any> {
    return this.http.delete(`${baseUrl}/Borrar/${id}`,{withCredentials: true});
  }
}
