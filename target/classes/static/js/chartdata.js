var chartDataStr = decodeHTML(chartData)
var charDataArr = JSON.parse(chartDataStr)

var arrLen = charDataArr.length
var numericData = [], labelData = []

for(var i=0; i < arrLen; i++){
	numericData[i] = charDataArr[i].value
	labelData[i] = charDataArr[i].label
}


var ctx = document.getElementById('myChart').getContext('2d');
var myChart = new Chart(ctx, {
    type: 'pie',
    data: {
        labels: labelData,
        datasets: [{
            label: 'My First dataset',
            backgroundColor: ["#1824b6", "#e56b7c", "#86a440", "#ef3fa3", "#ddee28"],
            data: numericData
        }]
    },
    options: {
        title: {
			display: true,
			text: "Project Status"
		}
    }
});


function decodeHTML(html){
	var txt = document.createElement("textarea")
	txt.innerHTML = html
	return txt.value
}