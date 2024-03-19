package com.example.myapp

import android.os.Bundle
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
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Pie
import com.example.myapp.profile.AdapterForCategory
import com.example.myapp.profile.CategoryWithMoney
import com.example.myapp.profile.UserList
import com.example.myapp.profile.createNewCategory.CreateNewCategoryFragment
import com.example.myapp.profile.editProfile.EditProfileFragment

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val employelist= CategoryWithMoney.getCategoryWithMoney()

        val itemAdapter= AdapterForCategory(employelist)

        val recyclerView: RecyclerView =view.findViewById(R.id.rcViewExpences)
        recyclerView.layoutManager = LinearLayoutManager(context)

        recyclerView.adapter = itemAdapter

        var btn: ViewGroup = view.findViewById(R.id.reportForYear)
        val navController = findNavController()
        btn.setOnClickListener{

            navController.navigate(R.id.action_profileFragment_to_reportForYearFragment)
        }

        var editBtn: ViewGroup = view.findViewById(R.id.editBtn)

        editBtn.setOnClickListener{
            navController.navigate(R.id.action_profileFragment_to_editProfileFragment)
        }

        var createCategoryBtn: ViewGroup = view.findViewById(R.id.createCategoryBtn2)

        createCategoryBtn.setOnClickListener{
            navController.navigate(R.id.action_profileFragment_to_createNewCategoryFragment)
        }

        //profile card

        var user_fullname: TextView = view.findViewById(R.id.user_fullname)
        user_fullname.text = StringBuilder().append(UserList.getUserList().get(1).name).append(" ").append(
            UserList.getUserList().get(1).surname).toString()
        var user_email: TextView = view.findViewById(R.id.user_email)
        user_email.text = UserList.getUserList().get(1).email
        var user_balance: TextView = view.findViewById(R.id.user_balance)
        user_balance.text = UserList.getUserList().get(1).balance.toString()
        var user_income: TextView = view.findViewById(R.id.user_income)
        user_income.text = UserList.getUserList().get(1).income.toString()
        var user_expense: TextView = view.findViewById(R.id.user_expense)
        user_expense.text = UserList.getUserList().get(1).outcome.toString()

        var anyChartView: AnyChartView = view.findViewById(R.id.any_chart_view)
        var pie: Pie = AnyChart.pie()
        var data = ArrayList<DataEntry>()
        data.add(ValueDataEntry("Food", 10000))
        data.add(ValueDataEntry("Taxi", 10000))
        data.add(ValueDataEntry("Shop", 10000))
        data.add(ValueDataEntry("Telephone", 10000))
        data.add(ValueDataEntry("Go out", 10000))
        data.add(ValueDataEntry("Present", 10000))
        data.add(ValueDataEntry("Deposit", 10000))

        pie.data(data)
        anyChartView.setChart(pie)

        //view.findViewById<TextView>(R.id.user_fullname).text = "${EditProfileFragment.currentUser.name} ${EditProfileFragment.currentUser.surname}"
        //view.findViewById<TextView>(R.id.user_email).text = "${EditProfileFragment.currentUser.email}"

    }

}