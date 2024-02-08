package com.example.apitest

import android.net.http.HttpException
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.annotation.RequiresExtension
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager

import com.systemira.tal3etsamak.R
import com.systemira.tal3etsamak.databinding.ActivityMainBinding
import java.io.IOError
import java.io.IOException

const val TAG = "MainActivity"
@Suppress("DEPRECATION")

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var todoAdapter :TodoAdapter

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()
        lifecycleScope.launchWhenCreated {
            binding.pb1.isVisible = true
            val response = try {
                RetrofitInstance.api.getTodo()

            }catch (e : IOException){
                Log.e(TAG, "IOException ,you might not have internet connection")
                binding.pb1.isVisible = false
                return@launchWhenCreated

            }catch (e : HttpException){
                Log.e(TAG,"response not success")
                binding.pb1.isVisible = false
                return@launchWhenCreated
            }

            if (response.isSuccessful && response.body() !=null){
                todoAdapter.submitList(response.body()!!)
            }
            else{
                Log.e(TAG,"HttpException , unexpected response")
            }
            binding.pb1.isVisible = false
        }

    }

    private fun setUpRecyclerView() = binding.rvItems.apply {
        todoAdapter = TodoAdapter()
        adapter = todoAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }
}