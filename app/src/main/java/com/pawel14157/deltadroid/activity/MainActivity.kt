package com.pawel14157.deltadroid.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.pawel14157.deltadroid.R
import com.pawel14157.deltadroid.dto.Delta
import com.pawel14157.deltadroid.service.ResolveService
import kotlinx.android.synthetic.main.activity_resolve.*
import kotlin.math.sqrt


class MainActivity : AppCompatActivity() {

    private val resolveService = ResolveService()
    private var inputA: EditText? = null
    private var inputB: EditText? = null
    private var inputC: EditText? = null
    private var chart: Boolean = false
    private var delta: Delta? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resolve)

        inputA = findViewById<View>(R.id.inputA) as EditText
        inputB = findViewById<View>(R.id.inputB) as EditText
        inputC = findViewById<View>(R.id.inputC) as EditText
    }

    fun resolveDelta(view: View) {
        val deltaTextView = findViewById<View>(R.id.delta) as TextView
        val sqrtDelta = findViewById<View>(R.id.sqrtDelta) as TextView
        val x1 = findViewById<View>(R.id.x1) as TextView
        val x2 = findViewById<View>(R.id.x2) as TextView
        val p = findViewById<View>(R.id.p) as TextView
        val q = findViewById<View>(R.id.q) as TextView
        val imageA = findViewById<View>(R.id.imageA) as TextView
        val imageB = findViewById<View>(R.id.imageB) as TextView
        val imageC = findViewById<View>(R.id.imageC) as TextView
        showKeyboard()
        when {
            isInputsEmpty() -> toast(Toast.makeText(applicationContext, R.string.inputData, Toast.LENGTH_SHORT))
            isAEqualZero() -> toast(Toast.makeText(applicationContext, R.string.A, Toast.LENGTH_SHORT))
            else -> {
                val a = java.lang.Double.parseDouble(inputA!!.text.toString())
                val b = java.lang.Double.parseDouble(inputB!!.text.toString())
                val c = java.lang.Double.parseDouble(inputC!!.text.toString())

                setFormulaNumbers(imageA, a, b, imageB, c, imageC)

                delta = resolveAll(a, b, c)
                deltaTextView.text = "Δ=" + round(delta!!.delta).toString()
                sqrtDelta.text = "√Δ=" + round(delta!!.sqrtDelta).toString()

                setTextView(delta!!, x1, x2)
                chart = true
            }
        }
    }

    private fun toast(toast: Toast) {
        toast.show()
    }

    private fun setFormulaNumbers(imageA: TextView, a: Double, b: Double, imageB: TextView,
                                  c: Double, imageC: TextView) {
        imageA.text = checkNumber(a)
        if (b >= 0) imageB.text = "+" + checkNumber(b) else imageB.text = checkNumber(b)
        if (c >= 0) imageC.text = "+" + checkNumber(c) else imageC.text = checkNumber(c)
    }

    private fun setTextView(delta: Delta, x1: TextView, x2: TextView) {
        if (delta.x1 != null && delta.x2 != null) {
            x1.text = "x1=" + round(delta.x1!!).toString()
            x2.text = "x2=" + round(delta.x2!!).toString()
        } else if (delta.x0 != null) {
            x1.text = "x=" + round(delta.x0!!).toString()
            x2.text = ""
        } else {
            x1.setText(R.string.notExist)
            x2.text = ""
        }
        p.text = "p=" + round(delta.p).toString()
        q.text = "q=" + round(delta.q).toString()
    }

    private fun isAEqualZero() = inputA!!.text.toString() == "0"

    private fun isInputsEmpty() =
            inputA!!.text.toString().isEmpty() || inputB!!.text.toString().isEmpty() || inputC!!.text.toString().isEmpty()

    private fun showKeyboard() {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
    }

    private fun checkNumber(number: Double): String {
        val checkNumberString: String
        val stringBuilder = StringBuilder()
        if (number % 1.0 == 0.0) {
            checkNumberString = number.toString()
            for (i in 0 until checkNumberString.length) {
                if (checkNumberString[i] == '.') {
                    break
                } else {
                    stringBuilder.append(checkNumberString[i])
                }
            }
        } else {
            return number.toString()
        }
        return stringBuilder.toString()
    }

    fun makeChart(view: View) {
        if (chart == true) {
            val intent = Intent(applicationContext, ChartActivity::class.java)
            intent.putExtra("a", delta?.a)
            intent.putExtra("b", delta?.b)
            intent.putExtra("c", delta?.c)
            intent.putExtra("x1", delta?.x1)
            intent.putExtra("x2", delta?.x2)
            intent.putExtra("x0", delta?.x2)
            intent.putExtra("p", delta?.p)
            intent.putExtra("q", delta?.q)
            chart = false
            startActivity(intent)
        } else {
            Toast.makeText(this, R.string.resolveEquation, Toast.LENGTH_SHORT).show()
        }
    }

    private fun resolveAll(a: Double, b: Double, c :Double): Delta {
        val delta = resolveService.resolveDelta(a, b, c)
        val sqrtDelta = sqrt(delta)
        val p = resolveService.resolveTopP(a, b)
        val q = resolveService.resolveTopQ(a, delta)
        var x1: Double? = null
        var x2: Double? = null
        var x0: Double? = null
        if (isDeltaGreaterThenZero(delta)) {
            x1 = resolveService.deltaIsGreaterThenZeroX1(a, b, sqrtDelta)
            x2 = resolveService.deltaIsGreaterThenZeroX2(a, b, sqrtDelta)
        }
        else if (delta.equals(0.0)) {
            x0 = resolveService.deltaIsEqualsZero(a, b)
        }
        return Delta(a, b, c, delta, sqrtDelta, x1, x2, x0, p, q)
    }

    private fun isDeltaGreaterThenZero(delta: Double) = delta.compareTo(0.0) == 1

    private fun round(number: Double): Double {
        var number = number
        number = Math.round(number * 100).toDouble()
        number = number / 100
        return number
    }
}



