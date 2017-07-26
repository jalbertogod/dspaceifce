google.charts.load('current', {'packages':['bar']});
google.charts.setOnLoadCallback(drawChart);

function drawChart() {

  // var data = google.visualization.arrayToDataTable();
  // data.addColumn('string', 'Mês');
  // data.addColumn('number', 'Nº de Views');
  // data.addRows(7);

  // for (var i=1; i <= 6; i++){
  //   var line = new Array();
  //   line[0] = document.getElementById("aspect_statistics_StatisticsTransformer_cell_0-"+i+"-h").innerHTML;
  //   line[1] = parseInt(document.getElementById("aspect_statistics_StatisticsTransformer_cell_0-"+i).innerHTML, 10);
  //   data.setValue(line);
  // }

  var dates = new Array();

  dates.push(['Mês', 'Visualização']);

  for (var i=1; i <= 6; i++){
    var line = new Array();
    line[0] = document.getElementById("aspect_statistics_StatisticsTransformer_cell_0-"+i+"-h").innerHTML;
    line[1] = parseInt(document.getElementById("aspect_statistics_StatisticsTransformer_cell_0-"+i).innerHTML, 10);
    dates.push(line);
  }

  var data = google.visualization.arrayToDataTable(dates);
  var options = {
    chart: {
      title: 'Total de visitas por mês',
      //subtitle: 'Sales, Expenses, and Profit: 2014-2017',
    }
  };

  var chart = new google.charts.Bar(document.getElementById('aspect_statistics_StatisticsTransformer_div_tablewrapper'));

  chart.draw(data, options);
}