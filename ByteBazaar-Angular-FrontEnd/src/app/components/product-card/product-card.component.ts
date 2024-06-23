import { Component, Input } from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import { MatDialog } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { ProductService } from 'src/app/services/product.service';
import { UpdateProductFormComponent } from '../update-product-form/update-product-form.component';
import {MatExpansionModule} from '@angular/material/expansion';
import { AuthService } from 'src/app/services/auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-product-card',
  standalone: true,
  imports: [MatCardModule, MatButtonModule, MatIconModule, MatExpansionModule, CommonModule],
  templateUrl: './product-card.component.html',
  styleUrl: './product-card.component.scss'
})
export class ProductCardComponent {

  @Input() product: any
  @Input() toggle: any

  user:any = null;
  likes: number[] = [];

  firstPanelOpenState = false;
  secondPanelOpenState = false;

  constructor(public dialog: MatDialog, private productService: ProductService, public authService:AuthService){}

  ngOnInit(){
    this.authService.authSubject.subscribe(
      (auth)=>{
        console.log("auth state", auth)
        this.user=auth.user
      }
    )
  }

  handleOpenEditProductForm() {
    this.dialog.open(UpdateProductFormComponent, {data: this.product});
  }

  handleDeleteProduct() {
    this.productService.deleteProduct(this.product.id).subscribe();
  }

  handleLikeProduct() {
    this.productService.likeProduct(this.product).subscribe({
      next:data=>console.log("update", data),
      error:error=>console.log("error", error)
    });
  }

  isLiked() {
    if (Array.isArray(this.product.likes)) {
      this.likes = this.product.likes;
      const isUserLiked = this.likes.includes(this.user?.id);
      return isUserLiked;
    }
    return false;
  }
}
