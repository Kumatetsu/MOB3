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

        var map: HashMap<Int, Int> = hashMapOf()

        var windSpeedList = fileDatas.wind_speed_list
        var windDirectionList = fileDatas.wind_dir_list

        for (i in 0..windDirectionList.size-1) {

            val direction = windDirectionList[i]
            Log.d("direction", "" + direction)

            if (windSpeedList[i] > 0) {
                var directionName = convertDirection(direction)

                Log.d("direction name", "" + directionName)

                // Increment direction
                if (finalMap.containsKey(directionName)) {
                    Log.d("direction contains", "" + directionName)
                    val newVal = finalMap.getValue(directionName) + 1
                    finalMap.set(directionName, newVal)
                }
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
                "\t\t\t\t\"data\": [\n" +
                "\t\t\t\t\t[\"N\", " + finalMap.getValue("N") + "],\n" +
                "\t\t\t\t\t[\"NE\", " + finalMap.getValue("NE") + "],\n" +
                "\t\t\t\t\t[\"ENE\", " + finalMap.getValue("ENE") + "],\n" +
                "\t\t\t\t\t[\"ESE\", " + finalMap.getValue("ESE") + "],\n" +
                "\t\t\t\t\t[\"SE\", " + finalMap.getValue("SE") + "],\n" +
                "\t\t\t\t\t[\"SSE\", " + finalMap.getValue("SSE") + "],\n" +
                "\t\t\t\t\t[\"S\", " + finalMap.getValue("S") + "],\n" +
                "\t\t\t\t\t[\"SSW\", " + finalMap.getValue("SSW") + "],\n" +
                "\t\t\t\t\t[\"SW\", " + finalMap.getValue("SW") + "],\n" +
                "\t\t\t\t\t[\"WSW\", " + finalMap.getValue("WSW") + "],\n" +
                "\t\t\t\t\t[\"W\", " + finalMap.getValue("W") + "],\n" +
                "\t\t\t\t\t[\"WNW\", " + finalMap.getValue("WNW") + "],\n" +
                "\t\t\t\t\t[\"NW\", " + finalMap.getValue("NW") + "],\n" +
                "\t\t\t\t\t[\"NNW\", " + finalMap.getValue("NNW") + "],\n" +
                "\n" +
                "\t\t\t\t],\n" +
                "\t\t\t\t\"type\": \"column\",\n" +
                "\t\t\t\t\"name\": \">500G\"\n" +
                "\t\t\t}],\n" +
                "    plotOptions: {\n" +
                "        series: {\n" +
                "            stacking: 'normal',\n" +
                "            shadow: false,\n" +
                "            groupPadding: 0,\n" +
                "            pointPlacement: 'on'\n" +
                "        }\n" +
                "    }\n" +
                "});" +
                "</script>"

        this.webview.loadData(html, "text/html; charset=utf-8", "UTF-8")

    }

    fun convertDirection(direction: Int): String {

        when (direction) {
            in 348.75..11.25 -> return "N"
            in 11.25..33.75 -> return "NNE"
            in 33.75..56.25 -> return "NE"
            in 56.25..78.75 -> return "ENE"
            in 78.75..101.25 -> return "E"
            in 101.25..123.75 -> return "ESE"
            in 123.75..146.25 -> return "SE"
            in 146.25..168.75 -> return "SSE"
            in 168.75..191.25 -> return "S"
            in 191.25..213.75 -> return "SSW"
            in 213.75..236.25 -> return "SW"
            in 236.25..258.75 -> return "WSW"
            in 258.75..281.25 -> return "W"
            in 281.25..303.75 -> return "WNW"
            in 303.75..326.25 -> return "NW"
            in 326.25..348.75 -> return "NNW"

            else -> { // Note the block
                return "None"
            }
        }

    }
}