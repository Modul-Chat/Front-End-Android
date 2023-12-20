package com.sukase.sukame.ui.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.sukase.core.domain.model.ChatModel
import com.sukase.core.domain.model.ConversationModel
import com.sukase.sukame.R
import com.sukase.sukame.databinding.ActivityChatBinding
import com.sukase.sukame.ui.base.NavigationUtils
import com.sukase.sukame.ui.base.showSnackBar
import com.sukase.sukame.ui.base.showToast
import com.sukase.sukame.ui.conversation.ConversationAdapter
import com.sukase.sukame.ui.conversation.ConversationViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    private val chatViewModel: ChatViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        chatViewModel.user.observe(this) {
        }

        chatViewModel.eventMessage.onEach {
            showToast(this, it)
        }.launchIn(lifecycleScope)

        chatViewModel.errorMessage.onEach {
            showSnackBar(binding.root, this, it)
        }.launchIn(lifecycleScope)
    }

    private fun showData(data: List<ChatModel>, uid: String) {
        val adapter = ChatAdapter(uid)
        adapter.differ.submitList(data.toMutableList())
        binding.rvChat.adapter = adapter
    }
}