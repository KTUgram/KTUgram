import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WarnUserDialogComponent } from './warn-user-dialog.component';

describe('WarnUserDialogComponent', () => {
  let component: WarnUserDialogComponent;
  let fixture: ComponentFixture<WarnUserDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WarnUserDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WarnUserDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
