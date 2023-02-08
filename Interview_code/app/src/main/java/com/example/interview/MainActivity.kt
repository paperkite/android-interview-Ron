package com.example.interview

import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.interview.models.Cat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URL
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    private val catViewModel: CatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val greenPager = findViewById<ViewPager2>(R.id.green_pager)
        val orangePager = findViewById<ViewPager2>(R.id.orange_pager)
        val purplePager = findViewById<ViewPager2>(R.id.purple_pager)

        greenPager.offscreenPageLimit = 2
        orangePager.offscreenPageLimit = 2
        purplePager.offscreenPageLimit = 2

        val greenData = arrayListOf("1", "2", "3", "4", "5", "6")
        val orangeData = arrayListOf("beng", "abys", "aege", "bamb")
        val purpleData = arrayListOf("1", "2", "3", "4", "5", "6")

        greenPager.adapter = PageViewAdaptor(greenData,R.layout.green_item)
        orangePager.adapter = PageViewAdaptor(orangeData,R.layout.orange_item)
        purplePager.adapter = PageViewAdaptor(purpleData,R.layout.purple_item)

        var selectedBreed = "beng"

        orangePager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                selectedBreed = orangeData[position]
                super.onPageSelected(position)
            }
        })

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer((40 * Resources.getSystem().displayMetrics.density).toInt()))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = (0.80f + r * 0.40f)
            page.scaleX = (0.80f + r * 0.40f)
        }
        greenPager.setPageTransformer(compositePageTransformer)
        orangePager.setPageTransformer(compositePageTransformer)
        purplePager.setPageTransformer(compositePageTransformer)


        val butt = findViewById<Button>(R.id.button)

        butt.setOnClickListener{
            val intent = Intent(this@MainActivity,DetailActivity::class.java)
            intent.putExtra("breed",selectedBreed)
            startActivity(intent)
        }


    }
}