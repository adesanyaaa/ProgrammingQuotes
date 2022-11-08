package com.adesoftware.programmingquotes

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.adesoftware.programmingquotes.databinding.ActivityProgramQuotesBinding
import retrofit2.HttpException
import java.io.IOException

const val TAG = "ProgramQuotesActivity"

class ProgramQuotesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProgramQuotesBinding
    private lateinit var programmingQuotesAdapter: ProgrammingQuotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProgramQuotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifeCycleScope()
        setUpRecyclerView()
    }

    private fun lifeCycleScope() {
        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true
            val response = try {
                RetrofitInstance.api.getQuotes()
            } catch (e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection")
                Toast.makeText(this@ProgramQuotesActivity,
                "You have not got internet connection", Toast.LENGTH_LONG).show()
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() != null) {
                programmingQuotesAdapter.programmingQuotes = response.body()!!
            } else {
                Log.e(TAG, "Response not successful")
            }
            binding.progressBar.isVisible = false
        }
    }

    private fun setUpRecyclerView() = binding.recyclerViewPq.apply {
        programmingQuotesAdapter = ProgrammingQuotesAdapter()
        adapter = programmingQuotesAdapter
        layoutManager = LinearLayoutManager(this@ProgramQuotesActivity)
    }
}