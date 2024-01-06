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
import {TokenService} from "../../../../core/services/token.service";

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
  userId: string | null;

  constructor(
    private accountService: AccountService,
    private tokenService: TokenService,
    private dialog: MatDialog,
    private _snackBar: MatSnackBar
  ) {
    this.userId = this.tokenService.getId();
    if (!this.userId) {
      this.openSnackBar("No se pudo obtener el id del usuario","Cerrar");
      return;
    }

    this.accountService.getAccounts(this.userId).subscribe({
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
      data: this.userId
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result){
        this.accountService.createAccount(result).subscribe({
          next: response => {
            this.openSnackBar("Cuenta creada correctamente","Cerrar");
            this.listAccounts.push(response);
          },
          error: error => {
            console.error('There was an error!', error);
          }
        });
      }
    });
  }

  updateAccount(account: Account){
    const dialogRef = this.dialog.open(EuAccountComponent,{
      height: '500px',
      width: '500px',
      data: account
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result){
        this.accountService.updateAccount(result).subscribe({
          next: response => {
            this.openSnackBar("Cuenta actualizada correctamente","Cerrar");
            this.listAccounts = this.listAccounts.map((item)=>{
              if(item.id === response.id){
                return response;
              }
              return item;
            });
          },
          error: error => {
            console.error('There was an error!', error);
          }
        });
      }
    });
  }

  deleteAccount(id: string){
    this.accountService.deleteAccount(id).subscribe({
      next: response => {
        this.openSnackBar("Cuenta eliminada correctamente","Cerrar");
        this.listAccounts = this.listAccounts.filter(
          (item)=>item.id !== id
        )
      },
      error: error => {
        console.error('There was an error!', error);
      }
    });
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action);
  }
}
