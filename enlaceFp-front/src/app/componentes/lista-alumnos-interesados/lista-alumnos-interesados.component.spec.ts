import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaAlumnosInteresadosComponent } from './lista-alumnos-interesados.component';

describe('ListaAlumnosInteresadosComponent', () => {
  let component: ListaAlumnosInteresadosComponent;
  let fixture: ComponentFixture<ListaAlumnosInteresadosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListaAlumnosInteresadosComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListaAlumnosInteresadosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
