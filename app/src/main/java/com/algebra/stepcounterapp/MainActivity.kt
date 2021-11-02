package com.algebra.stepcounterapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity( ) {

    private val chartValues = mutableListOf<BarEntry>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate( savedInstanceState )
        setContentView( R.layout.activity_main )

        showGraph( )

        bCheck.setOnClickListener {
            val f1 = Random( ).nextInt( 500 ).toFloat( )
            val f2 = Random( ).nextInt( 1000 ).toFloat( )
            updateGraph( f1, f2 )
        }
    }

    private fun showGraph( ) {
        chart.description.isEnabled = false
        chart.setMaxVisibleValueCount(10)
        chart.setPinchZoom(false)

        chart.setDrawBarShadow( true )
        chart.setDrawGridBackground( true )

        val xAxis = chart.xAxis
        xAxis.position = XAxisPosition.BOTTOM
        xAxis.setDrawGridLines( false )
        xAxis.setDrawLabels( false )

        chart.axisLeft.setDrawGridLines(false)
        chart.animateY( 1500 )

        chart.legend.isEnabled = true

        chartValues.add( BarEntry( 0f, 123f ) )
        chartValues.add( BarEntry( 1f, 25f ) )

        val dataSet = BarDataSet( chartValues, "Vrijednosti" )
        dataSet.colors = ColorTemplate.VORDIPLOM_COLORS.asList()
        dataSet.values = chartValues
        dataSet.label
        dataSet.setDrawValues(false)





        chart.legend.setCustom( getLegend( ) )
        val dataSets = mutableListOf<BarDataSet>()
        dataSets.add(dataSet)

        val data = BarData(dataSets as List<IBarDataSet>?)
        chart.data = data
        chart.setFitBars(true)
    }

    fun updateGraph( f1 : Float, f2 : Float ) {
        chartValues[0] = BarEntry( 0f, f1 )
        chartValues[1] = BarEntry( 1f, f2 )
        val dataSet = chart.data.getDataSetByIndex(0) as BarDataSet
        dataSet.values = chartValues
        chart.animateY( 1500 )
        chart.data.notifyDataChanged( )
        chart.notifyDataSetChanged( )
        chart.invalidate( )
    }



    private fun getLegend( ) : MutableList< LegendEntry > {
        val entries = mutableListOf< LegendEntry >( )

        var entry = LegendEntry( )
        entry.formColor = ColorTemplate.VORDIPLOM_COLORS[0]
        entry.label = "Desired"
        entries.add( entry )

        entry = LegendEntry( )
        entry.formColor = ColorTemplate.VORDIPLOM_COLORS[1]
        entry.label = "Actual"
        entries.add( entry )

        return entries
    }
}