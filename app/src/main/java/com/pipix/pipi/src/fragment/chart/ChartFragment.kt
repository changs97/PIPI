package com.pipix.pipi.src.fragment.chart

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.pipix.pipi.R
import com.pipix.pipi.config.BaseFragment
import com.pipix.pipi.databinding.FragmentChartBinding
import com.pipix.pipi.src.main.MainActivity




class ChartFragment   : BaseFragment<FragmentChartBinding>(FragmentChartBinding::bind, R.layout.fragment_chart) {

    val args: ChartFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.chartImgbtnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        val data =  args.myArg

        val pta = (data.tpaLeft + data.tpaRight) / 2
        val image1 = binding.chartImageviewAngry
        val image2 = binding.chartImageviewSad
        val image3 = binding.chartImageviewNeutral
        val image4 = binding.chartImageviewSmile
        val text1 = binding.chartTextviewAngry
        val text2 = binding.chartTextviewSad
        val text3 = binding.chartTextviewNeutural
        val text4 = binding.chartTextviewSmile
        when(pta){
            in 0..10 -> {  image4.setImageResource(R.drawable.smile2)
                text4.setTextColor(getColor((activity as MainActivity),R.color.navy))
                text4.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_baseline_lens_24_2, 0, 0)}
            in 10..30 -> {image3.setImageResource(R.drawable.neutral2)
                text3.setTextColor(getColor((activity as MainActivity),R.color.navy))
                text3.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_baseline_lens_24_2, 0, 0)}
            in 30..50 -> {image2.setImageResource(R.drawable.sad2)
                text2.setTextColor(getColor((activity as MainActivity),R.color.navy))
                text2.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_baseline_lens_24_2, 0, 0)}
            else -> {image1.setImageResource(R.drawable.angry2)
                text1.setTextColor(getColor((activity as MainActivity),R.color.navy))
                text1.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_baseline_lens_24_2, 0, 0)}

        }


        var lineChart: LineChart? = null

        lineChart = binding.chart

        lineChart.setPinchZoom(false) // 두손가락으로 줌 설정
        lineChart.isDragEnabled = false
        lineChart.setTouchEnabled(false) // 차트 터치 막기


        val entries = ArrayList<Entry>()
        val entries2 = ArrayList<Entry>()


        //그래프에 들어갈 좌표값 입력

        //왼쪽 데이터 리스트
        if(data.R_250 != null)
            entries.add(Entry(1f, data.R_250!!.toFloat()))
        if(data.R_500 != null)
            entries.add(Entry(2f, data.R_500!!.toFloat()))
        if(data.R_1000 != null)
            entries.add(Entry(3f, data.R_1000!!.toFloat()))
        if(data.R_2000 != null)
            entries.add(Entry(4f, data.R_2000!!.toFloat()))
        if(data.R_4000 != null)
            entries.add(Entry(5f, data.R_4000!!.toFloat()))
        if(data.R_8000 != null)
            entries.add(Entry(6f, data.R_8000!!.toFloat()))





        //오른쪽 데이터 리스트
        if(data.L_250 != null)
            entries2.add(
                Entry(1f, data.L_250!!.toFloat(),
                    ContextCompat.getDrawable((activity as MainActivity).getApplicationContext() ,R.drawable.iconx2))
            )
        if(data.L_500!= null)
            entries2.add(
                Entry(2f, data.L_500!!.toFloat(),
                    ContextCompat.getDrawable((activity as MainActivity).getApplicationContext(),R.drawable.iconx2))
            )
        if(data.L_1000 != null)
            entries2.add(
                Entry(3f, data.L_1000!!.toFloat(),
                    ContextCompat.getDrawable((activity as MainActivity).getApplicationContext(),R.drawable.iconx2))
            )
        if(data.L_2000 != null)
            entries2.add(
                Entry(4f, data.L_2000!!.toFloat(),
                    ContextCompat.getDrawable((activity as MainActivity).getApplicationContext(),R.drawable.iconx2))
            )
        if(data.L_4000 != null)
            entries2.add(
                Entry(5f, data.L_4000!!.toFloat(),
                    ContextCompat.getDrawable((activity as MainActivity).getApplicationContext(),R.drawable.iconx2))
            )
        if(data.L_8000 != null)
            entries2.add(
                Entry(6f, data.L_8000!!.toFloat(),
                    ContextCompat.getDrawable((activity as MainActivity).getApplicationContext(),R.drawable.iconx2))
            )


        //디자인 부분
        val chartData = LineData()
        val set1 = LineDataSet(entries, " : 왼쪽 귀")
        chartData.addDataSet(set1)
        set1.setColor(Color.RED)
        set1.lineWidth = 1F
        set1.circleRadius = 10F
        set1.setDrawCircleHole(true)
        set1.circleHoleRadius = 7F
        set1.setCircleColor(Color.RED)
        set1.setDrawValues(false)



        val set2 = LineDataSet(entries2, " : 오른쪽 귀")
        chartData.addDataSet(set2)
        set2.setColor(Color.BLUE)
        set2.setDrawValues(false)
        set2.lineWidth = 1F
        set2.setDrawCircles(false)
        set2.setDrawCircleHole(false)
        set2.setDrawIcons(set2.isDrawIconsEnabled())



        //Y축 레이블을 모두 나타내기 위한 틀
        val entries3 = ArrayList<Entry>()

        entries3.add(Entry(0f, -10f))
        entries3.add(Entry(0f, 0f))
        entries3.add(Entry(0f, 10f))
        entries3.add(Entry(0f, 20f))
        entries3.add(Entry(0f, 30f))
        entries3.add(Entry(0f, 40f))
        entries3.add(Entry(7f, 50f))
        entries3.add(Entry(7f, 60f))
        entries3.add(Entry(7f, 70f))
        entries3.add(Entry(7f, 80f))
        entries3.add(Entry(7f, 90f))
        entries3.add(Entry(7f, 100f))

        val set3 = LineDataSet(entries3,"test")
        chartData.addDataSet(set3)
        set3.setColor(ContextCompat.getColor((activity as MainActivity), R.color.transparency))
        set3.setDrawCircles(false)
        set3.setDrawCircleHole(false)
        set3.valueTextColor = ContextCompat.getColor((activity as MainActivity), R.color.transparency)

        lineChart.data = chartData


        lineChart.setDrawBorders(true)


        // Description set
        lineChart.setDescription(null) //차트에서 Description 설정 저는 따로 안했습니다.


        // Legend set
        lineChart.getLegend().setEnabled(false)


        // Axis set
        val xAxis = lineChart.xAxis
        xAxis.setLabelCount(6)
        xAxis.mAxisMaximum = 6.9f
        xAxis.mAxisMinimum = 0.9f

        xAxis.setDrawGridLines(true) // 격자

        val yAxisRight =lineChart.axisRight
        val yAxisLeft = lineChart.axisLeft
        yAxisRight.isInverted = true
        yAxisLeft.isInverted = true


        yAxisLeft.setDrawLabels(false)
        yAxisLeft.setDrawAxisLine(false)
        yAxisLeft.setDrawGridLines(false)

        yAxisRight.setDrawLabels(true)
        //yAxisRight.setDrawGridLines(true) // 격자
        yAxisRight.axisMaximum = 109f
        yAxisRight.axisMinimum = -19f //
        //yAxisRight.granularity = 10f // 10 단위마다 선을 그리려고 granularity 설정 해 주었다.
        yAxisRight.setLabelCount(12)



        xAxis.valueFormatter = MyXAxisFormatter()
        yAxisRight.valueFormatter = MyYAxisFormatter()



        lineChart.animateX(1000) //X축 애니메이션

        lineChart.invalidate()



    }

    inner class MyXAxisFormatter : ValueFormatter() {
        private val Hz = arrayOf("250", "500","1000","2000", "4000", "8000")
        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            return Hz.getOrNull(value.toInt() - 1) ?:""
        }
    }

    inner class MyYAxisFormatter : ValueFormatter() {
        private val dB = arrayOf("100dB","90dB","80dB","70dB","60dB","50dB","40dB","30dB","20dB","10dB","0dB","-10dB")
        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            return dB.getOrNull(value.toInt() - 1) ?: value.toInt().toString() + "dB" }
    }


}