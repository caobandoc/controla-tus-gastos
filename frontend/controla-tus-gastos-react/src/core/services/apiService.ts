interface LoginResponse {
  token: string;
}

const apiService = {
  login: async (username: string, password: string): Promise<LoginResponse> => {
    const response = await fetch('http://localhost:8080/api/v1/auth/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ username, password })
    });

    if (!response.ok) {
      //recuperar el mensaje de error, esta en el body como texto plano
      const message = await response.text();
      throw new Error(message);
    }

    const data = await response.json();

    return data;
  }
};

export default apiService;