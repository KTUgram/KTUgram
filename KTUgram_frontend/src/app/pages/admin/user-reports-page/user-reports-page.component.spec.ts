import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserReportsPageComponent } from './user-reports-page.component';

describe('UserPostsPageComponent', () => {
  let component: UserReportsPageComponent;
  let fixture: ComponentFixture<UserReportsPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserReportsPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserReportsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
