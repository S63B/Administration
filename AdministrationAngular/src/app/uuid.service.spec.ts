import { TestBed, inject } from '@angular/core/testing';

import { UUIDService } from './uuid.service';

describe('UUIDService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [UUIDService]
    });
  });

  it('should be created', inject([UUIDService], (service: UUIDService) => {
    expect(service).toBeTruthy();
  }));
});
