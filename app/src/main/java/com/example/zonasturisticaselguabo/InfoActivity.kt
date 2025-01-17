package com.example.zonasturisticaselguabo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.zonasturisticaselguabo.databinding.ActivityInfoBinding
import com.squareup.picasso.Picasso

class InfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Titulo de activity en la barra superior
        title = intent?.extras?.getString("titulo").toString()

        //Argumentos importados
        val arg1 = intent?.extras?.getString("imagen").toString()
        val arg2 = intent?.extras?.getString("descripcion").toString()

        //Mostrar datos
        Picasso.get().load(arg1).into(binding.imageView)
        binding.textView.text = arg2
    }
}