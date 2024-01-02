import {Component, Input} from '@angular/core';
import { CommonModule } from '@angular/common';
import {Account} from "../../../../core/models/account";
import {MatCardModule} from "@angular/material/card";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";

@Component({
  selector: 'app-account',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatButtonModule,MatIconModule],
  templateUrl: './account.component.html',
  styleUrl: './account.component.css'
})
export class AccountComponent {
  @Input() account: Account = {
    id: 'test',
    name: 'test',
    typeAccount: 'SAVINGS',
    amount: 100,
    currency: 'COP'
  }

}
