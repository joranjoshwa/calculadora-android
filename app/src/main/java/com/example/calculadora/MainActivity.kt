package com.example.calculadora

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.*
import net.objecthunter.exp4j.ExpressionBuilder
import net.objecthunter.exp4j.function.Function

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var mr = 0.0
        var temp1 = 0.0
        var temp2 = 0.0
        var operacao = 0
        var result = 0.0
        var isResult = false

        val display = findViewById<TextView>(R.id.display)
        val btn0 = findViewById<TextView>(R.id.btnZero)
        val btn1 = findViewById<TextView>(R.id.btnUm)
        val btn2 = findViewById<TextView>(R.id.btnDois)
        val btn3 = findViewById<TextView>(R.id.btnTres)
        val btn4 = findViewById<TextView>(R.id.btnQuatro)
        val btn5 = findViewById<TextView>(R.id.btnCinco)
        val btn6 = findViewById<TextView>(R.id.btnSeis)
        val btn7 = findViewById<TextView>(R.id.btnSete)
        val btn8 = findViewById<TextView>(R.id.btnOito)
        val btn9 = findViewById<TextView>(R.id.btnNove)
        val btnCE = findViewById<TextView>(R.id.btnCE)
        val btnMR = findViewById<TextView>(R.id.btnMR)
        val btnMmais = findViewById<TextView>(R.id.btnMmais)
        val btnMmenos = findViewById<TextView>(R.id.btnMmenos)
        val btnDividir = findViewById<TextView>(R.id.btnDividir)
        val btnVezes = findViewById<TextView>(R.id.btnVezes)
        val btnMenos = findViewById<TextView>(R.id.btnMenos)
        val btnMais = findViewById<TextView>(R.id.btnMais)
        val btnResultado = findViewById<TextView>(R.id.btnResultado)
        val btnVirgula = findViewById<TextView>(R.id.btnVirgula)

        val btnSin = findViewById<TextView>(R.id.btnSin)
        val btnCos = findViewById<TextView>(R.id.btnCos)
        val btnTan = findViewById<TextView>(R.id.btnTan)
        val btnAsin = findViewById<TextView>(R.id.btnAsin)
        val btnAcos = findViewById<TextView>(R.id.btnAcos)
        val btnAtan = findViewById<TextView>(R.id.btnAtan)

        val btnLog = findViewById<TextView>(R.id.btnLog)
        val btnLn = findViewById<TextView>(R.id.btnLn)

        val btnXpY = findViewById<TextView>(R.id.btnXpY)
        val btnExp = findViewById<TextView>(R.id.btnExp)
        val btn10x = findViewById<TextView>(R.id.btn10x)

        val btnRaiz = findViewById<TextView>(R.id.btnRaiz)
        val btnRaizN = findViewById<TextView>(R.id.btnRaizN)

        val btnFatorial = findViewById<TextView>(R.id.btnFatorial)

        val btnPi = findViewById<TextView>(R.id.btnPi)
        val btnEuler = findViewById<TextView>(R.id.btnEuler)

        val btnPorcentagem = findViewById<TextView>(R.id.btnPorcentagem)

        val btnModulo = findViewById<TextView>(R.id.btnModulo)

        btnSin.setOnClickListener {
            val valor = display.text.toString().toDouble()
            val resultado = sin(Math.toRadians(valor))
            display.text = resultado.toString()
        }

        btnCos.setOnClickListener {
            val valor = display.text.toString().toDouble()
            val resultado = cos(Math.toRadians(valor))
            display.text = resultado.toString()
        }

        btnTan.setOnClickListener {
            val valor = display.text.toString().toDouble()
            val resultado = tan(Math.toRadians(valor))
            display.text = resultado.toString()
        }

        btnAsin.setOnClickListener {
            val valor = display.text.toString().toDouble()
            val resultado = Math.toDegrees(asin(valor))
            display.text = resultado.toString()
        }

        btnAcos.setOnClickListener {
            val valor = display.text.toString().toDouble()
            val resultado = Math.toDegrees(acos(valor))
            display.text = resultado.toString()
        }

        btnAtan.setOnClickListener {
            val valor = display.text.toString().toDouble()
            val resultado = Math.toDegrees(atan(valor))
            display.text = resultado.toString()
        }


        btnRaiz.setOnClickListener {
            val valor = display.text.toString().toDoubleOrNull()
            if (valor != null && valor >= 0) {
                val resultado = Math.sqrt(valor)
                display.text = resultado.toString()
            } else {
                display.text = "Erro"
            }
        }

        btnRaizN.setOnClickListener {
            val input = display.text.toString()

            try {
                val parts = input.split("#")
                if (parts.size == 2) {
                    val indice = parts[0].toDouble()
                    val radicando = parts[1].toDouble()
                    val resultado = Math.pow(radicando, 1.0 / indice)
                    display.setText(resultado.toString())
                    isResult = true
                } else {
                    display.setText("Erro")
                }
            } catch (e: Exception) {
                display.setText("Erro")
            }
        }


        btnFatorial.setOnClickListener {
            val valor = display.text.toString().toDoubleOrNull()
            if (valor != null && valor >= 0 && valor == valor.toInt().toDouble()) {
                val inteiro = valor.toInt()
                var resultado = 1L
                for (i in 2..inteiro) {
                    resultado *= i
                }
                display.text = resultado.toString()
            } else {
                display.text = "Erro"
            }
        }


        btnPi.setOnClickListener {
            display.text = Math.PI.toString()
        }

        btnEuler.setOnClickListener {
            display.text = Math.E.toString()
        }


        var base: Double? = null

        btnXpY.setOnClickListener {
            val valorAtual = display.text.toString()

            if (base == null) {

                base = valorAtual.toDoubleOrNull() ?: 0.0
                display.setText("0")
            } else {

                val expoente = valorAtual.toDoubleOrNull() ?: 1.0
                val resultado = Math.pow(base!!, expoente)
                display.setText(resultado.toString())
                base = null
            }
        }


        btnExp.setOnClickListener {
            val valor = display.text.toString().toDoubleOrNull()
            if (valor != null) {
                val resultado = Math.exp(valor)
                display.text = resultado.toString()
            } else {
                display.text = "Erro"
            }
        }


        btn10x.setOnClickListener {
            val valor = display.text.toString().toDoubleOrNull()
            if (valor != null) {
                val resultado = Math.pow(10.0, valor)
                display.text = resultado.toString()
            } else {
                display.text = "Erro"
            }
        }


        btnLog.setOnClickListener {
            val valor = display.text.toString().toDoubleOrNull()
            if (valor != null && valor > 0) {
                val resultado = Math.log10(valor)
                display.text = resultado.toString()
            } else {
                display.text = "Erro"
            }
        }

        btnLn.setOnClickListener {
            val texto = display.text.toString().replace(",", ".")
            val valor = texto.toDoubleOrNull()
            if (valor != null && valor > 0) {
                val resultado = Math.log(valor)
                display.text = resultado.toString()
            } else {
                display.text = "Erro"
            }
        }


        btnPorcentagem.setOnClickListener {
            val valor = display.text.toString().toDoubleOrNull()
            if (valor != null) {
                val resultado = valor / 100
                display.text = resultado.toString()
            } else {
                display.text = "Erro"
            }
        }


        btnModulo.setOnClickListener {
            val valor = display.text.toString().toDoubleOrNull()
            if (valor != null) {
                val resultado = Math.abs(valor)
                display.text = resultado.toString()
            } else {
                display.text = "Erro"
            }
        }


        btn0.setOnClickListener{
            if (isResult) {
                display.setText("0")
                isResult = false
            } else {
                if (display.text.toString() != "0") {
                    display.setText(display.text.toString().plus("0"))
                }
            }
        }

        btn1.setOnClickListener {
            if (display.text.toString() == "0" || isResult) {
                display.text = "1"
                isResult = false
            } else {
                display.text = display.text.toString().plus("1")
            }
        }

        btn2.setOnClickListener {
            if (display.text.toString() == "0" || isResult) {
                display.text = "2"
                isResult = false
            } else {
                display.text = display.text.toString().plus("2")
            }
        }

        btn3.setOnClickListener {
            if (display.text.toString() == "0" || isResult) {
                display.text = "3"
                isResult = false
            } else {
                display.text = display.text.toString().plus("3")
            }
        }

        btn4.setOnClickListener {
            if (display.text.toString() == "0" || isResult) {
                display.text = "4"
                isResult = false
            } else {
                display.text = display.text.toString().plus("4")
            }
        }

        btn5.setOnClickListener {
            if (display.text.toString() == "0" || isResult) {
                display.text = "5"
                isResult = false
            } else {
                display.text = display.text.toString().plus("5")
            }
        }

        btn6.setOnClickListener {
            if (display.text.toString() == "0" || isResult) {
                display.text = "6"
                isResult = false
            } else {
                display.text = display.text.toString().plus("6")
            }
        }

        btn7.setOnClickListener {
            if (display.text.toString() == "0" || isResult) {
                display.text = "7"
                isResult = false
            } else {
                display.text = display.text.toString().plus("7")
            }
        }

        btn8.setOnClickListener {
            if (display.text.toString() == "0" || isResult) {
                display.text = "8"
                isResult = false
            } else {
                display.text = display.text.toString().plus("8")
            }
        }

        btn9.setOnClickListener {
            if (display.text.toString() == "0" || isResult) {
                display.text = "9"
                isResult = false
            } else {
                display.text = display.text.toString().plus("9")
            }
        }

        btnCE.setOnClickListener {
            display.setText("0")
        }

        btnVirgula.setOnClickListener {
            if (!display.text.contains(".")) {
                display.setText(display.text.toString().plus("."))
            }
        }

        btnMR.setOnClickListener{
            display.setText(mr.toString())
        }

        btnMmais.setOnClickListener {
            try {
                val expressao = ExpressionBuilder(display.text.toString()).build()
                val resultado = expressao.evaluate()
                mr += resultado
                display.setText("0")
                isResult = true
            } catch (e: Exception) {
                display.setText("Erro")
            }
        }

        btnMmenos.setOnClickListener {
            try {
                val expressao = ExpressionBuilder(display.text.toString()).build()
                val resultado = expressao.evaluate()
                mr -= resultado
                display.setText("0")
                isResult = true
            } catch (e: Exception) {
                display.setText("Erro")
            }
        }

        btnMais.setOnClickListener {
            if (isResult) {
                display.setText("0")
                isResult = false
            }
            display.setText(display.text.toString() + "+")
        }

        btnMenos.setOnClickListener {
            if (isResult) {
                display.setText("0")
                isResult = false
            }
            display.setText(display.text.toString() + "-")
        }

        btnVezes.setOnClickListener {
            if (isResult) {
                display.setText("0")
                isResult = false
            }
            display.setText(display.text.toString() + "*")
        }

        btnDividir.setOnClickListener {
            if (isResult) {
                display.setText("0")
                isResult = false
            }
            display.setText(display.text.toString() + "/")
        }

        btnResultado.setOnClickListener {
            val expressao = display.text.toString()

            try {
                val resultado = ExpressionBuilder(expressao).build().evaluate()
                display.text = resultado.toString()
                isResult = true
            } catch (e: Exception) {
                display.text = "Erro"
            }
        }



//        btnResultado.setOnClickListener {
//            temp2 = display.text.toString().toDouble()
//
//            when(operacao){
//
//                1 -> result = temp1 + temp2
//                2 -> result = temp1 - temp2
//                3 -> result = temp1 * temp2
//                4 -> result = temp1 / temp2
//                5 -> result = Math.pow(temp1, temp2) // aqui é o ajuste
//                6 -> {
//                    if (temp2 != 0.0) {
//                        result = Math.pow(temp2, 1.0 / temp1)
//                    } else {
//                        display.text = "Erro"
//                        return@setOnClickListener
//                    }
//                }
//
//            }
//            temp1 = 0.0
//            temp2 = 0.0
//            operacao = 0
//            display.setText(result.toString())
//            isResult = true
//            result = 0.0
//        }
    }
}