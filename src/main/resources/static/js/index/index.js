$(document).ready(function() {
	buscarChart1();
});

function buscarChart1() {
	var data = [];
	
	$.get('/buscar_uf', function(lista) {		
		init_chart(lista[0],lista[1]);
	});
}

function init_chart(ufs,valores) {
	// based on prepared DOM, initialize echarts instance
	var myChart = echarts.init(document.getElementById('estado'));

	// specify chart configuration item and data
	var option = {
		title : {
			text : 'TOTAL DE CANDIDATOS POR ESTADO',
			subtext : 'UF'
		},
		tooltip : {
			trigger : 'axis',
			axisPointer : {
				type : 'shadow'
			}
		},
		legend : {
			data : []
		},
		grid : {
			left : '3%',
			right : '4%',
			bottom : '3%',
			containLabel : true
		},
		xAxis : {
			type : 'value',
			boundaryGap : [ 0, 0.01 ]
		},
		yAxis : {
			type : 'category',
			data : ufs
		},
		series : [ {
			name : 'CANDIDATOS:',
			type : 'bar',
			data : valores,
			label : {
				show : true
			}
		} ]
	};

	// use configuration item and data specified to show chart
	myChart.setOption(option);
}

function init_chart2(data) {
	// based on prepared DOM, initialize echarts instance
	var myChart = echarts.init(document.getElementById(''));

	// specify chart configuration item and data
	var option = {
		    title: {
		        text: 'TOTAL DE CANDIDATOS',
		        subtext: 'POR ESTADO',
		        left: 'center'
		    },
		    tooltip: {
		        trigger: 'item'
		    },
		    legend: {
		    	top: '10%',
		        left: 'center',
		    },
		    series: [
		        {
		            name: 'ESTADO',
		            type: 'pie',
		            radius: '50%',
		            data: data,
		            height: '100%',
		            top: '0%',		            
		            emphasis: {
		                itemStyle: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		};

	// use configuration item and data specified to show chart
	myChart.setOption(option);
}