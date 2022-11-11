import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterConfirmPageComponent } from './register-confirm-page.component';

describe('RegisterConfirmPageComponent', () => {
  let component: RegisterConfirmPageComponent;
  let fixture: ComponentFixture<RegisterConfirmPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterConfirmPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegisterConfirmPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
