document.addEventListener("DOMContentLoaded", () => {
    const inventarioList = document.getElementById("inventoryList");

    const items = [
        {name: "Cerveja", quantity: 50},
        {name: "Água sem gás", quantity: 20},
        {name: "Energético", quantity: 30},
    ];

    const renderInventory = () => {
        inventarioList.innerHTML = "";
        items.forEach((item, index) => {
            const row = document.createElement("tr");

            const nameCell = document.createElement("td");
            nameCell.textContent = item.name;

            const quantityCell = document.createElement("td");
            quantityCell.textContent = item.quantity;

            const actionCell = document.createElement("td");
            const removeButton = document.createElement("button");
            removeButton.textContent = "Remover";
            removeButton.classList.add("btn");
            removeButton.onclick = () => {
                items.splice(index, 1);
                renderInventory();
            };
            actionCell.appendChild(removeButton);

            row.appendChild(nameCell);
            row.appendChild(quantityCell);
            row.appendChild(actionCell);
            inventoryList.appendChild(row);
        });
    };

    renderInventory();

    window.adicionarItem = () => {
        const newItem = {name: "Novo Item", quantity: 10};
        items.push(newItem);
        renderInventory();
    };

    window.sair = () => {
        alert("Você saiu do sistema!");
        window.location.href = "paginaLogin.html";
    };
});
