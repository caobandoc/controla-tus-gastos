export interface Account {
  id: string|null;
  name: string;
  typeAccount: string;
  amount: number;
  currency: string;
}

export interface EUAccount{
  id: string|null;
  name: string|null;
  typeAccount: string|null;
  amount: number|null;
  currency: string|null;
}
