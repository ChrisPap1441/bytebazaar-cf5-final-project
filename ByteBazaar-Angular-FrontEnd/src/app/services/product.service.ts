import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private baseUrl = 'http://localhost:8080'

  constructor(private http:HttpClient) { }

  productSubject = new BehaviorSubject<any>({
    products:[],
    loading: false,
    newProduct: null
  })

  private categorySource = new BehaviorSubject<string>('');
  currentCategory = this.categorySource.asObservable();

  changeCategory(category: string) {
    this.categorySource.next(category);
  }

  private getHeaders():HttpHeaders{
    const token = localStorage.getItem("jwt")
    return new HttpHeaders({
      Authorization:`Bearer ${localStorage.getItem("jwt")}`
    })
  }

  getProducts():Observable<any>{
    const headers = this.getHeaders();
    return this.http.get(`${this.baseUrl}/api/products`,{headers}).pipe
      (tap((products)=>{
        const currentState = this.productSubject.value;
        this.productSubject.next({...currentState, products});
      })
    );
  }

  createProduct(product:any):Observable<any>{
    const headers = this.getHeaders();
    return this.http.post(`${this.baseUrl}/api/products`, product,{headers}).pipe
      (tap((newProduct)=>{
        const currentState = this.productSubject.value;
        this.productSubject.next({...currentState, products: [newProduct, ...currentState.products]});
      })
    );
  }

  updateProduct(product:any):Observable<any>{
    const headers = this.getHeaders();
    return this.http.put(`${this.baseUrl}/api/products/${product.id}`, product,{headers}).pipe
      (tap((updateProduct:any)=>{
        const currentState = this.productSubject.value;
        const updatedProduct = currentState.products.map((item:any)=> item.id === updateProduct.id?updateProduct:item)
        this.productSubject.next({...currentState, products: updatedProduct});
      })
    );
  }

  deleteProduct(productId: any): Observable<any> {
    const headers = this.getHeaders();
    return this.http.delete(`${this.baseUrl}/api/products/${productId}`, {
      headers,
      responseType: 'text' // Set the response type to 'text'
    }).pipe(
      tap((deleteProduct: any) => {
        const currentState = this.productSubject.value;
        const deletedProduct = currentState.products.filter((item: any) => item.id !== productId);
        this.productSubject.next({ ...currentState, products: deletedProduct });
      })
    );
  }

  likeProduct(productId:any):Observable<any>{
    const headers = this.getHeaders();
    return this.http.put(`${this.baseUrl}/api/products/${productId.id}/like`, productId, {headers}).pipe
      (tap((likeProduct:any): void=>{
        const currentState = this.productSubject.value;
        const likedProduct = currentState.products.map((item:any)=> item.id === likeProduct.id?likeProduct:item);
        this.productSubject.next({...currentState, products: likedProduct});
      })
    );
  }
}