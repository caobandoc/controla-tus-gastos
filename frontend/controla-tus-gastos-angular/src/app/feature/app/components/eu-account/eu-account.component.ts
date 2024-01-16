import {Component, Inject, OnInit, signal} from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";

//materia
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {MatFormFieldModule} from "@angular/material/form-field";

//service
import {AccountService} from "../../../../core/services/account.service";

//models
import {Account, EUAccount} from "../../../../core/models/account";
import {MatInputModule} from "@angular/material/input";
import {MatSelectModule} from "@angular/material/select";
import {MatButtonModule} from "@angular/material/button";
import {CatalogService} from "../../../../core/services/catalog.service";
import {Catalog} from "../../../../core/models/catalog";

@Component({
  selector: 'app-eu-account',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatSelectModule, MatButtonModule],
  templateUrl: './eu-account.component.html',
  styleUrl: './eu-account.component.css'
})
export class EuAccountComponent implements OnInit{
  accountForm = new FormGroup({
    id: new FormControl('' ),
    userId: new FormControl(''),
    name: new FormControl('', [Validators.required, Validators.minLength(4)]),
    typeAccountId: new FormControl('', [Validators.required]),
    amount: new FormControl(0, [Validators.required]),
    currencyId: new FormControl('', [Validators.required])
  });

  typeAccounts:Catalog[] = [];

  currencies:Catalog[] = [];

  constructor(
    private accountService: AccountService,
    private catalogService: CatalogService,
    private dialogRef: MatDialogRef<EuAccountComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Account | string
  ) {
    this.catalogService.getCatalogAccounts().subscribe({
      next: typeAccounts => this.typeAccounts = typeAccounts,
      error: error => console.log(error)
    });

    this.catalogService.getCatalogCurrencies().subscribe({
      next: currencies => this.currencies = currencies,
      error: error => console.log(error)
    });

  }

  ngOnInit(): void {
    if (typeof this.data === 'string') {
      this.accountForm.controls.userId.setValue(this.data);
    } else {
      this.accountForm.controls.id.setValue(this.data.id);
      this.accountForm.controls.userId.setValue(this.data.userId);
      this.accountForm.controls.name.setValue(this.data.name);
      this.accountForm.controls.typeAccountId.setValue(this.data.typeAccount.id);
      this.accountForm.controls.amount.setValue(this.data.amount);
      this.accountForm.controls.currencyId.setValue(this.data.currency.id);
    }
  }

  onSubmit() {
    if (this.accountForm.valid) {
      let account: EUAccount = {
        id: this.accountForm.controls.id.value !=='' ? this.accountForm.controls.id.value : null,
        userId: this.accountForm.controls.userId.value !=='' ? this.accountForm.controls.userId.value : null,
        name: this.accountForm.controls.name.value,
        typeAccountId: this.accountForm.controls.typeAccountId.value,
        amount: this.accountForm.controls.amount.value,
        currencyId: this.accountForm.controls.currencyId.value
      };
      if (account.id) {
        this.accountService.updateAccount(account).subscribe({
          next: account => this.dialogRef.close(account),
          error: error => console.log(error)
        });
      } else {
        this.accountService.createAccount(account).subscribe({
          next: account =>  this.dialogRef.close(account),
          error: error => console.log(error)
        });
      }
    }
  }

  onCancel() {
    this.dialogRef.close();
  }
}
