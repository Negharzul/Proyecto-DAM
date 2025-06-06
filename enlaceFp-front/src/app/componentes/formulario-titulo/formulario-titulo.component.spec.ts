import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormularioTituloComponent } from './formulario-titulo.component';

describe('FormularioTituloComponent', () => {
  let component: FormularioTituloComponent;
  let fixture: ComponentFixture<FormularioTituloComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormularioTituloComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormularioTituloComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
