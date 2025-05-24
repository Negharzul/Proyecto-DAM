import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormularioProfesorModificacionComponent } from './formulario-profesor-modificacion.component';

describe('FormularioProfesorModificacionComponent', () => {
  let component: FormularioProfesorModificacionComponent;
  let fixture: ComponentFixture<FormularioProfesorModificacionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormularioProfesorModificacionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormularioProfesorModificacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
