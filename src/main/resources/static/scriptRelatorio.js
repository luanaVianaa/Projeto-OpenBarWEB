$(document).ready(function () {
    const apiUrlRelatorioDia = 'http://localhost:8080/api/relatorios/diario'; 
    const apiUrlRelatorioMes = 'http://localhost:8080/api/relatorios/mensal'; 
$("#gerarRelatorioDia").on("click", function () {
    const dataRelatorio = $("#dataRelatorio").val();

    if (!dataRelatorio) {
        alert("Por favor, selecione uma data.");
        return;
    }

    console.log("Data selecionada:", dataRelatorio); // Verifica o valor antes de enviar

    $.ajax({
        url: `${apiUrlRelatorioDia}?data=${dataRelatorio}`,
        type: 'GET',
        success: function (relatorio) {
            console.log("Relatório recebido:", relatorio);
            mostrarRelatorio(relatorio);
        },
        error: function (xhr) {
            alert("Erro ao gerar o relatório diário. Verifique os dados e tente novamente.");
            console.error("Erro na requisição:", xhr);
        }
    });
});


    // Função para gerar relatório mensal
    $("#gerarRelatorioMes").on("click", function () {
        const mesRelatorio = $("#mesRelatorio").val();
        const anoRelatorio = $("#anoRelatorio").val();

        if (!mesRelatorio || !anoRelatorio) {
            alert("Por favor, preencha o mês e o ano.");
            return;
        }

        $.ajax({
            url: `${apiUrlRelatorioMes}?mes=${mesRelatorio}&ano=${anoRelatorio}`,
            type: 'GET',
            success: function (relatorio) {
                mostrarRelatorio(relatorio);
            },
            error: function (xhr) {
                alert("Erro ao gerar o relatório mensal.");
                console.error(xhr);
            }
        });
    });

    // Função para exibir o relatório
    function mostrarRelatorio(relatorio) {
        let html = '<h3>Relatório Gerado:</h3>';
        
        if (relatorio) {
            html += `<p><strong>Item Mais Vendido:</strong> ${relatorio.itemMaisVendido}</p>`;
            html += `<p><strong>Quantidade Vendida:</strong> ${relatorio.quantidadeVendida}</p>`;
            html += `<p><strong>Faturamento Diário:</strong> R$ ${relatorio.faturamentoDia.toFixed(2)}</p>`;
            html += `<p><strong>Faturamento Mensal:</strong> R$ ${relatorio.faturamentoMes.toFixed(2)}</p>`;
        } else {
            html += '<p>Não foi possível gerar o relatório.</p>';
        }

        $("#resultadoRelatorio").html(html);
    }
});
