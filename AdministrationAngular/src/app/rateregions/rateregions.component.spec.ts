import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RateregionsComponent } from './rateregions.component';

describe('RateregionsComponent', () => {
  let component: RateregionsComponent;
  let fixture: ComponentFixture<RateregionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RateregionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RateregionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
