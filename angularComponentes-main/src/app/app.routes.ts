import { Routes } from '@angular/router';
import { ListarClientesComponent } from './listar-clientes/listar-clientes.component';
import { FormComponent } from './crear-clientes/form.component';
import { FormActualizarComponent } from './actualizar-clientes/form-actualizar.component';

export const routes: Routes = [
    {path: '', redirectTo: '/clientes/listarClientes', pathMatch:'full'},
    {path: 'clientes/listarClientes', component: ListarClientesComponent},
    {path: 'cliente/crearClientes', component: FormComponent},
    {path: 'clientes/actualizar/:id', component: FormActualizarComponent}
];
