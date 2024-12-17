$(document).ready(function () {
    const apiUrlListarPedidos = 'http://localhost:8080/api/pedidos/listar';  // Endereço da API para listar pedidos

    // Função para carregar a lista de pedidos
    function carregarPedidos() {
        $.ajax({
            url: apiUrlListarPedidos,
            type: 'GET',
            success: function (pedidos) {
                console.log("Pedidos recebidos:", pedidos);
                $("#pedidosTabela").empty();  // Limpa a tabela antes de adicionar os novos pedidos

                if (pedidos && pedidos.length > 0) {
                    pedidos.forEach(pedido => {
                        const nomeCliente = pedido.nomeCliente || 'Nome não disponível'; // Exibe nome do cliente (diretamente no pedido)
                        
                        // Verifica se a lista de itens existe e concatena os nomes dos itens
                        const itens = pedido.itens && pedido.itens.length > 0
                            ? pedido.itens.map(item => item.itemCardapio.nome).join(", ") 
                            : 'Nenhum item';

                        // Criação da linha da tabela
                        const tr = $("<tr>").attr("id", `pedido-${pedido.id}`);
                        tr.append(
                            `<td>${pedido.id}</td>`,
                            `<td>${nomeCliente}</td>`,  // Exibe o nome do cliente
                            `<td>${itens}</td>`,  // Exibe os itens do pedido
                            `<td>${pedido.status}</td>`,
                            `<td><input type="checkbox" class="concluirPedido" data-pedido-id="${pedido.id}"></td>`
                        );
                        $("#pedidosTabela").append(tr);
                    });

                    // Adiciona o evento para os checkboxes após a tabela ser carregada
                    document.querySelectorAll('.concluirPedido').forEach((checkbox) => {
                        checkbox.addEventListener('change', function () {
                            // Não faz nada no checkbox, só marca ou desmarca
                        });
                    });
                } else {
                    alert("Nenhum pedido encontrado.");
                }
            },
            error: function (xhr) {
                alert("Erro ao carregar os pedidos.");
                console.error(xhr);
            }
        });
    }

    // Carregar os pedidos ao carregar a página
    carregarPedidos();

    // Evento para o botão "Concluir" que processa apenas os pedidos marcados
    document.getElementById('concluirTodos').addEventListener('click', function () {
        document.querySelectorAll('.concluirPedido:checked').forEach((checkbox) => {
            const pedidoId = checkbox.dataset.pedidoId;

            // Envia a requisição DELETE para deletar o pedido
            fetch(`http://localhost:8080/api/pedidos/deletar/${pedidoId}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                }
            })
            .then(response => {
                if (response.ok) {
                    console.log('Pedido deletado:', pedidoId);
                    // Remove a linha da tabela imediatamente
                    const row = checkbox.closest('tr');
                    row.remove();
                } else {
                    console.error('Erro ao deletar o pedido');
                }
            })
            .catch((error) => {
                console.error('Erro ao deletar o pedido:', error);
            });
        });
    });
});
