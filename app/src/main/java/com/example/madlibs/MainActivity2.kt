package com.example.madlibs

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val id= findViewById<TextView>(R.id.id)
        readFile()
    }

    private fun readFile() {
        var archivoPlaceholders: String = ""
        val file = this.resources.getIdentifier("texto", "raw", this.packageName)
        val input = Scanner(resources.openRawResource(file))
        var cantidadLineas=0
        var cantidaPalbras=0
        with(input) {
            while (input.hasNextLine()) {
                var line = nextLine()
                cantidadLineas++
                if(cantidadLineas%2==0) {
                    cantidaPalbras++
                    line=line.replace("<","")
                    line=line.replace(">"," ")
                    archivoPlaceholders += (line)
                }
            }
        }
        input.close()

        contruccion(archivoPlaceholders,0,"")


    }
    fun contruccion(archivoPlaceholders: String,valorResta:Int,texto:String ){
        var valorResta1 = valorResta
        var texto1=texto
        val palabra = findViewById<TextView>(R.id.palabra)
        val placeholders=archivoPlaceholders.split(" ")

        palabra.hint=placeholders[valorResta1]
        val palabrasRestantes = findViewById<TextView>(R.id.palabrasRestantes)
        palabrasRestantes.text="Faltan ${placeholders.size-valorResta-1} palabras"
        val sugerencia = findViewById<TextView>(R.id.sugerencia)
        sugerencia.text="Por favor escriba un ${placeholders[valorResta1]}"
        val ok = findViewById<Button>(R.id.ok)
        ok.setOnClickListener { view ->
            valorResta1++
            texto1+=palabra.text.toString()+" "
            palabra.text=""
            if(placeholders.size-valorResta-1>1){
                sugerencia.text="Por favor escriba un ${placeholders[valorResta1]}"
                contruccion(archivoPlaceholders,valorResta1,texto1)
            }
            else{
                val intent = Intent(this,MainActivity3::class.java)
                intent.putExtra("PLACEHOLDERS",texto1)
                startActivity(intent)
                finish()
            }
        }
    }
}