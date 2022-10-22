import { TestBed } from '@angular/core/testing';

import { AdminGuardsGuard } from './admin-guards.guard';

describe('AdminGuardsGuard', () => {
  let guard: AdminGuardsGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(AdminGuardsGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
