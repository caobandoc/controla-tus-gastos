import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { ReactiveFormsModule, FormGroup, FormControl, Validators } from '@angular/forms';

//material
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBar } from '@angular/material/snack-bar';

//service
import { UserService } from '../../../../core/services/user.service';

//models
import { UserRegister } from '../../../../core/models/user';
import { TokenService } from '../../../../core/services/token.service';
import { routes } from '../../../../app.routes';


@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    CommonModule, ReactiveFormsModule, MatInputModule,
    MatSelectModule, MatFormFieldModule, MatButtonModule
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent implements OnInit {
  registerForm = new FormGroup({
    username: new FormControl('', [Validators.required, Validators.minLength(4)]),
    password: new FormControl('', [Validators.required, Validators.minLength(6)]),
    email: new FormControl('', [Validators.required, Validators.email])
  });

  constructor(
    private userService: UserService,
    private tokenService: TokenService,
    private _snackBar: MatSnackBar,
    private router: Router
  ) { }

  ngOnInit(): void {
    if (this.tokenService.getToken()) {
      this.router.navigate(['/app']);
    }
  }

  onSubmit() {
    if (this.registerForm.valid) {
      let userRegister: UserRegister = {
        username: this.registerForm.controls.username.value,
        password: this.registerForm.controls.password.value,
        email: this.registerForm.controls.email.value
      };
      this.userService.register(userRegister).subscribe({
        next: () => {
          this.router.navigate(['/login']);
        },
        error: error => {
          this._snackBar.open(error.error.detail, 'Close', {
            duration: 2000,
          });
        }
      });
    }

  }

  login() {
    this.router.navigate(['/login']);
  }

}
