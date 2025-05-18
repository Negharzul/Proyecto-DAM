import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormularioEmpresaModificacionComponent } from './formulario-empresa-modificacion.component';

describe('FormularioEmpresaModificacionComponent', () => {
  let component: FormularioEmpresaModificacionComponent;
  let fixture: ComponentFixture<FormularioEmpresaModificacionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormularioEmpresaModificacionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormularioEmpresaModificacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
