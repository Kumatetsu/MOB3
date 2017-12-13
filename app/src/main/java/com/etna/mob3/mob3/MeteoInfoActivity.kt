package com.etna.mob3.mob3

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TableRow
import android.widget.TextView
import com.etna.mob3.mob3.classes.FileDatas
import com.etna.mob3.mob3.tools.Tools
import kotlinx.android.synthetic.main.activity_meteo_info.*
import java.io.File

class MeteoInfoActivity : AppCompatActivity() {

    private val fileData: FileDatas? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meteo_info)

      //this.air_temperature.setBackgroundColor(Color.BLACK)

        val heading = TableRow(this)
        var analyseLabel = TextView(this)
        analyseLabel.text = "Analyse"
        heading.addView(analyseLabel)

        this.data_table.addView(heading)

        if (intent != null) {
            if (intent.extras != null) {
                var file: File = intent.extras.get("file") as File

                var fileData = Tools.parseFile(file)

                Log.d("filedata", "" + fileData)
            }
        }
    }
}
