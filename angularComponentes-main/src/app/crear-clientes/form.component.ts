import { Component } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { Cliente } from '../entidades/cliente';
import { FormsModule } from '@angular/forms';
import { SweetAlert2Module } from '@sweetalert2/ngx-sweetalert2';
import Swal from 'sweetalert2';
import { ClienteService } from '../service/cliente.service';
import { response } from 'express';
import { Router } from '@angular/router';

@Component({
  selector: 'app-form',
  standalone: true,
  imports: [FormsModule,SweetAlert2Module,HttpClientModule],
  templateUrl: './form.component.html',
  styleUrl: './form.component.css'
})
export class FormComponent {
  public cliente: Cliente = new Cliente();
  public titulo: String = 'Crear Cliente';

  constructor(private clienteService: ClienteService, private router:Router) {};


  public crearCliente(){
    console.log('Creando cliente desde form.component');
    this.clienteService.create(this.cliente).subscribe(
      response =>
      {
        console.log("Cliente creado exitosamente");
        console.log(this.cliente);
        this.router.navigate(['clientes/listarClientes']);
        Swal.fire('Nuevo Cliente',`Cliente ${response.nombre} creado con exito!`, 'success');
      }
    )
  }
}
