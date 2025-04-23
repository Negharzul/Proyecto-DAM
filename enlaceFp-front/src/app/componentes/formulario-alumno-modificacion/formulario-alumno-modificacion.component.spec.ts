import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormularioAlumnoModificacionComponent } from './formulario-alumno-modificacion.component';

describe('FormularioAlumnoModificacionComponent', () => {
  let component: FormularioAlumnoModificacionComponent;
  let fixture: ComponentFixture<FormularioAlumnoModificacionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormularioAlumnoModificacionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormularioAlumnoModificacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
