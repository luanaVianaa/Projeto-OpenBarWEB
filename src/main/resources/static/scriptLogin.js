document.addEventListener("DOMContentLoaded", () => {
    document.querySelector("form").addEventListener("submit", async function (event) {
        event.preventDefault(); // Impede o envio padrão do formulário

        const userLogin = document.getElementById("userNome").value.trim();
        const userSenha = document.getElementById("userSenha").value.trim();

        if (!userLogin || !userSenha) {
            alert("Por favor, preencha todos os campos.");
            return;
        }

        try {
            const response = await fetch("http://localhost:8080/api/usuarios/login", {
                method: "POST",
                headers: { "Content-Type": "application/x-www-form-urlencoded" },
                body: `nome=${userLogin}&senha=${userSenha}`
            });

            if (response.ok) {
                alert("Login bem-sucedido!");
                window.location.href = "./index.html"; // Redireciona para a página inicial
            } else {
                alert("Usuário ou senha inválidos.");
            }
        } catch (error) {
            console.error("Erro:", error);
            alert("Erro ao realizar o login. Tente novamente.");
        }
    });
});
