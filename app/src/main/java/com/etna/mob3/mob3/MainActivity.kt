package com.etna.mob3.mob3

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.io.File


class MainActivity : AppCompatActivity() {

    private val READ_REQUEST_CODE = 42

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        downloadButton.setOnClickListener {
            downloadButtonPressed()
        }
    }

    fun downloadButtonPressed() {
        Log.d("download","download button pressed")

        performFileSearch()
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

    fun performFileSearch() {

        // The wanted action (we want to open a document)
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)

        intent.addCategory(Intent.CATEGORY_OPENABLE)

        // Filter to show only images, using the image MIME data type.
        // If one wanted to search for ogg vorbis files, the type would be "audio/ogg".
        // To search for all documents available via installed storage providers,
        // it would be "*/*".
        intent.type = "*/*"

        startActivityForResult(intent, READ_REQUEST_CODE)

    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int,
                                         resultData: Intent?) {

        // Document is selected
        Log.d("result activity", "on result")

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            var uri: Uri? = null

            if (resultData != null) {

                // the uri of the selected document
                resultData.data
                uri = resultData.data

                val selectedfile = File(uri.path)
                val fileCopy: File = File(uri.path)

                var downloader = Downloader()

                Downloader()
               // downloader.copyFile(selectedfile, fileCopy)

                Log.d("", "Uri: " + uri!!.toString())
            }
        }
    }

}
