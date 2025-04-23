import { Component } from '@angular/core';
import { NavigationEnd, Router, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'enlaceFp-front';

  constructor(private router: Router) {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        document.body.className = ''; // Borra clases previas
        const ruta = event.urlAfterRedirects.split('/')[1];
        document.body.classList.add(`fondo-${ruta}`);
      }
    });
  }
}
