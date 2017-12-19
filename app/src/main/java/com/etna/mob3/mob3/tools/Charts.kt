package com.etna.mob3.mob3.tools

import android.view.View
import android.widget.RelativeLayout
import com.etna.mob3.mob3.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.io.File

/**
 * Created by kumatetsu on 18/12/2017.
 */

object Charts {

    fun createLinear(chart:LineChart, rl:RelativeLayout, file:File) {
        val air_entries      = ArrayList<Entry>()
        val humidity_entries = ArrayList<Entry>()
        val pressure_entries = ArrayList<Entry>()
        val windspeed        = ArrayList<Entry>()

        air_entries.add(Entry(1.0F,10.00F))
        air_entries.add(Entry(2.0F,12.00F))
        air_entries.add(Entry(3.0F,17.00F))
        air_entries.add(Entry(4.0F,19.00F))

        val dataSet = LineDataSet( air_entries, "temperature")
        dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        val lineData = LineData(dataSet)
        chart.setData(lineData)

        chart.invalidate()

        rl.addView(chart, 2000,600)
    }

    fun defineEntry(datas: ArrayList<Float>, entry: ArrayList<Entry>): ArrayList<Entry>{
        var i = 0F

        datas.forEach{
            entry.add(Entry(i, it))
            i ++
        }

        return entry
    }

    fun definedEntry(datas: Float, entry: ArrayList<Entry>) : ArrayList<Entry> {
        var i = 0F

        while (i < 24) {
            entry.add(Entry(i, datas))
            i ++
        }

        return entry
    }
}