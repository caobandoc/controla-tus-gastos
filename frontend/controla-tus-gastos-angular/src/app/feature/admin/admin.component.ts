import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {Router, RouterOutlet} from "@angular/router";

//material
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatIconModule} from "@angular/material/icon";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatButtonModule} from "@angular/material/button";
import {TokenService} from "../../core/services/token.service";

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [CommonModule, RouterOutlet, MatSidenavModule, MatIconModule, MatToolbarModule, MatButtonModule],
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.css'
})
export class AdminComponent {

  constructor(
    private router: Router,
    private tokenService: TokenService
  ) {
  }

  logout() {
    this.tokenService.removeToken();
    this.router.navigate(['/']);
  }

}
