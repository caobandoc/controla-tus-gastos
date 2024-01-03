import {Component, Inject, signal} from '@angular/core';
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

@Component({
  selector: 'app-eu-account',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatSelectModule, MatButtonModule],
  templateUrl: './eu-account.component.html',
  styleUrl: './eu-account.component.css'
})
export class EuAccountComponent {
  accountForm = new FormGroup({
    id: new FormControl('' ),
    name: new FormControl('', [Validators.required, Validators.minLength(4)]),
    typeAccount: new FormControl('', [Validators.required]),
    amount: new FormControl(0, [Validators.required]),
    currency: new FormControl('', [Validators.required])
  });

  typeAccounts = [
    'SAVINGS',
    'CURRENT'
  ];

  currencies = [
    'EUR',
    'USD',
    'COP'
  ];

  constructor(
    private accountService: AccountService,
    private dialogRef: MatDialogRef<EuAccountComponent>,
    @Inject(MAT_DIALOG_DATA) public data: EUAccount
  ) {
    if (data) {
      this.accountForm.controls.id.setValue(data.id);
      this.accountForm.controls.name.setValue(data.name);
      this.accountForm.controls.typeAccount.setValue(data.typeAccount);
      this.accountForm.controls.amount.setValue(data.amount);
      this.accountForm.controls.currency.setValue(data.currency);
    }
  }

  onSubmit() {
    if (this.accountForm.valid) {
      let account: EUAccount = {
        id: this.accountForm.controls.id.value !=='' ? this.accountForm.controls.id.value : null,
        name: this.accountForm.controls.name.value,
        typeAccount: this.accountForm.controls.typeAccount.value,
        amount: this.accountForm.controls.amount.value,
        currency: this.accountForm.controls.currency.value
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
