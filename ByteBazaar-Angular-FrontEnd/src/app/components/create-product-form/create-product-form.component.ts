import { Component } from '@angular/core';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import {MatRadioModule} from '@angular/material/radio';
import { ProductService } from 'src/app/services/product.service';
import { MatSelectModule } from '@angular/material/select';
import { MatDialog } from '@angular/material/dialog';
import { CommonModule } from '@angular/common';
import { ErrorDialogComponent } from '../error-dialog/error-dialog.component';

@Component({
  selector: 'app-create-product-form',
  standalone: true,
  imports: [FormsModule, MatFormFieldModule, MatInputModule, MatButtonModule, MatRadioModule, CommonModule, ReactiveFormsModule, MatSelectModule],
  templateUrl: './create-product-form.component.html',
  styleUrl: './create-product-form.component.scss'
})
export class CreateProductFormComponent {
  
  // productItem: any={
  //   title:"",
  //   category:"",
  //   image:"",
  //   description:"",
  //   contactInfo:""
  // }
  errorMessage: string = '';

  productForm = new FormGroup({
    title: new FormControl("", [Validators.required, Validators.minLength(3), Validators.maxLength(25)]),
    category: new FormControl("", [Validators.required]),
    image: new FormControl("", [Validators.required]),
    description: new FormControl("", [Validators.required, Validators.maxLength(1000)]),
    contactInfo: new FormControl("", [Validators.required, Validators.maxLength(200)]),
  });

  constructor(public dialog: MatDialog, private productService: ProductService){}

  onCancel() {
    this.dialog.closeAll();
    return false;
  }

  onSubmit() {
    console.log("values", this.productForm.value)
    this.productService.createProduct(this.productForm.value).subscribe({
      next:data=>{
        console.log("created product", data);
        this.dialog.closeAll();
      },
      error: (err) => {
        // Handle product creation error
        const errorMessage = "Error \n\nIncomplete form.\n\n";
        this.dialog.open(ErrorDialogComponent, {
          data: { message: errorMessage.replace(/\n/g, '<br>') }
        });
        console.error("Product creation error:", err);
      }
    })
  }
}
