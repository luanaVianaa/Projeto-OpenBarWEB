document.addEventListener("DOMContentLoaded", () => {
    const inputMes = document.getElementById("mes");
    const inputAno = document.getElementById("ano");
    const btnBuscarItens = document.getElementById("buscarItensMaisVendidos");
    const tabelaItens = document.getElementById("tabelaItens");

    btnBuscarItens.addEventListener("click", async () => {
        const mes = inputMes.value || new Date().getMonth() + 1;
        const ano = inputAno.value || new Date().getFullYear();

        try {
            const response = await fetch(`http://localhost:8080/api/pedidos/itens-mais-vendidos?mes=${mes}&ano=${ano}`);
            if (response.ok) {
                const itens = await response.json();
                tabelaItens.innerHTML = itens.map(item => `
                    <tr>
                        <td>${item.nome}</td>
                        <td>${item.quantidade}</td>
                        <td>R$ ${item.totalVendas.toFixed(2)}</td>
                    </tr>
                `).join("");
            } else {
                const erro = await response.text();
                alert(erro);
            }
        } catch (error) {
            console.error("Erro ao buscar itens mais vendidos:", error);
            alert("Erro ao buscar itens mais vendidos. Tente novamente.");
        }
    });
});
