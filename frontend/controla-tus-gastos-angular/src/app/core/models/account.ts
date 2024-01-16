import {TypeAccount} from "./typeAccount";
import {Currency} from "./currency";

export interface Account {
  id: string;
  userId: string;
  name: string;
  typeAccount: TypeAccount;
  amount: number;
  currency: Currency;
}

export interface EUAccount{
  id: string|null;
  userId: string | null;
  name: string|null;
  typeAccountId: string|null;
  amount: number|null;
  currencyId: string|null;
}
