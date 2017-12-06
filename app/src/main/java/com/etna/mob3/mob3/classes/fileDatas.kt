package com.etna.mob3.mob3.classes

/**
 * Created by kumatetsu on 05/12/2017.
 */

class FileDatas() {
    val datas = object {
        val air_temp = defineDatasObject()
        val rel_humidity = defineDatasObject()
        val air_pressure = defineDatasObject()
        val local_ws_2min_mnm = defineDatasObject()
    }

    private fun defineDatasObject (): Any {
        return object {
            var min      = ""
            var min_hour = ""
            var average  = ""
            var max      = ""
            var max_hour = ""
        }
    }
}