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
        val datas = FileDatas()
        var file_infos = file.readLines()
        var air_temp = ArrayList<Float>()

        file_infos = file_infos.drop(2)

        file_infos.forEach {
            var parsedLine : List<String> = it.split("\t")

            if ("" != parsedLine[33].trim()) {
                air_temp.add(parsedLine[33].toFloat())
            } else {
                air_temp.add("0".toFloat())
            }


        }

        Log.d("air_temp length", air_temp.size.toString())
        Log.d("air_temp value", air_temp.toString())

//        file.forEachLine {
//            parsed_file.plus(it.split("\t"))
//        }

//        var line = file_infos[2]
//        Log.d("split", line.split("\t").toString())

        return "OK"
    }
}