package com.example.myapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment(), NotificationAdapter.OnItemClickListener {

    private lateinit var binding: FragmentNotificationsBinding
    private lateinit var adapter: NotificationAdapter
    private lateinit var notificationsArrayList: ArrayList<Notifications>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataInitialize()
        val layoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.setHasFixedSize(true)
        adapter = NotificationAdapter(notificationsArrayList)
        binding.recyclerView.adapter = adapter
        adapter.setOnItemClickListener(this)
    }

    private fun dataInitialize() {
        notificationsArrayList = ArrayList()

        notificationsArrayList.add(
            Notifications(
                R.drawable.bonus_notification,
                "Kaspi Bonus",
                "A bonus of 15 000 tenge has been credited to your account.\n" +
                        "Check your balance.\n" +
                        "Available: 77 275 tenge.\n",
                "today",
                "21:09"
            )
        )
        notificationsArrayList.add(
            Notifications(
                R.drawable.deposit_notification,
                "Kaspi Deposit",
                "Kaspi Deposit D-028\n" +
                        "Transfer: 20 000 tenge\n" +
                        "On Kaspi Gold\n" +
                        "On Deposit: 78 265 tenge\n",
                "yesterday",
                "11:25"
            )
        )
        notificationsArrayList.add(
            Notifications(
                R.drawable.shop_notification,
                "Kaspi Store",
                "Thank you for your order No. 30025614.\n" +
                        "The application has been approved by the bank. Order details: Essence The Blush 01 Peony. Cosmart store.\n" +
                        "Your order has been transferred to the delivery service.\n" +
                        "The order will be delivered to Kaspi Postomat on February 15.\n",
                "27.01.24",
                "17:03"
            )
        )
    }


    override fun onItemClick(notifications: Notifications) {
        val intent = Intent(context, NewActivity::class.java)
        intent.putExtra("imageResource", notifications.titleImage)
        intent.putExtra("description", notifications.description)
        intent.putExtra("data", notifications.data)
        intent.putExtra("time", notifications.time)
        startActivity(intent)
    }
}