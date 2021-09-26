package com.luizcorrea.calculoplacasolar

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val emptyValue = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
    }

    private fun setListeners() {
        btn_Calcular.setOnClickListener {

            val watts = tv_entWatts.text.toString().toFloatOrNull()
            val hours = tv_entHSol.text.toString().toFloatOrNull()
            val consumption = tv_entConsumo.text.toString().toFloatOrNull()

            if (watts != null && hours != null && consumption != null) {
                val production = (watts * hours * (1 - 0.2)) / 1000
                val capacityUnitMonth = production * 30
                val panels = Math.ceil(consumption / capacityUnitMonth)

                if(panels <= 1) {
                    tv_RQuantPainel.text = String.format("%.0f painel solar", panels)
                } else {
                    tv_RQuantPainel.text = String.format("%.0f painéis solares", panels)
                }

                tv_RQuantosWatts.text = String.format("de %.0f watts", watts)

                tv_PUndia_R.text = String.format("%.3f", production)
                tv_PUnMes_R.text = String.format("%.3f", capacityUnitMonth)

                showResult()
                closeKeybloard()

            }
        }

        btn_clear.setOnClickListener {
            clear()
            hideResult()
        }
    }

    private fun clear() {
        tv_entWatts.setText("")
        tv_entHSol.setText("")
        tv_entConsumo.setText("")
    }

    private fun hideResult() {
        cl_result.visibility = View.GONE
    }
    private fun showResult() {
        cl_result.visibility = View.VISIBLE
    }

    private fun closeKeybloard() {
        val view = currentFocus
        view?.let {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken,0)
        }
    }
}