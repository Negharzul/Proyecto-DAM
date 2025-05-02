import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Alumno } from "../modelos/Alumno";

const baseUrl = 'http://localhost:8081/file';

@Injectable({
  providedIn: 'root'
})
export class FileService {

  constructor(private http: HttpClient) { }

  obtenerAlumnoPorId(id: number): Observable<Alumno> {
    return this.http.get<Alumno>(`${baseUrl}/${id}`,{withCredentials: true});
  }

  enviarExcel(file:FormData){
    return this.http.post(baseUrl+'/excelAlumno',file,{withCredentials: true})
  }
}
