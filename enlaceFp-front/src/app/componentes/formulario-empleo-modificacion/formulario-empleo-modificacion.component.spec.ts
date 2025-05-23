import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormularioEmpleoModificacionComponent } from './formulario-empleo-modificacion.component';

describe('FormularioEmpleoModificacionComponent', () => {
  let component: FormularioEmpleoModificacionComponent;
  let fixture: ComponentFixture<FormularioEmpleoModificacionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormularioEmpleoModificacionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormularioEmpleoModificacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
