import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommentReportsPageComponent } from './commentReports-page.component';

describe('CommentsPageComponent', () => {
  let component: CommentReportsPageComponent;
  let fixture: ComponentFixture<CommentReportsPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CommentReportsPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CommentReportsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
