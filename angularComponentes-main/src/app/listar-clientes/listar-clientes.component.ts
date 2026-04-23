import { Component, OnInit } from '@angular/core';
import { Cliente } from '../entidades/cliente';
import { ClienteService } from '../service/cliente.service';
import { RouterLink , Router} from "@angular/router";
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import Swal from 'sweetalert2';
import { SweetAlert2Module } from '@sweetalert2/ngx-sweetalert2';

@Component({
  selector: 'app-listar-clientes',
  standalone: true,
  imports: [RouterLink, CommonModule, HttpClientModule, SweetAlert2Module],
  templateUrl: './listar-clientes.component.html',
  styleUrl: './listar-clientes.component.css'
})
export class ListarClientesComponent implements OnInit {
  clientes: Cliente[] = [];

  constructor(private clienteService: ClienteService, private router:Router) {}
  ngOnInit(): void {
    this.clienteService.getClientes().subscribe({
      next: (data) => {
        this.clientes = data;
        console.log(this.clientes);
      },
      error: (err) => {
        console.error("Error al obtener clientes", err);
      }
    });
  }

  editarCLiente(id: number){
    this.router.navigate(['/clientes/actualizar', id]);
  }

  eliminarCliente(id: number): void {
    Swal.fire({
      title: '¿Desea eliminar el cliente?',
      text: 'La eliminación no se puede revertir',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Confirmar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
        this.clienteService.deleteCliente(id).subscribe(() => {
          this.clientes = this.clientes.filter(cliente => cliente.id !== id); 
          Swal.fire(
            'Eliminado',
            'El cliente ha sido eliminado exitosamente',
            'success'
          );
        });
      } else {
        console.log("Eliminación cancelada");
      }
    });
  }
}
