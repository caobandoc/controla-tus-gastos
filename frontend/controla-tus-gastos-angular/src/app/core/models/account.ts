export interface Account {
  id: string;
  name: string;
  typeAccount: 'SAVINGS' | 'CURRENT';
  amount: number;
  currency: string;
}
