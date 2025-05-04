import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaTitulosProfesorComponent } from './lista-titulos-profesor.component';

describe('ListaTitulosProfesorComponent', () => {
  let component: ListaTitulosProfesorComponent;
  let fixture: ComponentFixture<ListaTitulosProfesorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListaTitulosProfesorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListaTitulosProfesorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
