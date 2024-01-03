import {Component} from '@angular/core';
import { CommonModule } from '@angular/common';

//service
import {AccountService} from "../../../../core/services/account.service";

//models
import {Account} from "../../../../core/models/account";
import {AccountComponent} from "../../components/account/account.component";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {MatGridListModule} from "@angular/material/grid-list";

@Component({
  selector: 'app-accounts',
  standalone: true,
  imports: [CommonModule, AccountComponent, MatProgressSpinnerModule, MatGridListModule],
  templateUrl: './accounts.component.html',
  styleUrl: './accounts.component.css'
})
export class AccountsComponent{
  listAccounts: Account[] = [];
  loadListAccounts: boolean = false;

  constructor(
    private AccountService: AccountService,
  ) {
    this.AccountService.getAccounts().subscribe({
      next: response => {
        this.listAccounts = response;
      },
      error: error => {
        console.error('There was an error!', error);
      },
      complete: () => {
        this.loadListAccounts = true;
      }
    });
  }
}
