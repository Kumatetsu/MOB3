package com.etna.mob3.mob3

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TableRow
import android.widget.TextView
import com.etna.mob3.mob3.classes.FileDatas
import com.etna.mob3.mob3.tools.Tools
import kotlinx.android.synthetic.main.activity_meteo_info.*
import java.io.File

class MeteoInfoActivity : AppCompatActivity() {

    private var fileData: FileDatas? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meteo_info)

        if (intent != null) {
            if (intent.extras != null) {
                var file: File = intent.extras.get("file") as File

                this.fileData = Tools.parseFile(file)

                fillDataTable()
            }
        }
    }

    private fun fillDataTable() {

        load_Air_Temperature()
       /* load_Rel_Humidity()
        load_Air_Pressure()
        load_Local_WS_2min_mnm()*/

    }

    private fun load_Air_Temperature() {

        val air_temperature = TableRow(this)

        var title = TextView(this)
        var min = TextView(this)
        var hour_min = TextView(this)
        var avg_day = TextView(this)
        var max = TextView(this)

        title.text = "AIR_TEMPERATURE"
        min.text = this.fileData!!.air_temp_min.toString()
        hour_min.text = this.fileData!!.air_hour_min
        avg_day.text = this.fileData!!.air_temp_avg.toString()
        max.text = this.fileData!!.air_temp_max.toString()

        air_temperature.addView(title)
        air_temperature.addView(min)
        air_temperature.addView(hour_min)
        air_temperature.addView(avg_day)
        air_temperature.addView(max)

        this.data_table.addView(air_temperature)
    }

    private fun load_Rel_Humidity() {

    }
    private fun load_Air_Pressure() {

    }
    private fun load_Local_WS_2min_mnm() {

    }

}
