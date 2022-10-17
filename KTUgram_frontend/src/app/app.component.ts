import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'KTUgram_frontend';

  constructor() {
    const username = localStorage.getItem('username');
    console.log('username', username);
  }
}
