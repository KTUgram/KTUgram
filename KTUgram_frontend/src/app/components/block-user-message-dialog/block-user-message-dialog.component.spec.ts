import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BlockUserMessageDialogComponent } from './block-user-message-dialog.component';

describe('BlockUserMessageDialogComponent', () => {
  let component: BlockUserMessageDialogComponent;
  let fixture: ComponentFixture<BlockUserMessageDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BlockUserMessageDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BlockUserMessageDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
