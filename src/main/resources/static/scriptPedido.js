$(document).ready(function () {
    const apiUrlCardapio = 'http://localhost:8080/api/itens/listar';
    const apiUrlAdicionarItem = 'http://localhost:8080/api/pedidos/adicionar-item';
    const apiUrlRealizarPedido = 'http://localhost:8080/api/pedidos/adicionar';

    // Função para carregar o cardápio
    function carregarCardapio() {
        $.ajax({
            url: apiUrlCardapio,
            type: 'GET',
            success: function (itens) {
                console.log("Itens do cardápio recebidos:", itens);
                $("#listaCardapio").empty();  // Limpa a lista do cardápio antes de adicionar novos itens

                if (itens && itens.length > 0) {
                    itens.forEach(item => {
                        const li = $("<li>").text(`${item.nome} - R$ ${item.preco.toFixed(2)}`).attr("data-id", item.id);

                        const quantidadeInput = $("<input>").attr({
                            type: "number",
                            value: 1,
                            min: 1
                        }).css({
                            "width": "50px", // Largura menor
                            "padding": "5px", // Espaçamento interno
                            "border": "1px solid #ccc", // Cor da borda
                            "border-radius": "4px", // Bordas arredondadas
                            "font-size": "14px", // Tamanho da fonte
                            "margin-right": "10px"  // Espaço entre o input e o botão
                        });

                        const btnAdicionar = $("<button>").text("Adicionar")
                                .css({
                                    "background-color": "#007bff", // Cor de fundo azul
                                    "color": "white", // Cor do texto branca
                                    "border": "none",
                                    "padding": "8px 16px", // Tamanho do botão
                                    "border-radius": "5px", // Bordas arredondadas
                                    "cursor": "pointer", // Mostrar o cursor como pointer
                                    "font-size": "14px", // Tamanho da fonte
                                    "transition": "background-color 0.3s ease"  // Efeito ao passar o mouse
                                })
                                .hover(function () {
                                    // Alterar a cor ao passar o mouse
                                    $(this).css("background-color", "#0056b3");
                                }, function () {
                                    $(this).css("background-color", "#007bff");
                                })
                                .on("click", function () {
                                    const quantidade = parseInt(quantidadeInput.val(), 10);
                                    adicionarAoPedido(item.id, quantidade);
                                });

                        li.append(quantidadeInput, btnAdicionar);
                        $("#listaCardapio").append(li);
                    });
                } else {
                    alert("Nenhum item encontrado no cardápio.");
                }
            },
            error: function (xhr) {
                alert("Erro ao carregar o cardápio.");
                console.error(xhr);
            }
        });
    }

    // Função para adicionar o item ao pedido
    function adicionarAoPedido(itemId, quantidade) {
        const dataPedido = new Date().toISOString().split('T')[0];  // Data dinâmica atual

        const url = `${apiUrlAdicionarItem}?itemId=${itemId}&quantidade=${quantidade}&data=${dataPedido}`;
        console.log("URL da requisição:", url);

        $.ajax({
            url: url,
            type: 'POST',
            success: function (pedido) {
                console.log("Pedido atualizado:", pedido);
                $("#valorTotal").text(`R$ ${pedido.valorTotal.toFixed(2)}`);
                alert("Item adicionado ao pedido com sucesso!");
            },
            error: function (xhr) {
                alert("Erro ao adicionar o item ao pedido.");
                console.error(xhr.responseText);  // Exibe a resposta completa do erro
            }
        });
    }

    // Função para realizar o pedido
    $("#realizarPedido").on("click", function () {
        const nomeCliente = $("#nomeCliente").val();
        const telefoneCliente = $("#telefoneCliente").val();

        if (!nomeCliente || !telefoneCliente) {
            alert("Por favor, preencha os dados do cliente.");
            return;
        }

        const pedido = {
            nomeCliente: nomeCliente,
            telefoneCliente: telefoneCliente
        };

        $.ajax({
            url: apiUrlRealizarPedido,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(pedido),
            success: function (response) {
                alert("Pedido realizado com sucesso!");
                $("#valorTotal").text("R$ 0.00");
                $("#nomeCliente").val("");
                $("#telefoneCliente").val(""); 
            },
            error: function (xhr) {
                alert("Erro ao realizar o pedido.");
                console.error(xhr);
            }
        });
    });

    // Evento para carregar o cardápio ao clicar no botão
    $("#btnCarregarCardapio").on("click", function () {
        carregarCardapio();
    });
});
