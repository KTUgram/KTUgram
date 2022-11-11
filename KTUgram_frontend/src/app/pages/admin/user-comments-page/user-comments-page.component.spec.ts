import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserCommentsPageComponent } from './user-comments-page.component';

describe('UserCommentsPageComponent', () => {
  let component: UserCommentsPageComponent;
  let fixture: ComponentFixture<UserCommentsPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserCommentsPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserCommentsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
