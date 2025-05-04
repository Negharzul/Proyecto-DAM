import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Empresa } from "../modelos/Empresa";

const baseUrl = 'http://localhost:8081/Empresa';

@Injectable({
  providedIn: 'root'
})
export class EmpresaService {

  constructor(private http: HttpClient) { }

  obtenerEmpresaPorId(id: number): Observable<Empresa> {
    return this.http.get<Empresa>(`${baseUrl}/${id}`,{withCredentials: true});
  }

  obtenerTodasLasEmpresas(): Observable<Empresa[]> {
    return this.http.get<Empresa[]>(baseUrl,{withCredentials: true});
  }

  insertarEmpresa(empresa: Empresa): Observable<any> {
    return this.http.post(baseUrl+'/nuevaEmpresa', empresa,{withCredentials: true});
  }

  patchEmpresa(idEmpresa: number, empresa: Empresa): Observable<any> {
    return this.http.patch(`${baseUrl}/Modificar/${idEmpresa}`, empresa,{withCredentials: true});
  }

  deleteById(id: number): Observable<any> {
    return this.http.delete(`${baseUrl}/Borrar/${id}`,{withCredentials: true});
  }
}
