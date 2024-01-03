import {Component} from '@angular/core';
import { CommonModule } from '@angular/common';

//service
import {AccountService} from "../../../../core/services/account.service";

//models
import {Account} from "../../../../core/models/account";
import {AccountComponent} from "../../components/account/account.component";
import {MatGridListModule} from "@angular/material/grid-list";
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";
import {MatDialog} from "@angular/material/dialog";
import {EuAccountComponent} from "../../components/eu-account/eu-account.component";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-accounts',
  standalone: true,
  imports: [CommonModule, AccountComponent, MatGridListModule, MatIconModule,MatButtonModule],
  templateUrl: './accounts.component.html',
  styleUrl: './accounts.component.css'
})
export class AccountsComponent{
  listAccounts: Account[] = [];
  loadListAccounts: boolean = false;

  constructor(
    private AccountService: AccountService,
    private dialog: MatDialog,
    private _snackBar: MatSnackBar
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

  createAccount(){
    const dialogRef = this.dialog.open(EuAccountComponent,{
      height: '500px',
      width: '500px',
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result){
        this.AccountService.createAccount(result).subscribe({
          next: response => {
            this.openSnackBar("Cuenta creada correctamente","Cerrar");
            this.listAccounts.push(response);
          },
          error: error => {
            console.error('There was an error!', error);
          },
          complete: () => {
          }
        });
      }
    });
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action);
  }
}
