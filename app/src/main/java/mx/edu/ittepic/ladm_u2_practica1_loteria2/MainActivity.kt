package mx.edu.ittepic.ladm_u2_practica1_loteria2

import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock.sleep
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mx.edu.ittepic.ladm_u2_practica1_loteria2.databinding.ActivityMainBinding
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding :ActivityMainBinding
    var corrutinaCorriendo=false
    var ganaron=false
    lateinit var hiloBarajar:HiloQueBarajea
    var play=false//cuando arranca la ejecucion esta detenido
    var seestaVerificandocartas=false
    var mandamosMensajeIntermedio=false
    var intervaloEntreCartas=8500L //arranca en modo juego (puede cambiar a modo revision)
    var cartas= arrayListOf<Carta>() //nuestra lista de cartas
    //lateinit var hiloBarajar:HiloQueBarajea
    var indices= arrayOf(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //las cartas tienen 2 audios, en modo juego o en modo revision
        //el modo revision (tx) es mas corto
        //el modo juego (ax) tiene jiribilla y folklore mexicano
        cartas.add(Carta(R.drawable.i1,  R.raw.a1,R.raw.t1))
        cartas.add(Carta(R.drawable.i2,  R.raw.a2,R.raw.t2))
        cartas.add(Carta(R.drawable.i3,  R.raw.a3,R.raw.t3))
        cartas.add(Carta(R.drawable.i4,  R.raw.a4,R.raw.t4))
        cartas.add(Carta(R.drawable.i5,  R.raw.a5,R.raw.t5))
        cartas.add(Carta(R.drawable.i6,  R.raw.a6,R.raw.t6))
        cartas.add(Carta(R.drawable.i7,  R.raw.a7,R.raw.t7))
        cartas.add(Carta(R.drawable.i8,  R.raw.a8,R.raw.t8))
        cartas.add(Carta(R.drawable.i9,  R.raw.a9,R.raw.t9))
        cartas.add(Carta(R.drawable.i10, R.raw.a10,R.raw.t10))
        cartas.add(Carta(R.drawable.i11, R.raw.a11,R.raw.t11))
        cartas.add(Carta(R.drawable.i12, R.raw.a12,R.raw.t12))
        cartas.add(Carta(R.drawable.i13, R.raw.a13,R.raw.t13))
        cartas.add(Carta(R.drawable.i14, R.raw.a14,R.raw.t14))
        cartas.add(Carta(R.drawable.i15, R.raw.a15,R.raw.t15))
        cartas.add(Carta(R.drawable.i16, R.raw.a16,R.raw.t16))
        cartas.add(Carta(R.drawable.i17, R.raw.a17,R.raw.t17))
        cartas.add(Carta(R.drawable.i18, R.raw.a18,R.raw.t18))
        cartas.add(Carta(R.drawable.i19, R.raw.a19,R.raw.t19))
        cartas.add(Carta(R.drawable.i20, R.raw.a20,R.raw.t20))
        cartas.add(Carta(R.drawable.i21, R.raw.a21,R.raw.t21))
        cartas.add(Carta(R.drawable.i22, R.raw.a22,R.raw.t22))
        cartas.add(Carta(R.drawable.i23, R.raw.a23,R.raw.t23))
        cartas.add(Carta(R.drawable.i24, R.raw.a24,R.raw.t24))
        cartas.add(Carta(R.drawable.i25, R.raw.a25,R.raw.t25))
        cartas.add(Carta(R.drawable.i26, R.raw.a26,R.raw.t26))
        cartas.add(Carta(R.drawable.i27, R.raw.a27,R.raw.t27))
        cartas.add(Carta(R.drawable.i28, R.raw.a28,R.raw.t28))
        cartas.add(Carta(R.drawable.i29, R.raw.a29,R.raw.t29))
        cartas.add(Carta(R.drawable.i30, R.raw.a30,R.raw.t30))
        cartas.add(Carta(R.drawable.i31, R.raw.a31,R.raw.t31))
        cartas.add(Carta(R.drawable.i32, R.raw.a32,R.raw.t32))
        cartas.add(Carta(R.drawable.i33, R.raw.a33,R.raw.t33))
        cartas.add(Carta(R.drawable.i34, R.raw.a34,R.raw.t34))
        cartas.add(Carta(R.drawable.i35, R.raw.a35,R.raw.t35))
        cartas.add(Carta(R.drawable.i36, R.raw.a36,R.raw.t36))
        cartas.add(Carta(R.drawable.i37, R.raw.a37,R.raw.t37))
        cartas.add(Carta(R.drawable.i38, R.raw.a38,R.raw.t38))
        cartas.add(Carta(R.drawable.i39, R.raw.a39,R.raw.t39))
        cartas.add(Carta(R.drawable.i40, R.raw.a40,R.raw.t40))
        cartas.add(Carta(R.drawable.i41, R.raw.a41,R.raw.t41))
        cartas.add(Carta(R.drawable.i42, R.raw.a42,R.raw.t42))
        cartas.add(Carta(R.drawable.i43, R.raw.a43,R.raw.t43))
        cartas.add(Carta(R.drawable.i44, R.raw.a44,R.raw.t44))
        cartas.add(Carta(R.drawable.i45, R.raw.a45,R.raw.t45))
        cartas.add(Carta(R.drawable.i46, R.raw.a46,R.raw.t46))
        cartas.add(Carta(R.drawable.i47, R.raw.a47,R.raw.t47))
        cartas.add(Carta(R.drawable.i48, R.raw.a48,R.raw.t48))
        cartas.add(Carta(R.drawable.i49, R.raw.a49,R.raw.t49))
        cartas.add(Carta(R.drawable.i50, R.raw.a50,R.raw.t50))
        cartas.add(Carta(R.drawable.i51, R.raw.a51,R.raw.t51))
        cartas.add(Carta(R.drawable.i52, R.raw.a52,R.raw.t52))
        cartas.add(Carta(R.drawable.i53, R.raw.a53,R.raw.t53))
        cartas.add(Carta(R.drawable.i54, R.raw.a54,R.raw.t54))

        binding.verificar.setEnabled(false)
       // binding.verificar.setBackgroundColor(R.drawable.bordes_redondos);
        binding.pausa.setEnabled(false)
       // binding.pausa.setBackgroundColor(R.drawable.bordes_redondos);

        binding.iniciar.setCompoundDrawablesWithIntrinsicBounds(R.drawable.iniciar, 0, 0, 0)
        binding.pausa.setCompoundDrawablesWithIntrinsicBounds(R.drawable.pausa, 0, 0, 0)
        binding.verificar.setCompoundDrawablesWithIntrinsicBounds(R.drawable.revisar, 0, 0, 0)


        binding.iniciar.setOnClickListener {

            hiloBarajar=HiloQueBarajea(this,indices)//se inicia el hilo barajeo cada que inicia un nuevo juego
            println("Corrutina esta corriendo? "+corrutinaCorriendo)
            if(!corrutinaCorriendo){//si no esta corriendo la corrutina, la encendemos, sino la apagamos

                //binding.iniciar.setText("TERMINAR")
                binding.lblIniciar.setText("Acabar")
                binding.iniciar.setCompoundDrawablesWithIntrinsicBounds(R.drawable.terminar, 0, 0, 0)

                ganaron=false
                play=true
                binding.pausa.setEnabled(true)
                //binding.pausa.setBackgroundColor(Color.parseColor("#A5E45B"));
                correrCartas().cancel()
                correrCartas()
                corrutinaCorriendo=true

            }else{
                play=false
                AlertDialog.Builder(this)
                    .setTitle("¿TERMINAR?")
                    .setMessage("Seguro que quieres TERMINAR la partida")
                    .setPositiveButton("SI"){d,i->
                        ganaron=true
                        play=false
                        correrCartas().cancel()
                        intervaloEntreCartas=8500L
                       // binding.iniciar.setText("INICIAR")
                        binding.lblIniciar.setText("Iniciar")

                        binding.iniciar.setCompoundDrawablesWithIntrinsicBounds(R.drawable.iniciar, 0, 0, 0)

                        binding.verificar.setEnabled(false)
                       // binding.verificar.setBackgroundColor(Color.parseColor("#808080"));
                        binding.pausa.setEnabled(false)
                       // binding.pausa.setBackgroundColor(Color.parseColor("#808080"));
                       // binding.pausa.setText("PAUSA")
                        binding.pausa.setCompoundDrawablesWithIntrinsicBounds(R.drawable.pausa, 0, 0, 0)

                        corrutinaCorriendo=false
                        seestaVerificandocartas=false
                        Glide.with(baseContext)
                            .load(R.drawable.ini)
                            .into(binding.imagen)

                    }
                    .setNegativeButton("Cancelar"){d,i->
                       // binding.pausa.setText("PLAY")
                        Toast.makeText(this,"EL JUEGO SE PAUSARA",Toast.LENGTH_LONG).show()
                        binding.pausa.setCompoundDrawablesWithIntrinsicBounds(R.drawable.play, 0, 0, 0)

                        play=false
                        corrutinaCorriendo=true
                        if (seestaVerificandocartas){
                            binding.verificar.setEnabled(false)
                          //  binding.verificar.setBackgroundColor(Color.parseColor("#808080"));
                        }else{
                            binding.verificar.setEnabled(true)
                          //  binding.verificar.setBackgroundColor(Color.parseColor("#A5E45B"));
                        }

                        d.cancel()
                    }
                    .show()
            }//else

        }
        binding.pausa.setOnClickListener {
            Toast.makeText(this,binding.pausa.text.toString(), Toast.LENGTH_LONG).show()
            if (play){
               // binding.pausa.setText("PLAY")
                binding.pausa.setCompoundDrawablesWithIntrinsicBounds(R.drawable.play, 0, 0, 0)

                play=false
                binding.verificar.setEnabled(true)
                //binding.verificar.setBackgroundColor(Color.parseColor("#A5E45B"));
            }else{
               // binding.pausa.setText("PAUSA")
                binding.pausa.setCompoundDrawablesWithIntrinsicBounds(R.drawable.pausa, 0, 0, 0)
                binding.verificar.setEnabled(false)
               // binding.verificar.setBackgroundColor(Color.parseColor("#808080"));
                play=true
            }
            if (seestaVerificandocartas){
                binding.verificar.setEnabled(false)
            //binding.verificar.setBackgroundColor(Color.parseColor("#808080"));
            }

        }
        binding.verificar.setOnClickListener {
            binding.verificar.setEnabled(false)
            //binding.verificar.setBackgroundColor(Color.parseColor("#808080"));
            Toast.makeText(this,"Espere unos segundos porfavor...", Toast.LENGTH_LONG).show()

            audioVerificar()//corrutina para perder un tiempo


            play=true
            seestaVerificandocartas=true
            binding.verificar.setEnabled(false)
            //binding.verificar.setBackgroundColor(Color.parseColor("#808080"));
            mandamosMensajeIntermedio=true
          //  binding.pausa.setText("PAUSA")
            binding.pausa.setCompoundDrawablesWithIntrinsicBounds(R.drawable.pausa, 0, 0, 0)


        }
    }

    public fun swap(array: Array<Int>, i: Int, j: Int) {
        val temp = array[i]
        array[i] = array[j]
        array[j] = temp
    }
    fun audioVerificar() = GlobalScope.launch {
        var voz:MediaPlayer
        voz = MediaPlayer.create(baseContext, R.raw.revisar)
        runOnUiThread {
            play=false//detenemos la secuencia
            Glide.with(baseContext)
                .load(R.drawable.ini)
                .into(binding.imagen)
            voz.start()
        }
        delay(9000)
        play=true//le damos play
        voz.release()
    }
    fun correrCartas() = GlobalScope.launch {
        var contador=0
        var voz:MediaPlayer
        voz = MediaPlayer.create(baseContext,R.raw.inicio)//esto es para cargar el audio del inicio siempre
        try {
            hiloBarajar.start()

        }catch (e:Exception){
            println(e.message+"/////////////////////////////////////////////////////")
        }
        if(!ganaron&&play){
            //barajamos
            //barajeamelaMasDespacio(indices)
            voz.start()
            delay(9000)
            voz.release();//iniciamos el audio
        }

        while(!ganaron){

            while (play){
                corrutinaCorriendo=true//asignamos que esta corriendo la corrutina
                try{
                    if (seestaVerificandocartas){
                        voz = MediaPlayer.create(baseContext, cartas[indices[contador]].audioAna)
                        intervaloEntreCartas=2000L
                    }else{
                        voz = MediaPlayer.create(baseContext, cartas[indices[contador]].audioAdrian)
                    }
                }catch(e:Exception){
                    println("ERRRRRR"+e.message)
                    runOnUiThread {
                        Toast.makeText(baseContext, "ESPERE UN MOMENTO PORFAVOR", Toast.LENGTH_LONG).show()
                    }
                }
                if (contador<54){
                    runOnUiThread {
                        try{
                            Glide.with(baseContext)
                                .load(cartas[indices[contador]].imagen)
                                .into(binding.imagen)
                        voz.start()
                        }catch(e:Exception){
                            println("ERRRRR_2"+e.message)
                            runOnUiThread {
                                Toast.makeText(baseContext, "ESPERE UN MOMENTO PORFAVOR", Toast.LENGTH_LONG).show()
                            }
                        }
                    }

                    delay(intervaloEntreCartas)
                    voz.release()
                }else{
                    runOnUiThread {
                            Glide.with(baseContext)
                                .load(R.drawable.ini)
                                .into(binding.imagen)
                    voz = MediaPlayer.create(baseContext, R.raw.seacabo)
                    voz.start()
                        //reiniciamos valores para la nueva ronda
                        binding.pausa.setEnabled(false)
                       // binding.pausa.setBackgroundColor(Color.parseColor("#808080"));
                        binding.iniciar.setEnabled(false)
                        //binding.verificar.setBackgroundColor(Color.parseColor("#808080"));

                        //binding.iniciar.setText("INICIAR")
                        binding.lblIniciar.setText("Iniciar")

                        binding.iniciar.setCompoundDrawablesWithIntrinsicBounds(R.drawable.iniciar, 0, 0, 0)

                        corrutinaCorriendo=false
                        seestaVerificandocartas=false
                        intervaloEntreCartas=8500L
                    }
                    delay(9000)
                    runOnUiThread {
                        binding.iniciar.setEnabled(true)
                        //binding.iniciar.setBackgroundColor(Color.parseColor("#A5E45B"));
                    }
                    voz.release()
                    ganaron=false// se pone en false para que quede preparada para la sig ronda

                    return@launch// este nos ayuda a cortarla
                }

                contador++
                println(contador)
            }//play?
            delay(300)
            //println(contadorPausa++)
        }//ganaron?

    }//corrutina


}//clase main

///nuestro hilo
class HiloQueBarajea(puntero:MainActivity,array:Array<Int>) : Thread(){
    var p = puntero
    var random= Random()
    var array=array
    override fun run() {
        super.run()
        //barajeamos
        if (random == null) random = Random()
        val count = array.size
        for (i in count downTo 2) {
            p.swap(array, i - 1, random.nextInt(i))
        }
        for(e in array)println(e)//verificamos que si se hayan mezclado

    }
}
//clase para la carta
class Carta(imagen:Int, audioAdrian:Int,audioAna:Int){

    var imagen = 0
    var audioAdrian = 0
    var audioAna = 0

    init {
        this.imagen = imagen
        this.audioAdrian = audioAdrian
        this.audioAna = audioAna
    }


}

/*
class HiloQueBarajea(puntero:MainActivity) : Thread(){
    var p = puntero

    override fun run() {
        super.run()
        lateinit var voz:MediaPlayer
        voz = MediaPlayer.create(p, R.raw.inicio)//esto es para cargar el audio
        voz.release()
        //barajeamos
        p.runOnUiThread {
            p.barajeamelaMasDespacio(p.indices)
            if(!p.final) {
                try {
                    Glide.with(p)
                        .load(R.drawable.ini)
                        .into(p.binding.imagen)//esto es para cargar la imagen
                    voz = MediaPlayer.create(p, R.raw.inicio)//esto es para cargar el audio
                    voz.release()
                    voz = MediaPlayer.create(p, R.raw.inicio)
                    voz.start();//iniciamos el audio
                }catch (e:Exception){
                    println("HILO********************************* "+e.message)
                }
                println("hola barajar")

            }

        }

    }
}*/
/*
fun barajeamelaMasDespacio(array: Array<Int>) {
    var random= Random()
    if (random == null) random = Random()
    val count = array.size
    for (i in count downTo 2) {
        swap(array, i - 1, random.nextInt(i))
    }
    //for(e in array)println(e)
}
*/
