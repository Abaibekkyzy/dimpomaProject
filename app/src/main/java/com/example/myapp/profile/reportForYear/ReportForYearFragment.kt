package com.example.myapp.profile.reportForYear

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.enums.TooltipPositionMode
import com.example.myapp.ProfileFragment
import com.example.myapp.R
import com.example.myapp.profile.AdapterForCategory
import com.example.myapp.profile.CategoryWithMoney
import com.example.myapp.profile.UserList

class ReportForYearFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report_for_year, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // getting the employeelist
        val employelist= CategoryWithMoney.getCategoryWithMoney()

        // Assign employeelist to ItemAdapter
        val itemAdapter= AdapterForCategory(employelist)

        // Set the LayoutManager that
        // this RecyclerView will use.
        val recyclerView: RecyclerView =view.findViewById(R.id.rcViewExpences)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // adapter instance is set to the
        // recyclerview to inflate the items.
        recyclerView.adapter = itemAdapter
        val navController = findNavController()

        var btn: ViewGroup = view.findViewById(R.id.backToProfile)

        btn.setOnClickListener{
            // Переключение на другой фрагмент
//            val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
//            val newFragment = ProfileFragment() // замените NewFragment на ваш фрагмент
//            fragmentTransaction.replace(R.id.nav_host_fragment, newFragment)
//            fragmentTransaction.addToBackStack(null)
//            fragmentTransaction.commit()
            navController.navigate(R.id.action_reportForYearFragment_to_profileFragment)

        }

        var user_expense: TextView = view.findViewById(R.id.user_expense)
        user_expense.text = UserList.getUserList().get(1).outcome.toString()

        var user_income: TextView = view.findViewById(R.id.user_income)
        user_income.text = UserList.getUserList().get(1).income.toString()

       // barChart
        val anyChartView: AnyChartView = view.findViewById(R.id.barChart)
        val barChart = AnyChart.column()

        val data = listOf(
            ValueDataEntry("Jan", 145540),
            ValueDataEntry("Feb", 264190),
            ValueDataEntry("Mar", 102610),
            ValueDataEntry("Apl", 110430),
            ValueDataEntry("May", 128000),
            ValueDataEntry("Jun", 143760),
            ValueDataEntry("July", 170670),
            ValueDataEntry("Aug", 213210),
            ValueDataEntry("Sep", 249980),
            ValueDataEntry("Oct", 170670),
            ValueDataEntry("Nov", 213210),
            ValueDataEntry("Dec", 249980)
        )

        barChart.column(data)

        barChart.xAxis(0).labels().fontSize(8) // Устанавливаем размер текста для меток на оси X
        barChart.yAxis(0).labels().fontSize(8)

        barChart.animation(true)
        barChart.title("12 month incomes")
        barChart.yScale().minimum(0.0)

        barChart.yAxis(0).labels().format("{%Value}{groupsSeparator: }").enabled(true)

        barChart.tooltip().positionMode(TooltipPositionMode.POINT)

        barChart.xAxis(0).title("Income")
        barChart.yAxis(0).title("Month")
        barChart.background(true)
        barChart.background("#EBEBEB")
        barChart.palette(arrayOf("#B0ABFF"))

        anyChartView.setChart(barChart)

        //setupBarChart(view)
        //setupPieChart(view)

        //pie chart any_chart_view2

//        var pieChartView: AnyChartView = view.findViewById(R.id.any_chart_view2)
//        var pie: Pie = AnyChart.pie()
//        var data2 = ArrayList<DataEntry>()
//        data2.add(ValueDataEntry("Food", 10000))
//        data2.add(ValueDataEntry("Taxi", 10000))
//        data2.add(ValueDataEntry("Shop", 10000))
//        data2.add(ValueDataEntry("Telephone", 10000))
//        data2.add(ValueDataEntry("Go out", 10000))
//        data2.add(ValueDataEntry("Present", 10000))
//        data2.add(ValueDataEntry("Deposit", 10000))
//
//        pie.data(data2)
//        pieChartView.setChart(pie)

//        val fragmentManager = childFragmentManager // или childFragmentManager, если вы вложены в другой фрагмент
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        val innerFragment = diagramForReportFragment()
//        fragmentTransaction.add(R.id.fragmentDiagram, innerFragment)
//        fragmentTransaction.commit()



    }

    private fun setupBarChart(view: View) {
        val anyChartView: AnyChartView = view.findViewById(R.id.barChart)
        val barChart = AnyChart.column()

        val data = listOf(
            ValueDataEntry("Jan", 145540),
            ValueDataEntry("Feb", 264190),
            ValueDataEntry("Mar", 102610),
            ValueDataEntry("Apl", 110430),
            ValueDataEntry("May", 128000),
            ValueDataEntry("Jun", 143760),
            ValueDataEntry("July", 170670),
            ValueDataEntry("Aug", 213210),
            ValueDataEntry("Sep", 249980),
            ValueDataEntry("Oct", 170670),
            ValueDataEntry("Nov", 213210),
            ValueDataEntry("Dec", 249980)
        )

        barChart.column(data)

        barChart.xAxis(0).labels().fontSize(8)
        barChart.yAxis(0).labels().fontSize(8)

        barChart.animation(true)
        barChart.title("12 month incomes")
        barChart.yScale().minimum(0.0)

        barChart.yAxis(0).labels().format("{%Value}{groupsSeparator: }").enabled(true)

        barChart.tooltip().positionMode(TooltipPositionMode.POINT)

        barChart.xAxis(0).title("Income")
        barChart.yAxis(0).title("Month")
        barChart.background(true)
        barChart.background("#000000")
        barChart.palette(arrayOf("#B0ABFF"))

        anyChartView.setChart(barChart)
        Log.d("SetupBarChart", "Setting up bar chart")
    }




//    private fun setupPieChart(view: View) {
//        val pieChartView: AnyChartView = view.findViewById(R.id.any_chart_view2)
//        val pie: Pie = AnyChart.pie()
//        val data2 = arrayListOf<DataEntry>(
//            ValueDataEntry("Food", 10000),
//            ValueDataEntry("Taxi", 10000),
//            ValueDataEntry("Shop", 10000),
//            ValueDataEntry("Telephone", 10000),
//            ValueDataEntry("Go out", 10000),
//            ValueDataEntry("Present", 10000),
//            ValueDataEntry("Deposit", 10000)
//        )
//
//        pie.data(data2)
//        pieChartView.setChart(pie)
//        Log.d("SetupPieChart", "Setting up pie chart")
//    }


}