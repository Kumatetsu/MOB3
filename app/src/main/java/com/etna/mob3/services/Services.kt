package com.etna.mob3.services

import android.os.Environment
import android.util.Log
import java.io.File


/**
 * Created by kumatetsu on 27/11/2017.
 */

object Services {

    fun getFiles() {
        Log.d("getfiles", "getfiles called")
        var dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        if (dir.exists()) {
            Log.d("empty folder", "Download folder is empty")
         }
// else {
//            var files = dir.listFiles()
//            files.forEach {
//                Log.d("file", files.toString())
//            }
//        }
    }
}