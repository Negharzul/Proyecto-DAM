import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaEmpleosProfesorComponent } from './lista-empleos-profesor.component';

describe('ListaEmpleosProfesorComponent', () => {
  let component: ListaEmpleosProfesorComponent;
  let fixture: ComponentFixture<ListaEmpleosProfesorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListaEmpleosProfesorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListaEmpleosProfesorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
