import { Component, OnInit } from '@angular/core';
import { Cliente } from '../entidades/cliente';
import { ClienteService } from '../service/cliente.service';
import { RouterLink } from "@angular/router";

@Component({
  selector: 'app-listar-clientes',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './listar-clientes.component.html',
  styleUrl: './listar-clientes.component.css'
})
export class ListarClientesComponent implements OnInit {
  clientes: Cliente[] = [];

  constructor(private clienteService: ClienteService) {}
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
}
