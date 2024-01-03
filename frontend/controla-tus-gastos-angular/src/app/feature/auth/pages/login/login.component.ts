import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { ReactiveFormsModule, FormGroup, FormControl, Validators } from '@angular/forms';

//material
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { AuthService } from '../../../../core/services/auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';

//models
import { UserLogin } from '../../../../core/models/user';
import { TokenService } from '../../../../core/services/token.service';


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CommonModule, ReactiveFormsModule, MatInputModule,
    MatSelectModule, MatFormFieldModule, MatButtonModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginForm = new FormGroup({
    username: new FormControl('', [Validators.required, Validators.minLength(4)]),
    password: new FormControl('', [Validators.required, Validators.minLength(6)])
  });

  constructor(
    private authService: AuthService,
    private tokenService: TokenService,
    private _snackBar: MatSnackBar,
    private router: Router
  ) {}

  onSubmit() {
    if (this.loginForm.valid) {
      let userLogin: UserLogin = {
        username: this.loginForm.controls.username.value,
        password: this.loginForm.controls.password.value
      };
      this.authService.login(userLogin).subscribe({
        next: response => {
          this.tokenService.setToken(response.token);
          this.router.navigate(['/app']);
        },
        error: error => {
          this._snackBar.open(error.error, 'Close', {
            duration: 2000,
          });
        }
      });
    }
  }

  onRegister() {
    this.router.navigate(['/register']);
  }

}
