import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'jodatime'
})
export class JodatimePipe implements PipeTransform {

  transform(jodatime: any): String {
    return `${jodatime.dayOfMonth}-${jodatime.monthOfYear}-${jodatime.year}`;
  }

}
