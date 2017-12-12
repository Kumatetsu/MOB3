package com.etna.mob3.mob3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import com.etna.mob3.mob3.classes.FileDatas
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.io.File
import com.etna.mob3.mob3.tools.Tools
import java.io.ObjectInput

class MainActivity : AppCompatActivity() {

    private val READ_REQUEST_CODE: Int = 42
    private val FILE_SELECT_CODE: Int = 1
    private val TAG: String = "MainActivity"
    private val DL_DIR: String = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()
    private val APP_DIR: String = "/storage/emulated/0/Meteo/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fillList()

        downloadButton.setOnClickListener {
            downloadButtonPressed()
        }

        fileList.setOnItemClickListener { parent, view, position: Int, id ->
            val fileName = fileList.getItemAtPosition(position) as String
            val selectedFile : File = File(APP_DIR + fileName)

            val parsing_result: FileDatas = Tools.parseFile(selectedFile)

            Log.d("test object", parsing_result.air_temp_max.toString())
            //val intent = Intent(this, MeteoInfoActivity::class.java)
            //startActivity(intent);
        }
    }

    private fun downloadButtonPressed() {
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var dl_file: File
        var cp_file: File
        super.onActivityResult(requestCode, resultCode, data)
        if (data == null) {
            Log.d("Info", "Intent is null, no file selected")
        } else {

            when (requestCode) {
                FILE_SELECT_CODE -> if (resultCode == Activity.RESULT_OK) {
                    // Get the Uri of the selected file
                    val uri = data.data
                    // Get the path in file utils
                    var path = uri.path.removePrefix("/document/raw:")
                    Log.d("path", path)

                    // Pour get le filename on crée une variable de type File() pour
                    // avoir accès à la méthode getName()
                    dl_file = File(path)
                    var file_name: String = Tools.getFileDate(dl_file)

                    // On modifie la route du dl file pour que celui ci soit
                    // utilisable par la fonction copyTo
                    cp_file = File(APP_DIR + file_name)

                    // On try catch pour éviter que si le fichier n'est pas trouvable on bloque
                    // L'application
                    try {
                        dl_file.copyTo(cp_file, true)
                        Log.d("Result", "files copied")
                        Log.d("Resultpath", cp_file.canonicalPath)
                    } catch (e: Exception) {
                        Log.d(TAG, e.toString())
                    }
                }
            }
        }

        fillList()

    }

    fun fillList() {

        var fileArray = ArrayList<String>()

        // TODO replace pathname with the APP_DIR

        File(APP_DIR).walkTopDown().forEach {
            fileArray.add(it.name)
        }

        if (fileArray.size > 0) {

            var listAdapater = ArrayAdapter(this, android.R.layout.simple_list_item_1, fileArray)

            fileList.adapter = listAdapater

        }

    }
}
