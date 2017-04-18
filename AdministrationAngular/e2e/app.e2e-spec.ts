import { RoadPricingAngularPage } from './app.po';

describe('road-pricing-angular App', function() {
  let page: RoadPricingAngularPage;

  beforeEach(() => {
    page = new RoadPricingAngularPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
