const loginForm = document.getElementById("loginForm");

loginForm.addEventListener("submit", (e) => {
    e.preventDefault();

    const username = document.getElementById("userLogin").value.trim();
    const password = document.getElementById("userSenha").value.trim();

   
    const validCredentials = {
        username: "admin",
        password: "123456",
    };

    if (username === validCredentials.username && password === validCredentials.password) {
        alert("Login realizado com sucesso!");
        window.location.href = "index.html"; 
    } else {
        alert("Usuário ou senha inválidos. Tente novamente.");
    }

    loginForm.reset();
});
