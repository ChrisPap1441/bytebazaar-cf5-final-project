import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { AuthComponent } from './components/auth/auth.component';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, NavbarComponent, FooterComponent, HomePageComponent, AuthComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'ByteBazaar-Angular-FrontEnd';

  user:any = null;

  constructor(public authService: AuthService){}

  ngOnInit(){
    this.authService.getUserProfile().subscribe({
      next:data=>console.log("req user ", data),
      error:error=>console.log("error", error)
    });
    this.authService.authSubject.subscribe(
      (auth)=>{
        console.log("auth state", auth)
        this.user=auth.user
      }
    )
  }
}
