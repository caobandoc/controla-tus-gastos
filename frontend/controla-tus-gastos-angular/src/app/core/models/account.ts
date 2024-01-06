export interface Account {
  id: string;
  userId: string;
  name: string;
  typeAccount: string;
  amount: number;
  currency: string;
}

export interface EUAccount{
  id: string|null;
  userId: string | null;
  name: string|null;
  typeAccount: string|null;
  amount: number|null;
  currency: string|null;
}
