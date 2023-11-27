export interface User {
  id: number;
  username: string | null;
  password: string | null;
  email: string;
}

/* crear una interfaz para UserLogin */
export interface UserLogin extends Omit<User, 'id' | 'email'> {
}
