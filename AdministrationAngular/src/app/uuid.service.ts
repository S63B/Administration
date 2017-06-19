import { Injectable } from '@angular/core';

@Injectable()
export class UUIDService {

  constructor() { }

  /**
   * Generates a UUID (universally unique identifier).
   * 
   * @static
   */
  generate(): string {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, c => {
      var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
      return v.toString(16);
    });
  }
}
