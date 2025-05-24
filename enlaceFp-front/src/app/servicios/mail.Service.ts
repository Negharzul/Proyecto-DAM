import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const baseUrl = 'http://localhost:8081/Correo';

@Injectable({ providedIn: 'root' })
export class MailService {



  constructor(private http: HttpClient) {}


    correoAlumno(destinatario: string,tema:string,contenido:string): Observable<any> {
        const params = new HttpParams()
        .set('destinatario', destinatario)
        .set('tema', tema)
        .set('contenido', contenido);
      return this.http.post(`${baseUrl}/enviar`, params,{withCredentials: true});
    }

      correoEmpresa(idEmpleo:number): Observable<any> {
      return this.http.post(`${baseUrl}/contactarEmpresa/${idEmpleo}`, null,{withCredentials: true});
    }
}
