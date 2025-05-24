import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Credenciales } from '../modelos/Credenciales';
import { Observable } from 'rxjs';

const baseUrl = 'http://localhost:8081/login';

@Injectable({ providedIn: 'root' })
export class AutenticacionService {



  constructor(private http: HttpClient) {}

  login(credenciales:Credenciales): Observable<any> {
    const credentials = btoa(`${credenciales.usuario}:${credenciales.password}`);
    const headers = new HttpHeaders({
      'Authorization': `Basic ${credentials}`
    });
    return this.http.get(baseUrl,  {headers: headers, withCredentials:true},);
    //return this.http.post(baseUrl,credenciales );

  }

  logout():Observable<any>{
        const credentials = btoa(`falseaccount#123asd:falseaccount#123asd`);
    const headers = new HttpHeaders({
      'Authorization': `Basic ${credentials}`
    });
    return this.http.get(baseUrl,  {headers: headers, withCredentials:true},);
  }

  cambiarPassword(credenciales:Credenciales,password:string) :Observable<any>{
    const credentials = btoa(`${credenciales.usuario}:${credenciales.password}`);
    const headers = new HttpHeaders({
      'Authorization': `Basic ${credentials}`
    });
    const params = new HttpParams().set('password', password);
      return this.http.post(`${baseUrl}/cambiarPassword`, null, {
    headers: headers,
    params: params
  });
  }
}
