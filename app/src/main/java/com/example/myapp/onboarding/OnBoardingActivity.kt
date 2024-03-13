package com.example.myapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Intent
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class OnBoardingActivity : AppCompatActivity() {


    private val onBoardingPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            when (position) {
                0 -> {
                    skipBtn.text = "Skip"
                    skipBtn.visible()
                    nextBtn.visible()
                    previousBtn.gone()
                }

                pagerList.size - 1 -> {
                    skipBtn.text = "Get Started"
                    nextBtn.gone()
                    skipBtn.visible()
                    previousBtn.visible()

                }

                else -> {
                    skipBtn.text = "Skip"
                    skipBtn.visible()
                    nextBtn.visible()
                    previousBtn.visible()
                }
            }
        }
    }

    private val pagerList = arrayListOf(
        Page(
            "Track expenses and income",
            R.drawable.first,
            "Welcome to Basqaru!\n" +
                    "By managing your finances with us, you're taking a proactive step towards financial stability and success."
        ),
        Page(
            "Plan budgets and goals",
            R.drawable.second,
            "Get ready to take control of your finances by planning budgets.\n" +
                    "Let's start planning for your financial success!"
        ),
        Page(
            "Increasing your financial literacy",
            R.drawable.third,
            "Let's embark on this journey to enhance your financial understanding and secure your financial future!\n" +
                    "Let's dive in:)"
        )
    )

    lateinit var onBoardingViewPager2: ViewPager2
    lateinit var skipBtn: Button
    lateinit var nextBtn: Button
    lateinit var previousBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        onBoardingViewPager2 = findViewById(R.id.onBoardingViewPager2)
        skipBtn = findViewById(R.id.skipBtn)
        nextBtn = findViewById(R.id.nextBtn)
        previousBtn = findViewById(R.id.previousBtn)

        onBoardingViewPager2.apply {
            adapter = OnBoardingAdapter(this@OnBoardingActivity, pagerList)
            registerOnPageChangeCallback(onBoardingPageChangeCallback)
            (getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        TabLayoutMediator(tabLayout, onBoardingViewPager2) { tab, position -> }.attach()


        nextBtn.setOnClickListener {
            if (onBoardingViewPager2.currentItem < onBoardingViewPager2.adapter!!.itemCount - 1) {
                onBoardingViewPager2.currentItem += 1
            } else {
                homeScreenIntent()
            }
        }

        skipBtn.setOnClickListener {
            homeScreenIntent()
        }

        previousBtn.setOnClickListener {
            if (onBoardingViewPager2.currentItem > 0) {
                onBoardingViewPager2.currentItem -= 1
            }
        }
    }

    override fun onDestroy() {
        onBoardingViewPager2.unregisterOnPageChangeCallback(onBoardingPageChangeCallback)
        super.onDestroy()
    }

    private fun homeScreenIntent() {
        val sharedPreferenceManger = SharedPreferenceManger(this)
        sharedPreferenceManger.isFirstTime = false
        val homeIntent = Intent(this, MainActivity::class.java)
        startActivity(homeIntent)
        finish()
    }
}