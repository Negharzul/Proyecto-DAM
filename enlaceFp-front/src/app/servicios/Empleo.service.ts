import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Empleo } from "../modelos/Empleo";

const baseUrl = 'http://localhost:8081/empleo';

@Injectable({
  providedIn: 'root'
})
export class EmpleoService {

  constructor(private http: HttpClient) { }

  obtenerEmpleoPorId(id: number): Observable<Empleo> {
    return this.http.get<Empleo>(`${baseUrl}/${id}`,{withCredentials: true});
  }

  obtenerTodosLosEmpleos(): Observable<Empleo[]> {
    return this.http.get<Empleo[]>(baseUrl,{withCredentials: true});
  }

  obtenerTodosLosEmpleosDeAlumno(id: number):Observable<Empleo[]>{
    return this.http.get<Empleo[]>(`${baseUrl}/empleosAlumno/${id}`,{withCredentials: true});
  }

  obtenerPosiblesEmpleosAlumno():Observable<Empleo[]>{
    return this.http.get<Empleo[]>(`${baseUrl}/posiblesEmpleos`,{withCredentials: true});
  }

  insertarEmpleo(empleo: Empleo): Observable<any> {
    return this.http.post(baseUrl+'/NuevoEmpleo', empleo,{withCredentials: true});
  }

  elegirEmpleo(idEmpleo:number,interesado:boolean): Observable<any> {
    return this.http.post(`${baseUrl}/interesadoEmpleo/${idEmpleo}?interesado=${interesado}`,{},{withCredentials: true});
  }

  empresaParaEmpleo(idEmpleo:number,idEmpresa:number):  Observable<any>{
    return this.http.post(`${baseUrl}/tituloEmpleo/${idEmpleo}`,null,{withCredentials: true})
  }

    insertarRelacionesTitulaciones(idEmpleo:number,idsTitulos:number[]): Observable<any> {
      return this.http.post(`${baseUrl}/titulosEmpleo/${idEmpleo}`,idsTitulos,{withCredentials: true});
    }

  patchTitulo(id: number, empleo: Empleo): Observable<any> {
    return this.http.patch(`${baseUrl}/${id}`, empleo,{withCredentials: true});
  }

  deleteEmpleoById(id: number): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`,{withCredentials: true});
  }

}
