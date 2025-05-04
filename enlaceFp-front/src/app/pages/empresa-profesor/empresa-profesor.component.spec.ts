import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmpresaProfesorComponent } from './empresa-profesor.component';

describe('EmpresaProfesorComponent', () => {
  let component: EmpresaProfesorComponent;
  let fixture: ComponentFixture<EmpresaProfesorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EmpresaProfesorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmpresaProfesorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
