package com.example.rubtsova_diana_pr31_var4

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class KKalkulator : AppCompatActivity() {
    lateinit var loanSlider: SeekBar
    lateinit var loanAmount: TextView
    lateinit var loanTerm: EditText
    lateinit var calculateButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kkalkulator)
        loanSlider = findViewById(R.id.loanSlider)
        loanAmount = findViewById(R.id.loanAmount)
        loanTerm = findViewById(R.id.loanTerm)
        calculateButton = findViewById(R.id.calculateButton)
        loanSlider.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                with(loanAmount) { this?.setText("Сумма кредита: " + (30000 + progress)) }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        calculateButton.setOnClickListener(View.OnClickListener {
            val amount = 30000 + loanSlider.getProgress()
            val term = loanTerm.getText().toString().toInt()
            val payment = calculatePayment(amount, term)
            val intent = Intent(this@KKalkulator, Raschet::class.java)
            intent.putExtra("payment", payment)
            startActivity(intent)
        })
    }

    private fun calculatePayment(amount: Int, term: Int): Double {
        val monthlyPayment: Double
        monthlyPayment = if (term <= 12) {
            amount / term + amount * 0.059
        } else {
            (amount / term + (amount - amount / term)).toDouble()
        }
        return monthlyPayment
    }
}