package com.etna.mob3.mob3

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.support.design.widget.Snackbar
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.net.URISyntaxException
import java.io.File
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    private val READ_REQUEST_CODE = 42
    private val FILE_SELECT_CODE = 1
    private val TAG = "MainActivity"
    private val APP_DIR: String = "/storage/emulated/0/Download/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        downloadButton.setOnClickListener {
            downloadButtonPressed()
        }
    }

    fun downloadButtonPressed() {
        Log.d("download","download button pressed")

        showFileChooser()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun showFileChooser() {

        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"
        intent.addCategory(Intent.CATEGORY_OPENABLE)

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"),
                    FILE_SELECT_CODE)
        } catch (ex: android.content.ActivityNotFoundException) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show()
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        val dl_file: File
        val cp_file: File

        when (requestCode) {
            FILE_SELECT_CODE -> if (resultCode == Activity.RESULT_OK) {
                // Get the Uri of the selected file
                val uri = data.data
                Log.d(TAG, "File Uri: " + uri!!.toString())
                // Get the path in file utils
                val path = uri.path
                Log.d(TAG, "File Path: " + path)
                dl_file = File("/storage/emulated/0/Download/1_ENHANCED_25.his")
                Log.d(TAG, "file name: " + dl_file.absolutePath)
                cp_file = File(APP_DIR + "TESTSAMERE.his")
                val toto = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()
                Log.d("test", toto)
//                try {
//                    val toto = dl_file.readLines(Charsets.UTF_8)
//                    Log.d(TAG, toto[1])
//                } catch (e: Exception) {
//                    Log.d(TAG, e.toString())
//                }
                try {
                    dl_file.copyTo(cp_file)
                    Log.d("Result", "files copied")
                } catch (e: Exception) {
                    Log.d(TAG, e.toString())
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}
