import { Component } from '@angular/core';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import { AuthService } from 'src/app/services/auth.service';
import { ProductService } from 'src/app/services/product.service';
import { MatMenuModule } from '@angular/material/menu';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [MatToolbarModule, MatIconModule, MatButtonModule, MatMenuModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent {
  user:any = null;

  constructor(public authService:AuthService, private productService: ProductService){}

  ngOnInit(){
    this.authService.authSubject.subscribe(
      (auth)=>{
        console.log("auth state", auth)
        this.user=auth.user
      }
    )
  }

  handleLogOut(){
    this.authService.logout()
  }

  selectCategory(category: string) {
    this.productService.changeCategory(category);
  }
}
