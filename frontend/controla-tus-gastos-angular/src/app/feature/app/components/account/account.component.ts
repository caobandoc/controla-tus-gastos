import {Component, EventEmitter, Input, Output} from '@angular/core';
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
  @Input() account: Account ={
    id: '0a',
    userId: '0a',
    name: "test",
    amount: 0,
    typeAccount: "test",
    currency: "test"
  }
  @Output() updateAccount = new EventEmitter<Account>();
  @Output() deleteAccount = new EventEmitter<string>();

  onUpdateAccount(){
    this.updateAccount.emit(this.account);
  }

  onDeleteAccount(){
    if(this.account.id && confirm("¿Estas seguro de eliminar la cuenta?")){
      this.deleteAccount.emit(this.account.id);
    }
  }

}
