package com.etna.mob3.mob3.classes

/**
 * Created by kumatetsu on 05/12/2017.
 */

data class FileDatas(
        val air_temp_max: Int,
        val air_temp_min: Int,
        val air_temp_avg: Int,
        val air_hour_min: String,
        val air_hour_max: String,
        val rel_humi_max: Int,
        val rel_humi_min: Int,
        val rel_humi_avg: Int,
        val rel_hour_min: String,
        val rel_hour_max: String,
        val air_pres_max: Int,
        val air_pres_min: Int,
        val air_pres_avg: Int,
        val airp_hour_min: String,
        val airp_hour_max: String,
        val wind_dir_max: Int,
        val wind_dir_min: Int,
        val wind_dir_avg: Int,
        val wind_dir_list: ArrayList<Int>,
        val windd_hour_min: String,
        val windd_hour_max: String,
        val wind_speed_max: Int,
        val wind_speed_min: Int,
        val wind_speed_avg: Int,
        val wind_speed_list: ArrayList<Int>,
        val winds_hour_min: String,
        val winds_hour_max: String
)