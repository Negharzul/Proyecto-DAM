import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SeleccionTituloComponent } from './seleccion-titulo.component';

describe('SeleccionTituloComponent', () => {
  let component: SeleccionTituloComponent;
  let fixture: ComponentFixture<SeleccionTituloComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SeleccionTituloComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SeleccionTituloComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
