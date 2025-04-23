import { Routes } from '@angular/router';
import { ListaAlumnosComponent } from './componentes/lista-alumnos/lista-alumnos.component';
import { mainProfesorComponent } from './pages/mainProfesor/mainProfesor.component';
import { mainAlumnoComponent } from './pages/mainAlumno/mainAlumno.component';
import { SeleccionTituloComponent } from './componentes/seleccion-titulo/seleccion-titulo.component';
import { FormularioAlumnoModificacionComponent } from './componentes/formulario-alumno-modificacion/formulario-alumno-modificacion.component';

export const routes: Routes = [

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
}





];
