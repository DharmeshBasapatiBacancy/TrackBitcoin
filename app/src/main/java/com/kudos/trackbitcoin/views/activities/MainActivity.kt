package com.kudos.trackbitcoin.views.activities

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.work.*
import com.kudos.trackbitcoin.databinding.ActivityMainBinding
import com.kudos.trackbitcoin.viewmodel.TrackBitcoinViewModel
import com.kudos.trackbitcoin.worker.TrackBitcoinWorker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val trackBitcoinViewModel: TrackBitcoinViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        observeBitcoinRates()
        startBitcoinRatesTracker()
        binding.refreshRatesButton.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            refreshBitcoinRates()
        }
    }

    private fun observeBitcoinRates() {
        lifecycleScope.launch {
            trackBitcoinViewModel.latestBitcoinRates.observe(this@MainActivity) {
                if (it.isNotEmpty()) {
                    binding.bitcoinRates = it[0]
                }
            }
        }
    }

    private fun refreshBitcoinRates() {
        val workManager = WorkManager.getInstance(this)

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val myTestWorkerRequest =
            OneTimeWorkRequestBuilder<TrackBitcoinWorker>()
                .setConstraints(constraints)
                .build()

        workManager.enqueueUniqueWork(
            "BitcoinRatesTracker",
            ExistingWorkPolicy.REPLACE,
            myTestWorkerRequest
        )

        workManager.getWorkInfoByIdLiveData(myTestWorkerRequest.id).observe(this) { workInfo ->
            if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun startBitcoinRatesTracker() {
        val workManager = WorkManager.getInstance(this)

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val myTestWorkerRequest =
            PeriodicWorkRequestBuilder<TrackBitcoinWorker>(
                PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS, TimeUnit.MINUTES
            )
                //to set some constraints in worker class
                .setConstraints(constraints)
                //in case of retry
                .setBackoffCriteria(
                    BackoffPolicy.LINEAR,
                    PeriodicWorkRequest.MIN_BACKOFF_MILLIS,
                    TimeUnit.MILLISECONDS
                )
                .build()

        workManager.enqueueUniquePeriodicWork(
            "BitcoinRatesTracker",
            ExistingPeriodicWorkPolicy.REPLACE,
            myTestWorkerRequest
        )

    }
}