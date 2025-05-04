import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TitulosProfesorComponent } from './titulos-profesor.component';

describe('TitulosProfesorComponent', () => {
  let component: TitulosProfesorComponent;
  let fixture: ComponentFixture<TitulosProfesorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TitulosProfesorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TitulosProfesorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
