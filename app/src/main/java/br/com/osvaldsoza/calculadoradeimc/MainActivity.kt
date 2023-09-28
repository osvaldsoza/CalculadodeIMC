package br.com.osvaldsoza.calculadoradeimc


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import br.com.osvaldsoza.calculadoradeimc.databinding.ActivityMainBinding
import java.text.DecimalFormat
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnCalcular.setOnClickListener {
            if (binding.edtPeso.text.toString().isEmpty()) {
                binding.txtMensagem.setText("Informe o seu peso")
            } else if (binding.edtAltura.text.toString().isEmpty()) {
                binding.txtMensagem.setText("Informe a sua altura")
            } else {
                calcularIMC()
            }
        }

    }

    private fun calcularIMC() {

        val pesoId = binding.edtPeso
        val alturaId = binding.edtAltura

        val pesso = pesoId.text.toString().toInt()
        val altura = alturaId.text.toString().toFloat()
        val imc = pesso / (altura * altura)

        val mensagem = when {
            imc <= 18.5 -> "Peso Baixo"
            imc <= 24.9 -> "Peso Normal"
            imc <= 29.9 -> "Sobrepeso"
            imc <= 34.9 -> "Obsidade Grau I"
            imc <= 39.9 -> "Obsidade Grau II"
            else -> "Obsidade Mórbida"
        }

        binding.txtMensagem.setText("IMC: $imc \n $mensagem")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.menu_principal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.reset -> {
                binding.edtPeso.setText("")
                binding.edtAltura.setText("")
                binding.txtMensagem.setText("")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}