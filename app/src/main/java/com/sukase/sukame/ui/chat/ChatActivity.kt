package com.sukase.sukame.ui.chat

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.sukase.core.domain.model.ChatModel
import com.sukase.sukame.databinding.ActivityChatBinding
import com.sukase.sukame.ui.utils.NavigationUtils.EXTRA_CHAT
import com.sukase.sukame.ui.base.showSnackBar
import com.sukase.sukame.ui.base.showToast
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    private val chatViewModel: ChatViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val conversationId = Intent().getStringExtra(EXTRA_CHAT) ?: ""

        chatViewModel.user.observe(this) { user ->
            chatViewModel.getChatList(user.token, conversationId)
            chatViewModel.data.observe(this) { chat ->
                showData(chat.filterNotNull(), user.id)
            }
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