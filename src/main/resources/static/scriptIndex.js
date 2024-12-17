
/* global fetch */

document.addEventListener("DOMContentLoaded", () => {
    // Exibe uma mensagem de boas-vindas
    const userNameElement = document.querySelector("span[th\\:text='${userName}']");
    if (userNameElement) {
        const userName = userNameElement.textContent.trim();
        if (userName) {
            console.log(`Bem-vindo(a), ${userName}!`);
        }
    }

    // Exemplo de atualização de pedidos pendentes (simula atualização dinâmica)
    const pedidosPendentesElement = document.querySelector("span[th\\:text='${pedidosPendentes}']");
    if (pedidosPendentesElement) {
        fetch("/api/pedidos/pendentes") // Substitua pelo endpoint correto do seu sistema
            .then(response => response.json())
            .then(data => {
                pedidosPendentesElement.textContent = data.totalPendentes || 0;
            })
            .catch(error => {
                console.error("Erro ao buscar pedidos pendentes:", error);
            });
    }

    // Exemplo de logout
    document.querySelector(".btn-sair").addEventListener("click", sair);
});

// Função de logout
function sair() {
    fetch("/login/logout", {
        method: "GET"
    })
    .then(response => {
        if (response.ok) {
            alert("Você saiu com sucesso.");
            window.location.href = "/login"; // Redireciona para a página de login
        } else {
            throw new Error("Erro ao sair.");
        }
    })
    .catch(error => {
        console.error("Erro ao realizar logout:", error);
        alert("Ocorreu um erro ao tentar sair.");
    });
}
