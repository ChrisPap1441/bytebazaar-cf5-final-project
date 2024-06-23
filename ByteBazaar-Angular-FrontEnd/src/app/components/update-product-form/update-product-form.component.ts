import { Component, Inject } from '@angular/core';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { ProductService } from 'src/app/services/product.service';
import { MatSelectModule } from '@angular/material/select';
import { MatDialog } from '@angular/material/dialog';
import { CommonModule } from '@angular/common';
import { ErrorDialogComponent } from '../error-dialog/error-dialog.component';

@Component({
  selector: 'app-update-product-form',
  standalone: true,
  imports: [FormsModule, MatFormFieldModule, MatInputModule, MatButtonModule, ReactiveFormsModule, CommonModule, MatSelectModule],
  templateUrl: './update-product-form.component.html',
  styleUrl: './update-product-form.component.scss'
})
export class UpdateProductFormComponent {

  productItem: any={
    title:"",
    category:"",
    image:"",
    description:"",
    contactInfo:""
  }

  productForm = new FormGroup({
    id: new FormControl(this.productItem.id),
    title: new FormControl("", [Validators.required, Validators.minLength(3), Validators.maxLength(25)]),
    category: new FormControl("", [Validators.required]),
    image: new FormControl("", [Validators.required]),
    description: new FormControl("", [Validators.required, Validators.maxLength(1000)]),
    contactInfo: new FormControl("", [Validators.required, Validators.maxLength(200)]),
  });

  constructor(@Inject(MAT_DIALOG_DATA) public product: any ,private productService: ProductService, public dialog: MatDialog){}

  onSubmit() {
    console.log("values", this.productForm.value)
    this.productService.updateProduct(this.productForm.value).subscribe({
      next:data=>{
        console.log("update product", data);
        this.dialog.closeAll();
      },
      error: (err) => {
        // Handle product update error
        const errorMessage = "Error \n\nIncomplete form.\n\n";
        this.dialog.open(ErrorDialogComponent, {
          data: { message: errorMessage.replace(/\n/g, '<br>') }
        });
        console.error("Product update error:", err);
      }
    });
  }

  onCancel() {
    this.dialog.closeAll();
    return false;
  }

  ngOnInit(){
    this.productItem = this.product
    this.productForm.patchValue(this.productItem);
  }

  get title() { return this.productForm.get('title'); }
  get category() { return this.productForm.get('category'); }
  get image() { return this.productForm.get('image'); }
  get description() { return this.productForm.get('description'); }
  get contactInfo() { return this.productForm.get('contactInfo'); }
}
