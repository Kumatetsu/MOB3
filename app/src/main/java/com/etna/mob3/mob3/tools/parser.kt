package com.etna.mob3.mob3.tools

import android.util.Log
import com.etna.mob3.mob3.classes.FileDatas
import java.io.File
import java.util.*

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

    fun parseFile (file: File): FileDatas {
        var file_infos     = file.readLines()
        var time           = ArrayList<String>()
        var air_temp       = ArrayList<Float>()
        var rel_humidity   = ArrayList<Float>()
        var air_pressure   = ArrayList<Float>()
        var wind_speed     = ArrayList<Int>()
        var wind_direction = ArrayList<Int>()

        var file_infos_nb = file_infos.drop(2)

        file_infos_nb.forEach {
            var parsedLine : List<String> = it.split("\t")

            if ("" != parsedLine[0].trim()) {
                time.add(parsedLine[0])
            }

            if ("" != parsedLine[33].trim()) {
                air_temp.add(parsedLine[33].toFloat())
            }

            if ("" != parsedLine[35].trim()) {
                rel_humidity.add(parsedLine[35].toFloat())
            }

            if ("" != parsedLine[38].trim()) {
                air_pressure.add(parsedLine[38].toFloat())
            }

            if ("" != parsedLine[3].trim()) {
                wind_speed.add(parsedLine[3].toInt())
            }

            if ("" != parsedLine[9].trim()) {
                wind_direction.add(parsedLine[9].toInt())
            }

        }

        Log.d("time length", time.size.toString())
        Log.d("time value", time.toString())

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

        val dayDatas = FileDatas(
                air_temp.max()!!.toInt(),
                air_temp.min()!!.toInt(),
                air_temp.average().toInt(),
                time[air_temp.indexOf(air_temp.max()!!)],
                time[air_temp.indexOf(air_temp.min()!!)],
                rel_humidity.max()!!.toInt(),
                rel_humidity.min()!!.toInt(),
                rel_humidity.average().toInt(),
                time[rel_humidity.indexOf(rel_humidity.max()!!)],
                time[rel_humidity.indexOf(rel_humidity.min()!!)],
                air_pressure.max()!!.toInt(),
                air_pressure.min()!!.toInt(),
                air_pressure.average().toInt(),
                time[air_pressure.indexOf(air_pressure.max()!!)],
                time[air_pressure.indexOf(air_pressure.min()!!)],
                wind_speed.max()!!.toInt(),
                wind_speed.min()!!.toInt(),
                wind_speed.average().toInt(),
                time[wind_speed.indexOf(wind_speed.max()!!)],
                time[wind_speed.indexOf(wind_speed.min()!!)],
                wind_direction.max()!!.toInt(),
                wind_direction.min()!!.toInt(),
                wind_direction.average().toInt(),
                time[wind_direction.indexOf(wind_direction.max()!!)],
                time[wind_direction.indexOf(wind_direction.min()!!)]

        )

        return dayDatas
    }

}
