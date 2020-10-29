package com.example.madlibs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import java.util.*

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        val placeholders = intent.getStringExtra("PLACEHOLDERS").toString()
        val parrafo= findViewById<TextView>(R.id.parrafo)
        parrafo.text=readFile(placeholders)
    }
    private fun readFile(placeholders:String): String {
        var parrafo: String = ""
        val file = this.resources.getIdentifier("texto", "raw", this.packageName)
        val input = Scanner(resources.openRawResource(file))
        var cantidadLineas=0
        val placeholder = placeholders.split(" ")
        var cont=0
        with(input) {
            while (input.hasNextLine()) {
                var line = nextLine()
                cantidadLineas++
                if(cantidadLineas%2==0) {
                }
                else{
                    if(cont!=placeholder.size){
                        parrafo += (line)+" "+placeholder[cont]+" "
                        cont++
                    }
                }
            }
        }
        input.close()
        Log.v("CONSOLA",parrafo)
        return parrafo
    }
     fun onClickNew(v : View){
         finish()
     }
}