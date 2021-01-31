package com.convert.mypiechart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class PieChartActivity : AppCompatActivity() {
    lateinit var  number:EditText;
    lateinit var  pieChart:PieChartView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pie_chart)
        number = findViewById(R.id.et_number)
        pieChart = findViewById(R.id.pcView)
    }
    fun click(v: View){
        var number = number.text.toString().toInt()
        pieChart.setSweepAngle(number)
    }
}