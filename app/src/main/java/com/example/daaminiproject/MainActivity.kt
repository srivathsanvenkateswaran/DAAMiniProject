package com.example.daaminiproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.daaminiproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            val amount = binding.amountEditText.text.toString().toInt()
            binding.answerTextView.text = calculateCoinChange(
                curVal = amount
            )
            binding.answerTextView.visibility = View.VISIBLE
        }
    }

    private fun calculateCoinChange(
        curVal: Int
    ): String {
        var amount = curVal
        Log.d("CoinChange", "Amount: $amount")
        val answerList = mutableListOf<Int>(
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0
        )

        val denominationsList = mutableListOf<Int>(
            2000,
            500,
            100,
            50,
            20,
            10,
            5,
            2,
            1
        )

        var currentIndex = 0

        while (amount > 0) {
            answerList[currentIndex] = amount/denominationsList[currentIndex]
            amount %= denominationsList[currentIndex]

            currentIndex++
        }

        var answer: String = "Currency values\n"
        for (i in 0..8) {
            if (answerList[i] != 0) {
                answer += "${denominationsList[i]} x ${answerList[i]}\n"
            }
        }

        return answer
    }
}