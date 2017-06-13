import { Component } from '@angular/core';
import { TranslateService } from "ng2-translate";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app works!';

  constructor(private translate: TranslateService) {
    this.initializeTranslateService();
  }

  /**
   * Initializes the NG2-Translate translate service.
   */
  initializeTranslateService() {
    this.translate.addLangs(['en', 'nl']);
    this.translate.setDefaultLang('en');
    this.translate.use('en');
  }
}
