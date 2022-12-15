import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FoundMessageDialogComponent } from './found-message-dialog.component';

describe('FoundMessageDialogComponent', () => {
  let component: FoundMessageDialogComponent;
  let fixture: ComponentFixture<FoundMessageDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FoundMessageDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FoundMessageDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
