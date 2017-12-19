package com.etna.mob3.mob3

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import com.etna.mob3.mob3.tools.Tools
import com.github.mikephil.charting.data.LineData
import java.io.File
import com.github.mikephil.charting.charts.LineChart



/**
 * Created by kumatetsu on 18/12/2017.
 */

class LinearChartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chart)
        val chart = LineChart(applicationContext)
        var rl = findViewById<View>(R.id.rl) as RelativeLayout
        var txt = findViewById<View>(R.id.textView) as TextView

        txt.append(" " + intent.extras.get("name"))
        val data : LineData = Tools.parseForLinear(intent.extras.get("index") as Int, intent.extras.get("data") as File)
        Log.d("datas", data.toString())
        chart.setData(data)
        chart.invalidate()
        rl.addView(chart, 2000,600)
    }
}