
const cardapio = [
    {nome: "Hambúrguer", preco: 15.90},
    {nome: "Refrigerante", preco: 5.00},
    {nome: "Batata Frita", preco: 7.50},
    {nome: "Sobremesa", preco: 12.00}
];
o
function carregarCardapio() {
    const listaCardapio = document.getElementById('listaCardapio');
    listaCardapio.innerHTML = ""; 

    cardapio.forEach(item => {
        const li = document.createElement('li');
        li.innerHTML = `
            <input type="checkbox" name="item" value="${item.preco}" onchange="calcularTotal()">
            ${item.nome} - R$ ${item.preco.toFixed(2)}
        `;
        listaCardapio.appendChild(li);
    });
}

function calcularTotal() {
    const checkboxes = document.querySelectorAll('input[name="item"]:checked');
    let total = 0;

    checkboxes.forEach(checkbox => {
        total += parseFloat(checkbox.value);
    });

    document.getElementById('valorTotal').textContent = `R$ ${total.toFixed(2)}`;
}


document.getElementById('btnCarregarCardapio').addEventListener('click', carregarCardapio);
