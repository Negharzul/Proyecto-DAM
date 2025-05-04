import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormularioEmpleoComponent } from './formulario-empleo.component';

describe('FormularioEmpleoComponent', () => {
  let component: FormularioEmpleoComponent;
  let fixture: ComponentFixture<FormularioEmpleoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormularioEmpleoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormularioEmpleoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
