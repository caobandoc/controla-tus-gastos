const userRegister = {
  register: async (username: string, password: string, email: string): Promise<void> => {
    const response = await fetch('http://localhost:8080/api/v1/user', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ username, password, email })
    });

    if (!response.ok) {
      const { detail } = await response.json();
      throw new Error(detail);
    }
  }
};

export default userRegister;