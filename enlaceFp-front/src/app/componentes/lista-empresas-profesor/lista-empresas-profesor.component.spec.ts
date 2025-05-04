import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaEmpresasProfesorComponent } from './lista-empresas-profesor.component';

describe('ListaEmpresasProfesorComponent', () => {
  let component: ListaEmpresasProfesorComponent;
  let fixture: ComponentFixture<ListaEmpresasProfesorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListaEmpresasProfesorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListaEmpresasProfesorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
