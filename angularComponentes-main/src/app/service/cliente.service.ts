import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Cliente } from '../entidades/cliente';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {
  private HttpHeaders = new HttpHeaders({'Content-Type': 'application/json'});
  private urlEndPoint: string = "http://localhost:5000/api/clientes";
  constructor(private http: HttpClient) { }

  getClientes(): Observable<Cliente[]>{
    console.log("Listando los clientes desde el servicio cliente");
    let result = this.http.get<Cliente[]>(this.urlEndPoint);
    console.log(result);
    return result;
  }

  create(cliente: Cliente): Observable<Cliente>{
    console.log("Creando un cliente desde el servicio");
    return this.http.post<Cliente>(this.urlEndPoint, cliente, {headers: this.HttpHeaders});
  }
}
