package com.flavio.android.megasorteio.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.flavio.android.megasorteio.R
import com.flavio.android.megasorteio.dao.ApostaDao
import com.flavio.android.megasorteio.dao.SequenciaDao
import com.flavio.android.megasorteio.model.Aposta
import com.flavio.android.megasorteio.model.Sequencia

class MostrarApostaUnitaria : AppCompatActivity() {
    lateinit var aposta : Aposta
    lateinit var ad: ApostaDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_aposta_unitaria)

        this.aposta = intent.extras.get("aposta") as Aposta
        Toast.makeText(this, aposta.mostraTodasSequencias(),Toast.LENGTH_SHORT).show()

        ad = ApostaDao(this)
        aposta.idAposta = ad.save(aposta)
        if(aposta.idAposta>=0){
            val sd = SequenciaDao(this)
            var passou = true
            for(sequencia : Sequencia in aposta.sequencias){
                if(sd.save(sequencia,aposta.idAposta.toInt())< 0) passou = false
            }
            if(passou) Toast.makeText(this,"Aposta inserida com sucesso!",Toast.LENGTH_LONG).show()
            else Toast.makeText(this,"Falha ao salvar sequencias!",Toast.LENGTH_LONG).show()

        }

    }
}