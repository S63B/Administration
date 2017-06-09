import { TestBed, inject } from '@angular/core/testing';

import { RateRegionService } from './rate-region.service';

describe('RateRegionService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [RateRegionService]
    });
  });

  it('should be created', inject([RateRegionService], (service: RateRegionService) => {
    expect(service).toBeTruthy();
  }));
});
