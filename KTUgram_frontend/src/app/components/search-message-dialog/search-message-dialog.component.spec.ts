import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchMessageDialogComponent } from './search-message-dialog.component';

describe('SearchMessageDialogComponent', () => {
  let component: SearchMessageDialogComponent;
  let fixture: ComponentFixture<SearchMessageDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchMessageDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchMessageDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
