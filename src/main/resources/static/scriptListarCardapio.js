$(document).ready(function() {
    // Função para carregar os itens do cardápio
    function carregarCardapio() {
        $.ajax({
            url: 'http://localhost:8080/api/itens/listar', 
            type: 'GET',
            success: function(data) {
                var tableBody = $('#cardapioTable tbody');
                tableBody.empty();  // Limpar o conteúdo da tabela antes de adicionar os novos itens

                // Iterar sobre os itens recebidos e adicionar as linhas na tabela
                data.forEach(function(item) {
                    var row = $('<tr>');

                    // Adicionando as células para nome, descrição, preço e tipo
                    row.append('<td>' + item.nome + '</td>');
                    row.append('<td>' + item.descricao + '</td>');
                    row.append('<td>' + item.preco.toFixed(2) + '</td>');
                    row.append('<td>' + item.tipo + '</td>');

                    // Adiciona o botão de remover dentro de uma célula <td> no final da linha
                    var removeButton = $('<button class="btn-remover">Remover</button>');
                    removeButton.on('click', function() {
                        removerItem(item.id);  // Chama a função de remover
                    });

                    var removeCell = $('<td>');  // Cria a célula da tabela
                    removeCell.append(removeButton);  // Coloca o botão dentro da célula
                    row.append(removeCell);  // Adiciona a célula à linha

                    tableBody.append(row);  // Adiciona a linha à tabela
                });
            },
            error: function(error) {
                console.error('Erro ao carregar o cardápio:', error);
            }
        });
    }

    // Função para remover item do cardápio
    function removerItem(id) {
        $.ajax({
            url: 'http://localhost:8080/api/itens/remover/' + id,  // URL com ID do item
            type: 'DELETE',
            success: function() {
                alert('Item removido com sucesso!');
                carregarCardapio();  // Recarrega a lista após a remoção
            },
            error: function(error) {
                console.error('Erro ao remover o item:', error);
                alert('Erro ao remover o item. Tente novamente mais tarde.');
            }
        });
    }

    // Chama a função para carregar os itens assim que a página for carregada
    carregarCardapio();
});
