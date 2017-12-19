package com.etna.mob3.mob3

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ListView
import android.widget.TableRow
import android.widget.TextView
import com.etna.mob3.mob3.classes.CustomAdapterCharts
import com.etna.mob3.mob3.classes.FileDatas
import com.etna.mob3.mob3.classes.listChartData
import com.etna.mob3.mob3.tools.Tools
import kotlinx.android.synthetic.main.activity_meteo_info.*
import java.io.File

class MeteoInfoActivity : AppCompatActivity() {

    private var fileData: FileDatas? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meteo_info)
        var file: File = intent.extras.get("file") as File
        var adapter: CustomAdapterCharts? = null

        if (intent != null) {
            if (intent.extras != null) {
                var file: File = intent.extras.get("file") as File

                this.fileData = Tools.parseFile(file)

                fillDataTable()
            }
        }

        var columns = file.readLines().get(1).split("\t")
        columns = columns.drop(1)
        var linview = findViewById<View>(R.id.linview) as ListView
        var alldataview = findViewById<View>(R.id.alldataview) as ListView
        var roseview = findViewById<View>(R.id.roseview) as ListView
        var dataModels: ArrayList<listChartData> = ArrayList()
        adapter = CustomAdapterCharts(dataModels, this)
        var index = 1
        columns.forEach {
            dataModels.add(listChartData(it,index))
            index ++
        }

        this.linview.setAdapter(adapter)
        this.alldataview.setAdapter(adapter)
        this.roseview.setAdapter(adapter)

        linview.setOnItemClickListener { parent, view, position: Int, id ->

            val selectedLine: listChartData = linview.getItemAtPosition(position) as listChartData
          //  val intent = Intent(this, MeteoInfoActivity::class.java)

            val intent = Intent(this, WebView::class.java)

            val index = selectedLine.index
            var name = selectedLine.name

            intent.putExtra("index", index)
            intent.putExtra("name", name)
            intent.putExtra("file", file)

            startActivity(intent)
        }

        if (intent.extras.get("name") != null) {
            // TODO : Call the graph with parse data
        }

    }

    private fun fillDataTable() {

        load_Air_Temperature()
        load_Rel_Humidity()
        load_Air_Pressure()
        load_Local_WS_2min_mnm()

    }

    private fun load_Air_Temperature() {

        val air_temperature = TableRow(this)

        var title = TextView(this)
        var min = TextView(this)
        var hour_min = TextView(this)
        var avg_day = TextView(this)
        var max = TextView(this)
        var hour_max = TextView(this)

        title.text = "AIR_TEMPERATURE"
        min.text = this.fileData!!.air_temp_min.toString()
        hour_min.text = this.fileData!!.air_hour_min
        avg_day.text = this.fileData!!.air_temp_avg.toString()
        max.text = this.fileData!!.air_temp_max.toString()
        hour_max.text = this.fileData!!.air_hour_max

        title.setTextColor(Color.WHITE)
        avg_day.setTextColor(Color.WHITE)
        hour_min.setTextColor(Color.WHITE)
        hour_max.setTextColor(Color.WHITE)
        max.setTextColor(Color.WHITE)
        min.setTextColor(Color.WHITE)

        air_temperature.addView(title)
        air_temperature.addView(min)
        air_temperature.addView(hour_min)
        air_temperature.addView(avg_day)
        air_temperature.addView(max)
        air_temperature.addView(hour_max)

        this.data_table.addView(air_temperature)
    }

    private fun load_Rel_Humidity() {

        val rel_humidity = TableRow(this)

        var title = TextView(this)
        var min = TextView(this)
        var hour_min = TextView(this)
        var avg_day = TextView(this)
        var max = TextView(this)
        var hour_max = TextView(this)

        title.text = "REL_HUMIDITY"
        min.text = this.fileData!!.rel_humi_min.toString()
        hour_min.text = this.fileData!!.rel_hour_min
        avg_day.text = this.fileData!!.rel_humi_avg.toString()
        max.text = this.fileData!!.rel_humi_max.toString()
        hour_max.text = this.fileData!!.rel_hour_max

        title.setTextColor(Color.WHITE)
        avg_day.setTextColor(Color.WHITE)
        hour_min.setTextColor(Color.WHITE)
        hour_max.setTextColor(Color.WHITE)
        max.setTextColor(Color.WHITE)
        min.setTextColor(Color.WHITE)

        rel_humidity.addView(title)
        rel_humidity.addView(min)
        rel_humidity.addView(hour_min)
        rel_humidity.addView(avg_day)
        rel_humidity.addView(max)
        rel_humidity.addView(hour_max)

        this.data_table.addView(rel_humidity)
    }

    private fun load_Air_Pressure() {
        val air_pressure = TableRow(this)

        var title = TextView(this)
        var min = TextView(this)
        var hour_min = TextView(this)
        var avg_day = TextView(this)
        var max = TextView(this)
        var hour_max = TextView(this)

        title.text = "AIR_PRESSURE"
        min.text = this.fileData!!.air_pres_min.toString()
        avg_day.text = this.fileData!!.air_pres_min.toString()
        hour_min.text = this.fileData!!.air_hour_min
        avg_day.text = this.fileData!!.air_pres_avg.toString()
        max.text = this.fileData!!.air_pres_max.toString()
        hour_max.text = this.fileData!!.air_hour_max

        title.setTextColor(Color.WHITE)
        avg_day.setTextColor(Color.WHITE)
        hour_min.setTextColor(Color.WHITE)
        hour_max.setTextColor(Color.WHITE)
        max.setTextColor(Color.WHITE)
        min.setTextColor(Color.WHITE)

        air_pressure.addView(title)
        air_pressure.addView(min)
        air_pressure.addView(hour_min)
        air_pressure.addView(avg_day)
        air_pressure.addView(max)
        air_pressure.addView(hour_max)

        this.data_table.addView(air_pressure)
    }

    private fun load_Local_WS_2min_mnm() {

        val local_ws_2min = TableRow(this)

        var title = TextView(this)
        var min = TextView(this)
        var hour_min = TextView(this)
        var avg_day = TextView(this)
        var max = TextView(this)
        var hour_max = TextView(this)

        title.text = "LOCAL_WS_2MIN_MNM"
        min.text = this.fileData!!.wind_speed_min.toString()
        hour_min.text = this.fileData!!.winds_hour_min
        avg_day.text = this.fileData!!.wind_speed_avg.toString()
        max.text = this.fileData!!.wind_speed_max.toString()
        hour_max.text = this.fileData!!.winds_hour_min

        title.setTextColor(Color.WHITE)
        avg_day.setTextColor(Color.WHITE)
        hour_min.setTextColor(Color.WHITE)
        hour_max.setTextColor(Color.WHITE)
        max.setTextColor(Color.WHITE)
        min.setTextColor(Color.WHITE)

        local_ws_2min.addView(title)
        local_ws_2min.addView(min)
        local_ws_2min.addView(hour_min)
        local_ws_2min.addView(avg_day)
        local_ws_2min.addView(max)
        local_ws_2min.addView(hour_max)

        this.data_table.addView(local_ws_2min)
    }

}
