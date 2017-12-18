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

        val html = "<body>\n" +
                "...\n" +
                "<script type=\"text/javascript\" src=\"file:///android_asset/flot-0.9-work/lib/jquery.js\"></script>\n" +
                "<script type=\"text/javascript\" src=\"file:///android_asset/flot-0.9-work/lib/jquery.colorhelpers.js\"></script>\n" +
                "<script type=\"text/javascript\" src=\"file:///android_asset/flot-0.9-work/jquery.flot.js\"></script><script type=\"text/javascript\" src=\"file:///android_asset/flot-0.9-work/jquery.flot.categories.js\"></script>\n" +
                "<script type=\"text/javascript\" src=\"file:///android_asset/flot-0.9-work/jquery.flot.tickrotor.js\"></script>\n" +
                "<script type=\"text/javascript\" src=\"file:///android_asset/flot-0.9-work/jquery.flot.axislabels.js\"></script>\n" +
                "...\n" +
                "</body>" +
                "<script type=\"text/javascript\">\n" +
                "\$(function() {\n" +
                "    var data = [ [0,0.0],[1,0.0],[2,0.0],[3,0.0],[4,0.0],[5,0.0],[6,0.0] ];\n" +
                "    var options = {\n" +
                "           xaxes: [ { ticks:[ [0,\"A\"],[1,\"B\"],[2,\"C\"],[3,\"D\"],[4,\"E\"],[5,\"F\"],[6,\"G\"] ],rotateTicks: 90 } ],\n" +
                "           series: { bars: { show: true, barWidth: 0.6, align: \"center\" } },\n" +
                "               xaxis: { mode: \"categories\", tickLength: 0, axisLabel: \"Name\", position: 'bottom', color: '#009874'},\n" +
                "               yaxis: { axisLabel: \"Total\", position: 'left', color: '#B163A3' } };\n" +
                "    \$.plot(\"#placeholder\", [ data ], options);\n" +
                "});\n" +
                "</script>"
        this.webview.loadData(html, "text/html; charset=utf-8", "UTF-8")

    }

}