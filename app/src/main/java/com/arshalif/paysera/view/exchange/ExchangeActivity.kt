package com.arshalif.paysera.view.exchange

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.arshalif.paysera.R
import com.arshalif.paysera.databinding.ActivityExchangeBinding

class ExchangeActivity : AppCompatActivity() {

    private val viewModel: ExchangeViewModel by viewModels()
    private var _binding: ActivityExchangeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityExchangeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}