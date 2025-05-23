import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfesorCorreoAlumnoComponent } from './profesor-correo-alumno.component';

describe('ProfesorCorreoAlumnoComponent', () => {
  let component: ProfesorCorreoAlumnoComponent;
  let fixture: ComponentFixture<ProfesorCorreoAlumnoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProfesorCorreoAlumnoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfesorCorreoAlumnoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
