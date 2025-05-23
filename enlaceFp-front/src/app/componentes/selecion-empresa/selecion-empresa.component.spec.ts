import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SelecionEmpresaComponent } from './selecion-empresa.component';

describe('SelecionEmpresaComponent', () => {
  let component: SelecionEmpresaComponent;
  let fixture: ComponentFixture<SelecionEmpresaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SelecionEmpresaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SelecionEmpresaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
