import { Routes } from '@angular/router';
import { ListarClientesComponent } from './listar-clientes/listar-clientes.component';
import { FormComponent } from './crear-clientes/form.component';

export const routes: Routes = [
    {path: '', redirectTo: '/clientes/listarClientes', pathMatch:'full'},
    {path: 'clientes/listarClientes', component: ListarClientesComponent},
    {path: 'cliente/crearClientes', component: FormComponent}
];
