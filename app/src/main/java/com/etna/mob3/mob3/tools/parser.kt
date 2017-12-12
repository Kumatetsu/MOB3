package com.etna.mob3.mob3.tools

import android.util.Log
import com.etna.mob3.mob3.classes.FileDatas
import java.io.File

/**
 * Created by kumatetsu on 02/12/2017.
 */

object Tools {

    fun getFileDate(file: File): String {
        val regex = Regex("\\S*-\\S*")
        var regexResult: MatchResult

        var exctract = file.readLines()
        var line: CharSequence = exctract[2]

        regexResult = regex.find(line)!!
        Log.d("Line", regexResult.value)

        return regexResult.value
    }

    fun parseFile (file: File): String {
        val datas          = FileDatas()
        var file_infos     = file.readLines()
        var air_temp       = ArrayList<Float>()
        var rel_humidity   = ArrayList<Float>()
        var air_pressure   = ArrayList<Float>()
        var wind_speed     = ArrayList<Float>()
        var wind_direction = ArrayList<Float>()

        file_infos = file_infos.drop(2)

        file_infos.forEach {
            var parsedLine : List<String> = it.split("\t")

            if ("" != parsedLine[33].trim()) {
                air_temp.add(parsedLine[33].toFloat())
            } else {
                air_temp.add("0".toFloat())
            }

            if ("" != parsedLine[35].trim()) {
                rel_humidity.add(parsedLine[35].toFloat())
            } else {
                rel_humidity.add("0".toFloat())
            }

            if ("" != parsedLine[38].trim()) {
                air_pressure.add(parsedLine[38].toFloat())
            } else {
                air_pressure.add("0".toFloat())
            }

            if ("" != parsedLine[3].trim()) {
                wind_speed.add(parsedLine[3].toFloat())
            } else {
                wind_speed.add("0".toFloat())
            }

            if ("" != parsedLine[9].trim()) {
                wind_direction.add(parsedLine[9].toFloat())
            } else {
                wind_direction.add("0".toFloat())
            }

        }

        Log.d("air_temp length", air_temp.size.toString())
        Log.d("air_temp value", air_temp.toString())

        Log.d("rel_humi length", rel_humidity.size.toString())
        Log.d("rel_humi value", rel_humidity.toString())

        Log.d("air_pressure length", air_pressure.size.toString())
        Log.d("air_pressure value", air_pressure.toString())

        Log.d("wind_speed length", wind_speed.size.toString())
        Log.d("wind_speed value", wind_speed.toString())

        Log.d("wind_direction length", wind_direction.size.toString())
        Log.d("wind_direction value", wind_direction.toString())

//        file.forEachLine {
//            parsed_file.plus(it.split("\t"))
//        }

//        var line = file_infos[2]
//        Log.d("split", line.split("\t").toString())

        return "OK"
    }
}