import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { AuthService } from 'src/app/services/auth.service';
import { AbstractControl, ValidatorFn } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ErrorDialogComponent } from '../error-dialog/error-dialog.component';

@Component({
  selector: 'app-auth',
  standalone: true,
  imports: [CommonModule, FormsModule, MatFormFieldModule, MatInputModule, MatButtonModule, ReactiveFormsModule],
  templateUrl: './auth.component.html',
  styleUrl: './auth.component.scss'
})
export class AuthComponent {

  isRegister = false;
  errorMessage: string = '';
   
  constructor(public authService: AuthService, private dialog: MatDialog){}

  passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!*()?<>]).{8,}$"

  registerForm = new FormGroup({
    username: new FormControl("", [Validators.required, Validators.minLength(3), Validators.maxLength(25)]),
    email: new FormControl("", [Validators.required, Validators.email]),
    password: new FormControl("", [Validators.required, Validators.pattern(this.passwordPattern)]),
    confirmPassword: new FormControl('', [Validators.required, matchValidator('password')]),
  });

  loginForm = new FormGroup({
    email: new FormControl("", [Validators.required, Validators.email]),
    password: new FormControl("", [Validators.required]),
  });
  
  handleRegister() {
    console.log("Register", this.registerForm.value);
    this.authService.register(this.registerForm.value).subscribe({
      next: (response) => {
        localStorage.setItem("jwt", response.jwt);
        this.authService.getUserProfile().subscribe();
        console.log("signup success", response);
      },
      error: (err) => {
        // Handle registration error
        const errorMessage = "Registration failed. \nCheck for:\n\nIncomplete form \nUsername/Email already in use\nPasswords not matching.\n\nTry again.";
        this.dialog.open(ErrorDialogComponent, {
          data: { message: errorMessage.replace(/\n/g, '<br>') }
        });
        console.error("Registration error:", err);
      }
    });
  }

  handleLogin() {
    console.log("Login ", this.loginForm.value);
    this.authService.login(this.loginForm.value).subscribe({
      next: (response) => {
        console.log("login success", response);
        if (response.jwt) {
          localStorage.setItem("jwt", response.jwt);
          this.authService.getUserProfile().subscribe({
            next: (profile) => {
              console.log("User profile fetched successfully", profile);
            },
            error: (err) => {
              const errorMessage = "Failed to fetch user profile. Please try again later.";
              this.dialog.open(ErrorDialogComponent, {
                data: { message: errorMessage.replace(/\n/g, '<br>') }
              });
              console.error("Profile fetch error:", err);
            }
          });
        } else {
          const errorMessage = "Login failed. \n\nIncorrect Email or/and password.\n\n";
          this.dialog.open(ErrorDialogComponent, {
            data: { message: errorMessage.replace(/\n/g, '<br>') }
          });
          console.error("Login failed: Received null JWT token", response);
        }
      },
      error: (err) => {
        const errorMessage = "Login failed. \n\nIncorrect Email or/and password.\n\n";
        this.dialog.open(ErrorDialogComponent, {
          data: { message: errorMessage.replace(/\n/g, '<br>') }
        });
        console.error("Login error:", err);
      }
    });
  }
  
  

  togglePanel() {
    this.isRegister=!this.isRegister
  }
}

// Custom validator function
function matchValidator(matchTo: string): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } | null => {
    const password = control.parent?.get('password');
    const confirmPassword = control.value;

    if (password && confirmPassword !== password.value) {
      return { passwordMismatch: true };
    }
    return null;
  };
}