document.addEventListener("DOMContentLoaded", function () {
    const apiUrlListar = "http://localhost:8080/api/inventario/listar";
    const apiUrlAdicionar = "http://localhost:8080/api/inventario/adicionar";
    const apiUrlDeletar = "http://localhost:8080/api/inventario/deletar";
    const form = document.getElementById("formInventario");
    const tabelaInventario = document.getElementById("inventarioList");
    const botaoAdicionar = document.getElementById("adicionarItem");

    async function listarInventario() {
    try {
        const response = await fetch(apiUrlListar);
        if (!response.ok) {
            throw new Error("Erro ao listar itens do inventário.");
        }

        const inventario = await response.json();

        tabelaInventario.innerHTML = ""; // Limpa a tabela

        inventario.forEach(item => {
            const nome = item.nome || "Sem nome";
            const tipo = item.tipo || "Não especificado"; // Define um valor padrão para tipo
            const quantidade = item.quantidade || 0;

            const row = `
                <tr>
                    <td>${nome}</td>
                    <td>${tipo}</td>
                    <td>${quantidade}</td>
                    <td>
                        <button class="btn-excluir" onclick="excluirItem(${item.id})">Excluir</button>
                    </td>
                </tr>
            `;
            tabelaInventario.insertAdjacentHTML("beforeend", row);
        });
    } catch (error) {
        console.error(error.message);
        alert("Erro ao listar o inventário. Tente novamente mais tarde.");
    }
}


    // Função para adicionar item ao inventário
    botaoAdicionar.addEventListener("click", async function () {
        const nome = form.nomeItem.value;
        const tipo = form.tipoItem.value;
        const quantidade = parseInt(form.quantidadeItem.value, 10);

        console.log("Dados recebidos do formulário:", { nome, tipo, quantidade }); // Log para depuração

        // Validações
        if (!nome || !tipo || isNaN(quantidade)) {
            alert("Todos os campos devem ser preenchidos corretamente.");
            return;
        }

        const novoItem = { nome, tipo, quantidade };

        try {
            const response = await fetch(apiUrlAdicionar, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(novoItem)
            });

            if (!response.ok) {
                throw new Error("Erro ao adicionar item ao inventário.");
            }

            // Reseta o formulário
            form.reset();

            // Atualiza a lista de itens do inventário
            listarInventario();
        } catch (error) {
            console.error(error.message);
            alert("Erro ao adicionar item. Tente novamente mais tarde.");
        }
    });

    // Função para excluir item do inventário
    window.excluirItem = async function (id) {
        try {
            const response = await fetch(`${apiUrlDeletar}/${id}`, {
                method: "DELETE"
            });

            if (!response.ok) {
                throw new Error("Erro ao excluir item.");
            }

            listarInventario();
        } catch (error) {
            console.error(error.message);
            alert("Erro ao excluir item. Tente novamente mais tarde.");
        }
    };

    // Carrega o inventário ao abrir a página
    listarInventario();
});
