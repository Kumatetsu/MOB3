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
        return "OK"
    }
}