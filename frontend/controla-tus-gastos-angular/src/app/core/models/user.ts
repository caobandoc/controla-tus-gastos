export interface User {
  id: number;
  username: string | null;
  password: string | null;
  email: string | null;
}

/* crear una interfaz para UserLogin */
export interface UserLogin extends Omit<User, 'id' | 'email'> {
}

/* crear una interfaz para UserRegister */
export interface UserRegister extends Omit<User, 'id'> {
}
