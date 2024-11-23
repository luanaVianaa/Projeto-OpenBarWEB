const formCardapio = document.getElementById("formCardapio");
const cardapioList = document.getElementById("cardapioList");
let cardapio = []; 

formCardapio.addEventListener("submit", (e) => {
    e.preventDefault();

    const nome = document.getElementById("nomeItem").value.trim();
    const descricao = document.getElementById("descricaoItem").value.trim();
    const valor = parseFloat(document.getElementById("valorItem").value);

    if (!nome || !descricao || isNaN(valor) || valor <= 0) {
        alert("Por favor, preencha todos os campos corretamente!");
        return;
    }

    const item = { nome, descricao, valor };
    cardapio.push(item);
    renderCardapio();

    formCardapio.reset();
});


function renderCardapio() {
    cardapioList.innerHTML = "";
    cardapio.forEach((item, index) => {
        const li = document.createElement("li");
        li.innerHTML = `
            <strong>${item.nome}</strong> - ${item.descricao} - R$ ${item.valor.toFixed(2)}
            <button onclick="excluirItem(${index})">Excluir</button>
        `;
        cardapioList.appendChild(li);
    });
}

function excluirItem(index) {
    cardapio.splice(index, 1);
    renderCardapio();
}
