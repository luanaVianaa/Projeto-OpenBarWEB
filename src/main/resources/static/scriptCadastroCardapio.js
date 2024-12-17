/* global fetch */
$(document).ready(function () {
    $("#formCardapio").on("submit", function (event) {
        event.preventDefault();

        const nome = $("#itemNome").val().trim();
        const descricao = $("#itemDescricao").val().trim();
        const valor = $("#itemValor").val().trim();
        const tipo = $("#tipoItem").val();  

        const tipoMap = {
            "Alimento": "ALIMENTO",  
            "Bebida": "BEBIDAS"
        };

        const categoria = tipoMap[tipo];

        console.log("Nome:", nome);
        console.log("Descrição:", descricao);
        console.log("Valor:", valor);
        console.log("Tipo:", tipo);  

        if (!nome || !descricao || !valor || !tipo) {
            alert("Por favor, preencha todos os campos.");
            return;
        }

        if (isNaN(valor) || valor <= 0) {
            alert("O valor deve ser maior que zero.");
            return;
        }

        fetch('http://localhost:8080/api/itens/adicionar', {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                nome: nome,
                descricao: descricao,
                preco: parseFloat(valor),
                tipo: tipo 
            })
        })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error("Erro ao salvar o item. Verifique os dados e tente novamente.");
            }
        })
        .then(data => {
            alert("Item cadastrado com sucesso!");
            $("#formCardapio")[0].reset();
        })
        .catch(error => {
            console.error("Erro ao cadastrar item no cardápio:", error);
            alert("Ocorreu um erro. Tente novamente mais tarde.");
        });
    });

    $("#logoutButton").on("click", function () {
        fetch("/login/logout", {
            method: "GET"
        })
        .then(response => {
            if (response.ok) {
                alert("Você saiu com sucesso.");
                window.location.href = "/login";  
            } else {
                throw new Error("Erro ao sair.");
            }
        })
        .catch(error => {
            console.error("Erro ao realizar logout:", error);
            alert("Ocorreu um erro ao tentar sair.");
        });
    });
});
