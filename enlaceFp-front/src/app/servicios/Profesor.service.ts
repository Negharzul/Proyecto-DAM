import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Profesor } from '../modelos/Profesor';


const baseUrl = 'http://localhost:8081/Profesor';

@Injectable({
  providedIn: 'root'
})
export class ProfesorService {

  constructor(private http: HttpClient) { }


  obtenerProfesorPropio(): Observable<Profesor>{
    return this.http.get<Profesor>(`${baseUrl}/profesorPropio`,{withCredentials: true});
  }
}
