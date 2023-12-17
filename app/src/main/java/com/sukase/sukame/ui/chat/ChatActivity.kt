package com.sukase.sukame.ui.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sukase.sukame.R
import com.sukase.sukame.databinding.ActivityChatBinding

class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}