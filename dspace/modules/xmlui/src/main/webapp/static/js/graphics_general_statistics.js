google.charts.load('current', {'packages':['corechart', 'table', 'bar']});
google.charts.setOnLoadCallback(drawTable);
google.charts.setOnLoadCallback(drawChartViews);

// Mudando title
var title = document.getElementById("main-container").getElementsByTagName('h2');
title[0].innerHTML = "Estatísticas ProEdu"
// Apagando as divs não utilizadas
document.getElementById("aspect_artifactbrowser_StatisticsViewer_div_all_actions_performed").innerHTML = null;
document.getElementById("aspect_artifactbrowser_StatisticsViewer_div_user_logins").innerHTML = null;
document.getElementById("aspect_artifactbrowser_StatisticsViewer_div_averaging_information").innerHTML = null;
document.getElementById("aspect_artifactbrowser_StatisticsViewer_div_log_level_information").innerHTML = null;
document.getElementById("aspect_artifactbrowser_StatisticsViewer_div_processing_information").innerHTML = null;
document.getElementById("aspect_artifactbrowser_StatisticsViewer_div_words_searched").innerHTML = null;
// Elementos h3
var heads = document.getElementById("aspect_artifactbrowser_StatisticsViewer_div_statistics").getElementsByTagName('h3');
heads[0].innerHTML = "Visão Geral do Repositório";
heads[1].innerHTML = "Arquivos mais baixados";
heads[2].innerHTML = "Links para Arquivos";
// Apagando os desnecessarios
for (var i=3; i < heads.length; i++){
	heads[i].innerHTML = null;
}

function drawTable() { // Geral tabela geral do Reposiorio
	var general_table = document.getElementById("aspect_artifactbrowser_StatisticsViewer_table_reportBlock");

	//var length_table = general_table.rows.length;

	var infos = ['Total de Arquivos', 'Downloads', 'Visualizações', 'Visulizações das Comunidades', 
			'Visulizações das Coleções', 'Total de Usuários', 'Pesquisas Realizadas'];

	var dados = new Array();

	for (var i=0; i < 7/*length_table*/; i++){
		var data = new Array();
		data[0] = infos[i];
		data[1] = (general_table.rows[i].cells.item(1).innerHTML).replace(',','.');
		dados.push(data);
	}

	var itens_table = document.getElementById("aspect_artifactbrowser_StatisticsViewer_div_archive_information");
	var tables = itens_table.getElementsByTagName('table');
	var table = tables[0];
	var value = (parseInt(table.rows[1].cells[1].innerHTML) + 1);
	dados[0][1] = value.toString().replace(',','.');

	// Google Charts
	var data = new google.visualization.DataTable();
	data.addColumn('string', 'Dados');
	data.addColumn('string', 'Quantidade');
	data.addRows(dados);

	var table = new google.visualization.Table(document.getElementById('aspect_artifactbrowser_StatisticsViewer_div_general_overview'));

	table.draw(data, {showRowNumber: true, width: '90%', height: '300px'});
}

function drawChartViews(){
	var viewed_table = document.getElementById("aspect_artifactbrowser_StatisticsViewer_div_items_viewed").getElementsByTagName('tr');
	var dados = new Array();
	dados.push(['Arquivo', 'Visualizações'])
	for (var i=1; i < viewed_table.length; i++){ // 1 -> não pegar cabeçalho
		if (i <= 10){ // os 10 mais baixados
			var valor = new Array();
			var start = viewed_table[i].cells.item(0).innerHTML.indexOf(">") + 1;
			var end = viewed_table[i].cells.item(0).innerHTML.indexOf("(");
			valor[0] = viewed_table[i].cells.item(0).innerHTML.slice(start, end).concat(" ");
			valor[1] = parseInt(viewed_table[i].cells.item(1).innerHTML.replace('.',''), 10);
			dados.push(valor);
		}
		else {
			viewed_table[i].innerHTML = null;
		}
	}
	var data = google.visualization.arrayToDataTable(dados);

	var options = {
		title: "Quantidade de Acessos por Arquivo",
		width: '95%',
		height: '100%',
		bar: {groupWidth: "70%"},
		legend: { position: "none" },
	};
	var div = document.getElementById("aspect_artifactbrowser_StatisticsViewer_div_archive_information");
	div.style.height = '400px';

	var chart = new google.visualization.BarChart(div);
	chart.draw(data, options);
}