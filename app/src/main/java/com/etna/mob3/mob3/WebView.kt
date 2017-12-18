package com.etna.mob3.mob3

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.web_view.*



/**
 * Created by jeremydebelleix on 18/12/2017.
 */

class WebView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_view)

        // Enable Javascript
        val webSettings = this.webview.getSettings()
        webSettings.setJavaScriptEnabled(true)

        val html =  "<script src=\"https://code.highcharts.com/highcharts.js\"></script>\n" +
                "<script src=\"https://code.highcharts.com/modules/exporting.js\"></script>\n" +
                "<body style=\"background-color: grey;\">\n" +
                "...\n" +
                "\n" +
                "<div id=\"container\" style=\"min-width: 310px; height: 400px; margin: 0 auto\"></div>\n" +
                "<div id=\"container\" style=\"width:700px; height:700px;\"></div>" +
                "<div>hello</div>"+
                        "<script>" +
                        "Highcharts.chart('container', {\n" +
                        "    chart: {\n" +
                        "        type: 'column'\n" +
                        "    },\n" +
                        "    title: {\n" +
                        "        text: 'Monthly Average Rainfall'\n" +
                        "    },\n" +
                        "    subtitle: {\n" +
                        "        text: 'Source: WorldClimate.com'\n" +
                        "    },\n" +
                        "    xAxis: {\n" +
                        "        categories: [\n" +
                        "            'Jan',\n" +
                        "            'Feb',\n" +
                        "            'Mar',\n" +
                        "            'Apr',\n" +
                        "            'May',\n" +
                        "            'Jun',\n" +
                        "            'Jul',\n" +
                        "            'Aug',\n" +
                        "            'Sep',\n" +
                        "            'Oct',\n" +
                        "            'Nov',\n" +
                        "            'Dec'\n" +
                        "        ],\n" +
                        "        crosshair: true\n" +
                        "    },\n" +
                        "    yAxis: {\n" +
                        "        min: 0,\n" +
                        "        title: {\n" +
                        "            text: 'Rainfall (mm)'\n" +
                        "        }\n" +
                        "    },\n" +
                        "    tooltip: {\n" +
                        "        headerFormat: '<span style=\"font-size:10px\">{point.key}</span><table>',\n" +
                        "        pointFormat: '<tr><td style=\"color:{series.color};padding:0\">{series.name}: </td>' +\n" +
                        "            '<td style=\"padding:0\"><b>{point.y:.1f} mm</b></td></tr>',\n" +
                        "        footerFormat: '</table>',\n" +
                        "        shared: true,\n" +
                        "        useHTML: true\n" +
                        "    },\n" +
                        "    plotOptions: {\n" +
                        "        column: {\n" +
                        "            pointPadding: 0.2,\n" +
                        "            borderWidth: 0\n" +
                        "        }\n" +
                        "    },\n" +
                        "    series: [{\n" +
                        "        name: 'Tokyo',\n" +
                        "        data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4]\n" +
                        "\n" +
                        "    }, {\n" +
                        "        name: 'New York',\n" +
                        "        data: [83.6, 78.8, 98.5, 93.4, 106.0, 84.5, 105.0, 104.3, 91.2, 83.5, 106.6, 92.3]\n" +
                        "\n" +
                        "    }, {\n" +
                        "        name: 'London',\n" +
                        "        data: [48.9, 38.8, 39.3, 41.4, 47.0, 48.3, 59.0, 59.6, 52.4, 65.2, 59.3, 51.2]\n" +
                        "\n" +
                        "    }, {\n" +
                        "        name: 'Berlin',\n" +
                        "        data: [42.4, 33.2, 34.5, 39.7, 52.6, 75.5, 57.4, 60.4, 47.6, 39.1, 46.8, 51.1]\n" +
                        "\n" +
                        "    }]\n" +
                        "});</script>" +
                "</body>"

        this.webview.loadData(html, "text/html; charset=utf-8", "UTF-8")

    }

}