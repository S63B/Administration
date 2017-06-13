import { JodatimePipe } from './jodatime.pipe';

describe('JodatimePipe', () => {
  it('create an instance', () => {
    const pipe = new JodatimePipe();
    expect(pipe).toBeTruthy();
  });
});
