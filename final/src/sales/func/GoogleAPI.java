package sales.func;

import java.util.ArrayList;
import java.util.List;

import sales.domain.PieVO;
import sales.domain.startVO;

public class GoogleAPI {

	
	public String getPieChart(String title,String[] cnames,int[] ccount) {
		String htmlString = "<html>\r\n" + 
				"  <head>\r\n" + 
				"    <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>\r\n" + 
				"    <script type=\"text/javascript\">\r\n" + 
				"      google.charts.load('current', {'packages':['corechart']});\r\n" + 
				"      google.charts.setOnLoadCallback(drawChart);\r\n" + 
				"      function drawChart() {\r\n" + 
				"        var data = google.visualization.arrayToDataTable([\r\n" + 
				"          ['�׸�', '��ġ'],\r\n";
				for(int i=0; i<cnames.length;i++ )
				{
					htmlString+="['"+cnames[i]+"',"+ccount[i] + "],\r\n";
				}
				
				htmlString+="        ]);\r\n" + 
				"        var options = {\r\n" + 
				"          title: '"+title+"'\r\n" + 
				"        };\r\n" + 
				"        var chart = new google.visualization.PieChart(document.getElementById('piechart'));\r\n" + 
				"        chart.draw(data, options);\r\n" + 
				"      }\r\n" + 
				"    </script>\r\n" + 
				"  </head>\r\n" + 
				"  <body style='overflow-x:hidden;overflow-y:hidden'>\r\n" + 
				"    <div id=\"piechart\" style=\"width: 585px; height: 500px;\"></div>\r\n" + 
				"  </body>\r\n" + 
				"</html>\r\n" + 
				"";
		
		
		
		
		return htmlString;
		
		
	}
}
