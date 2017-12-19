package com.etna.mob3.mob3.tools

import android.util.Log
import com.etna.mob3.mob3.classes.FileDatas
import com.etna.mob3.mob3.tools.Charts.defineEntry
import com.etna.mob3.mob3.tools.Charts.definedEntry
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
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
                wind_speed,
                time[wind_speed.indexOf(wind_speed.max()!!)],
                time[wind_speed.indexOf(wind_speed.min()!!)],
                wind_direction.max()!!.toInt(),
                wind_direction.min()!!.toInt(),
                wind_direction.average().toInt(),
                wind_direction,
                time[wind_direction.indexOf(wind_direction.max()!!)],
                time[wind_direction.indexOf(wind_direction.min()!!)]

        )

        return dayDatas
    }

    fun parseForLinear(index: Int, file: File) : LineData{
        var fileLines = file.readLines()
        var min = ArrayList<Float>()
        var max = ArrayList<Float>()
        var avg = ArrayList<Float>()
        var dmax: Float
        var dmin: Float
        var davg: Float
        var minChart = ArrayList<Entry>()
        var maxChart = ArrayList<Entry>()
        var avgChart = ArrayList<Entry>()
        var dminChart = ArrayList<Entry>()
        var davgChart = ArrayList<Entry>()
        var dmaxChart = ArrayList<Entry>()

        fileLines = fileLines.drop(2)

        min = getMinPerH(index, fileLines)
        max = getMaxPerH(index, fileLines)
        avg = getAvgPerH(index, fileLines)
        dmin = getMin(index, fileLines)
        dmax = getMax(index, fileLines)
        davg = getAvg(index, fileLines)

        minChart = defineEntry(min, minChart)
        maxChart = defineEntry(max, maxChart)
        avgChart = defineEntry(avg, avgChart)
        dminChart = definedEntry(dmin, dminChart)
        dmaxChart = definedEntry(dmax, dmaxChart)
        davgChart = definedEntry(davg, davgChart)

        val dataSet1 = LineDataSet( minChart, "minPerHour")
        dataSet1.setAxisDependency(YAxis.AxisDependency.LEFT)
        dataSet1.setColor(123, 154)
        val dataSet2 = LineDataSet( maxChart, "maxPerHour")
        dataSet2.setAxisDependency(YAxis.AxisDependency.LEFT)
        dataSet2.setColor(200, 356)
        val dataSet3 = LineDataSet( avgChart, "avgPerHour")
        dataSet3.setAxisDependency(YAxis.AxisDependency.LEFT)
        dataSet3.setColor(600, 954)
        val dataSet4 = LineDataSet( dminChart, "dayMin")
        dataSet4.setAxisDependency(YAxis.AxisDependency.LEFT)
        dataSet4.setColor(800, 432)
        val dataSet5 = LineDataSet( dmaxChart, "dayMax")
        dataSet5.setAxisDependency(YAxis.AxisDependency.LEFT)
//        dataSet5.setColor(5, 5)
        val dataSet6 = LineDataSet( davgChart, "dayAvg")
        dataSet6.setAxisDependency(YAxis.AxisDependency.LEFT)
//        dataSet6.setColor(6, 6)

        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(dataSet1)
        dataSets.add(dataSet2)
        dataSets.add(dataSet3)
        dataSets.add(dataSet4)
        dataSets.add(dataSet5)
        dataSets.add(dataSet6)

        val data = LineData(dataSets)

        return data
    }

    fun getMinPerH(column: Int, file: List<String>) : ArrayList<Float>{
        var container = ArrayList<Float>()
        var chaudron  = ArrayList<Float>()
        var index = 00.0F
        val regex = Regex("(\\d{2})")
        var regexResult: MatchResult

        file.forEach {
            var fileLine = it.split("\t")
            var time : CharSequence = fileLine[0]
            regexResult = regex.find(time, 11)!!
            var hour = regexResult.value.toFloat()
            if(hour == index) {
                if ("" != fileLine[column].trim()) {
                    chaudron.add(fileLine[column].toFloat())
                } else {
                    chaudron.add(0F)
                }

            } else {
                container.add(chaudron.min()!!.toFloat())
                chaudron.clear()
                chaudron.add(it[column].toFloat())
                index ++
            }
        }

        container.add(chaudron.min()!!.toFloat())

        return container
    }

    fun getMaxPerH(column: Int, file: List<String>) : ArrayList<Float> {
        var container = ArrayList<Float>()
        var chaudron  = ArrayList<Float>()
        var index = 00.0F
        val regex = Regex("(\\d{2})")
        var regexResult: MatchResult

        file.forEach {
            var fileLine = it.split("\t")
            var time : CharSequence = fileLine[0]
            regexResult = regex.find(time, 11)!!
            var hour = regexResult.value.toFloat()
            if(hour == index) {
                if ("" != fileLine[column].trim()) {
                    chaudron.add(fileLine[column].toFloat())
                } else {
                    chaudron.add(0F)
                }
            } else {
                container.add(chaudron.max()!!.toFloat())
                chaudron.clear()
                chaudron.add(it[column].toFloat())
                index ++
            }
        }

        container.add(chaudron.min()!!.toFloat())

        return container
    }

    fun getAvgPerH(column: Int, file: List<String>) : ArrayList<Float>{
        var container = ArrayList<Float>()
        var chaudron  = ArrayList<Float>()
        var index = 00.0F
        val regex = Regex("(\\d{2})")
        var regexResult: MatchResult

        file.forEach {
            var fileLine = it.split("\t")
            var time : CharSequence = fileLine[0]
            regexResult = regex.find(time, 11)!!
            var hour = regexResult.value.toFloat()
            if(hour == index) {
                if ("" != fileLine[column].trim()) {
                    chaudron.add(fileLine[column].toFloat())
                } else {
                    chaudron.add(0F)
                }
            } else {
                container.add(chaudron.average()!!.toFloat())
                chaudron.clear()
                chaudron.add(it[column].toFloat())
                index ++
            }
        }

        container.add(chaudron.min()!!.toFloat())

        return container
    }

    fun getMax(column: Int, file: List<String>) : Float {
        var data = 0.00F
        var container = ArrayList<Float>()
        file.forEach {
            var fileLine = it.split("\t")
            if ("" != fileLine[column].trim()) {
                container.add(it[column].toFloat())
            } else {
                container.add(0F)
            }
        }

        data = container.max()!!.toFloat()!!
        return data
    }


    fun getMin(column: Int, file: List<String>) : Float {
        var data = 0.00F
        var container = ArrayList<Float>()
        file.forEach {
            var fileLine = it.split("\t")
            if ("" != fileLine[column].trim()) {
                container.add(it[column].toFloat())
            } else {
                container.add(0F)
            }
        }

        data = container.min()!!.toFloat()!!
        return data
    }

    fun getAvg(column: Int, file: List<String>) : Float {
        var data = 0.00F
        var container = ArrayList<Float>()
        file.forEach {
            var fileLine = it.split("\t")
            if ("" != fileLine[column].trim()) {
                container.add(it[column].toFloat())
            } else {
                container.add(0F)
            }
        }

        data = container.average()!!.toFloat()!!
        return data
    }


}
