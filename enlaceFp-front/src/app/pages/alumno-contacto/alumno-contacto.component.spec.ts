import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlumnoContactoComponent } from './alumno-contacto.component';

describe('AlumnoContactoComponent', () => {
  let component: AlumnoContactoComponent;
  let fixture: ComponentFixture<AlumnoContactoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AlumnoContactoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AlumnoContactoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
