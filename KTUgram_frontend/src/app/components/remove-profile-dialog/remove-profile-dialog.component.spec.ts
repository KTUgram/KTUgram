import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RemoveProfileDialogComponent } from './remove-profile-dialog.component';

describe('RemoveProfileDialogComponent', () => {
  let component: RemoveProfileDialogComponent;
  let fixture: ComponentFixture<RemoveProfileDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RemoveProfileDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RemoveProfileDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
