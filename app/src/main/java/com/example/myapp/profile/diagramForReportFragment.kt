package com.example.myapp.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anychart.AnyChartView
import com.anychart.AnyChart;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.charts.Pie
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.example.myapp.R
//import com.example.myapp.databinding.FragmentDiagramForReportBinding
import com.example.myapp.databinding.FragmentHomeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [diagramForReportFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class diagramForReportFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        val binding = FragmentDiagramForReportBinding.inflate(inflater, container, false)
//        val view = binding.root
//
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
//
//        return inflater.inflate(R.layout.fragment_diagram_for_report, container, false)
//    }
//
//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment diagramForReport.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            diagramForReportFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}