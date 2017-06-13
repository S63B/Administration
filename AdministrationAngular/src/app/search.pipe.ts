import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'search'
})
export class SearchPipe implements PipeTransform {
  transform(value: any, input: string) {
    if (input) {
      input = input.toLowerCase();
      return value.filter(function (el: any) {
        var rowString;
          Object.keys(el).forEach(function (key, index) {
            switch (key) {
              case 'date':
                rowString += `${el[key].dayOfMonth}-${el[key].monthOfYear}-${el[key].year}`;
                break;
              case 'totalPrice':
                rowString += el[key];
                break;
              case 'paymentStatus':
                rowString += el[key] == 1 ? 'ja' : 'nee' ;
                break;
            }
          });
        return rowString.toLowerCase().indexOf(input) > -1;
      })
    }
    return value;
  }
}
