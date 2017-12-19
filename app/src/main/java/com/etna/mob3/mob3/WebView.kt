package com.etna.mob3.mob3

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.etna.mob3.mob3.tools.Tools
import kotlinx.android.synthetic.main.web_view.*
import java.io.File

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

        val file: File = intent.extras.get("file") as File
        val fileDatas = Tools.parseFile(file)

        val directionMap: HashMap<Double, String> = hashMapOf(
                11.25 to "N",
                33.75 to "NNE",
                56.25 to "NE",
                78.75 to "ENE",
                101.25 to "E",
                123.75 to "ESE",
                146.25 to "SE",
                168.75 to "SSE",
                191.25 to "S",
                213.75 to "SSW",
                236.25 to "SW",
                258.75 to "WSW",
                281.25 to "W",
                303.75 to "WNW",
                326.25 to "NW",
                348.75 to "NNW")

        val finalMap: HashMap<String, Int> = hashMapOf(
                "N" to 0,
                "NNE" to 0,
                "NE" to 0,
                "ENE" to 0,
                "E" to 0,
                "ESE" to 0,
                "SE" to 0,
                "SSE" to 0,
                "S" to 0,
                "SSW" to 0,
                "SW" to 0,
                "WSW" to 0,
                "W" to 0,
                "WNW" to 0,
                "NW" to 0,
                "NNW" to 0)

        var windSpeedList = fileDatas.wind_speed_list
        var windDirectionList = fileDatas.wind_dir_list

        Log.d("list size", "" + windDirectionList.size)
        for (i in 0..windDirectionList.size-1) {

            var directionValue: Double = windDirectionList.get(i).toDouble()

            if (directionValue > 0) {
                directionValue = (windDirectionList.get(i).toDouble() / 16)
                val directionName: String = directionMap.getValue(directionValue)

                val newVal = finalMap.getValue(directionName) + 1
                finalMap.set(directionName, newVal)
            }

        }

        val html =  "<script src=\"https://code.highcharts.com/highcharts.js\"></script>\n" +
                "<script src=\"https://code.highcharts.com/highcharts-more.js\"></script>\n" +
                "<script src=\"https://code.highcharts.com/modules/data.js\"></script>\n" +
                "<script src=\"https://code.highcharts.com/modules/exporting.js\"></script>\n" +
                "\n" +
                "<body>" +
                "<div id=\"container\" style=\"min-width: 420px; max-width: 600px; height: 400px; margin: 0 auto\"></div>\n" +
                "\n" +
                "<div style=\"display:none\">\n" +
                "</div>\n" +
                "\"</body>\" +\n" +
                "<script>" +
                "Highcharts.chart('container', {\n" +
                "    \n" +
                "    chart: {\n" +
                "        polar: true,\n" +
                "        type: 'column'\n" +
                "    },\n" +
                "\n" +
                "    title: {\n" +
                "        text: 'Wind rose for South Shore Met Station, Oregon'\n" +
                "    },\n" +
                "\n" +
                "    subtitle: {\n" +
                "        text: 'Source: or.water.usgs.gov'\n" +
                "    },\n" +
                "\n" +
                "    pane: {\n" +
                "        size: '85%'\n" +
                "    },\n" +
                "\n" +
                "    legend: {\n" +
                "        align: 'right',\n" +
                "        verticalAlign: 'top',\n" +
                "        y: 100,\n" +
                "        layout: 'vertical'\n" +
                "    },\n" +
                "\n" +
                "    xAxis: {\n" +
                "        tickmarkPlacement: 'on',\n" +
                "        type:'category'\n" +
                "    },\n" +
                "\n" +
                "    yAxis: {\n" +
                "        min: 0,\n" +
                "        endOnTick: false,\n" +
                "        showLastLabel: true,\n" +
                "        \n" +
                "        \n" +
                "        reversedStacks: false\n" +
                "    },\n" +
                "\n" +
                "   \n" +
                "\t\tseries: [{\n" +
                "\"data\": [\n" +
                "\t\t\t\t\t[\"N\", 12],\n" +
                "\t\t\t\t\t[\"NNE\", 12],\n" +
                "\t\t\t\t\t[\"NE\", 12],\n" +
                "\t\t\t\t\t[\"ENE\", 12],\n" +
                "\t\t\t\t\t[\"E\", 12],\n" +
                "\t\t\t\t\t[\"ESE\", 12],\n" +
                "\t\t\t\t\t[\"SE\", 12],\n" +
                "\t\t\t\t\t[\"SSE\", 12],\n" +
                "\t\t\t\t\t[\"S\", 12],\n" +
                "\t\t\t\t\t[\"SSW\", 12],\n" +
                "\t\t\t\t\t[\"SW\", 12],\n" +
                "\t\t\t\t\t[\"WSW\", 12],\n" +
                "\t\t\t\t\t[\"W\", 12],\n" +
                "\t\t\t\t\t[\"WNW\", 12],\n" +
                "\t\t\t\t\t[\"NW\", 12],\n" +
                "\t\t\t\t\t[\"NNW\", 12],\n" +
                "\t\t\t\t],\n" +
                "\t\t\t\t\"type\": \"column\",\n" +
                "\t\t\t\t\"name\": \"<40G\"\n" +
                "\t\t\t}\n" +
                "],\n" +
                "    plotOptions: {\n" +
                "        series: {\n" +
                "            stacking: 'normal',\n" +
                "            shadow: false,\n" +
                "            groupPadding: 0,\n" +
                "            pointPlacement: 'on'\n" +
                "        }\n" +
                "    }\n" +
                "});\n" +
                "</script>"

        this.webview.loadData(html, "text/html; charset=utf-8", "UTF-8")

    }

}