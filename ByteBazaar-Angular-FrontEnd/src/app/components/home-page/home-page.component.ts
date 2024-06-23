// import { Component } from '@angular/core';
// import { ProductCardComponent } from '../product-card/product-card.component';
// import { MatIconModule } from '@angular/material/icon';
// import { MatButtonModule } from '@angular/material/button';
// import { MatDialog } from '@angular/material/dialog';
// import { ProductService } from 'src/app/services/product.service';
// import { CreateProductFormComponent } from '../create-product-form/create-product-form.component';
// import { AuthService } from 'src/app/services/auth.service';
// import {MatSidenavModule} from '@angular/material/sidenav';

// @Component({
//   selector: 'app-home-page',
//   standalone: true,
//   imports: [ProductCardComponent, MatIconModule, MatButtonModule, MatSidenavModule],
//   templateUrl: './home-page.component.html',
//   styleUrl: './home-page.component.scss'
// })
// export class HomePageComponent {
//   products = [];
//   selectedCategory: string = '';
//   user:any = null;

//   constructor(public dialog: MatDialog, public authService: AuthService, private productService: ProductService){
//     this.productService.currentCategory.subscribe(category => this.selectedCategory = category);
//   }

//   handleOpenCreateProductForm() {
//     this.dialog.open(CreateProductFormComponent)
//   }

//   ngOnInit() {
//     this.authService.getUserProfile();
//     this.productService.getProducts().subscribe();
//     this.productService.productSubject.subscribe(
//       (state)=> {
//         console.log("state", state);
//         this.products = state.products
//         console.log("product's username is: ", this.products[0].user.username)
//       }
//     )
//   }
// }

import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ProductService } from 'src/app/services/product.service';
import { CreateProductFormComponent } from '../create-product-form/create-product-form.component';
import { AuthService } from 'src/app/services/auth.service';
import { ProductCardComponent } from '../product-card/product-card.component';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import {MatSidenavModule} from '@angular/material/sidenav';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [ProductCardComponent, MatIconModule, MatButtonModule, MatSidenavModule, CommonModule],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.scss'
})
export class HomePageComponent implements OnInit {
  products = [];
  selectedCategory: string = '';
  user: any = null;

  constructor(public dialog: MatDialog, public authService: AuthService, private productService: ProductService) {
    this.productService.currentCategory.subscribe(category => this.selectedCategory = category);
  }

  ngOnInit() {
    this.authService.getUserProfile();
    this.authService.authSubject.subscribe(
      (auth)=>{
        console.log("auth state", auth)
        this.user=auth.user
      }
    )
    this.productService.getProducts().subscribe({
      next: data => {
        if (Array.isArray(data)) {
          this.products = data.sort((a, b) => new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime());
        } else {
          console.error("Unexpected response format", data);
          this.products = []; // Fallback to an empty array
        }
      },
      error: error => console.log("error", error)
    });
    
    this.productService.productSubject.subscribe(
      (state) => {
        console.log("state", state);
        this.products = state.products.sort((a, b) => new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime());
        if (this.products.length > 0) {
          console.log("product's username is: ", this.products[0].user.username);
        }
      }
    );
  }

  handleOpenCreateProductForm() {
    this.dialog.open(CreateProductFormComponent);
  }

  // Optional: Track by function for ngFor trackBy
  trackByFn(index: number, item: any): number {
    return item.id;
  }
}
