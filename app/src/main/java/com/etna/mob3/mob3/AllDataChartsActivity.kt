package com.etna.mob3.mob3

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.etna.mob3.mob3.tools.Tools
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.LineData
import java.io.File

/**
 * Created by kumatetsu on 19/12/2017.
 */
class AllDataChartsActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.all_data_chart)
        val chart = LineChart(applicationContext)
        var rl = findViewById<View>(R.id.relativeLayout) as RelativeLayout
        var txt = findViewById<View>(R.id.textView2) as TextView

        txt.append(" " + intent.extras.get("name"))
        val data : LineData = Tools.parseForAllDatas(intent.extras.get("index") as Int, intent.extras.get("data") as File)
        Log.d("datas", data.toString())
        chart.setData(data)
        chart.invalidate()
        rl.addView(chart, 2000,600)
    }
}