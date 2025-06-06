import { Routes } from '@angular/router';

import { mainProfesorComponent } from './pages/mainProfesor/mainProfesor.component';
import { mainAlumnoComponent } from './pages/mainAlumno/mainAlumno.component';
import { SeleccionTituloComponent } from './componentes/seleccion-titulo/seleccion-titulo.component';
import { FormularioAlumnoModificacionComponent } from './componentes/formulario-alumno-modificacion/formulario-alumno-modificacion.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { EmpresaProfesorComponent } from './pages/empresa-profesor/empresa-profesor.component';
import { EmpleosProfesorComponent } from './pages/empleos-profesor/empleos-profesor.component';
import { TitulosProfesorComponent } from './pages/titulos-profesor/titulos-profesor.component';
import { NotFoundComponent } from './pages/not-found/not-found.component';
import { AlumnoContactoComponent } from './pages/alumno-contacto/alumno-contacto.component';
import { ProfesorAdminComponent } from './pages/profesor-admin/profesor-admin.component';





export const routes: Routes = [
{ path: '', redirectTo: 'login', pathMatch: 'full' },


{
  path:'modificar',
  component: FormularioAlumnoModificacionComponent
},
{
  path:'profesor',
  component: mainProfesorComponent
},
{
  path:'alumno',
  component: mainAlumnoComponent
},
{
  path:'titulo',
  component: SeleccionTituloComponent
},
{
  path:'login',
  component:LoginPageComponent
},
{
  path: 'profesor/empresa',
  component: EmpresaProfesorComponent
},
{
  path: 'profesor/empleo',
  component: EmpleosProfesorComponent
},
{
  path: 'profesor/titulo',
  component: TitulosProfesorComponent
},
{
  path: 'alumno/contacto',
  component: AlumnoContactoComponent
},
{
  path: 'profesor/contacto',
  component: AlumnoContactoComponent
},
{
  path: 'profesor/admin',
  component: ProfesorAdminComponent
},

{ path: '**', component: NotFoundComponent }



];
