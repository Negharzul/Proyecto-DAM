import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmpleosProfesorComponent } from './empleos-profesor.component';

describe('EmpleosProfesorComponent', () => {
  let component: EmpleosProfesorComponent;
  let fixture: ComponentFixture<EmpleosProfesorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EmpleosProfesorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmpleosProfesorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
