package com.etna.mob3.mob3

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.etna.mob3.mob3.classes.FileDatas
import com.etna.mob3.mob3.tools.Tools
import java.io.File

class MeteoInfoActivity : AppCompatActivity() {

    private val fileData: FileDatas? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meteo_info)

        if (intent != null) {
            if (intent.extras != null) {
                var file: File = intent.extras.get("file") as File

                var fileData: FileDatas = Tools.parseFile(file)

                Log.d("filedata", "" + fileData.air_hour_max)
            }
        }
    }
}
