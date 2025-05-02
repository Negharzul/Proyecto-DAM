import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Titulo } from '../modelos/Titulo';

const baseUrl = 'http://localhost:8081/titulo';

@Injectable({
  providedIn: 'root'
})
export class TituloService {

  constructor(private http: HttpClient) { }

  obtenerTituloPorId(id: number): Observable<Titulo> {
    return this.http.get<Titulo>(`${baseUrl}/${id}`,{withCredentials: true});
  }

  obtenerTodosLosTitulos(): Observable<Titulo[]> {
    return this.http.get<Titulo[]>(baseUrl,{withCredentials: true});
  }

  insertarTitulo(titulo: Titulo): Observable<any> {
    return this.http.post(baseUrl, titulo,{withCredentials: true});
  }

  patchTitulo(id: number, alumno: Titulo): Observable<any> {
    return this.http.patch(`${baseUrl}/${id}`, alumno,{withCredentials: true});
  }

  deleteTituloById(id: number): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`,{withCredentials: true});
  }
}
