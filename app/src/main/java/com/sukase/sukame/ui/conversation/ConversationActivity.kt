package com.sukase.sukame.ui.conversation

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.sukase.core.domain.model.ConversationModel
import com.sukase.sukame.databinding.ActivityConversationBinding
import com.sukase.sukame.ui.base.NavigationUtils
import com.sukase.sukame.ui.base.showSnackBar
import com.sukase.sukame.ui.base.showToast
import com.sukase.sukame.ui.chat.ChatActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ConversationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConversationBinding
    private val conversationViewModel: ConversationViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConversationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        conversationViewModel.data.observe(this) {
            showData(it.filterNotNull())
        }

        conversationViewModel.eventMessage.onEach {
            showToast(this, it)
        }.launchIn(lifecycleScope)

        conversationViewModel.errorMessage.onEach {
            showSnackBar(binding.root, this, it)
        }.launchIn(lifecycleScope)
    }

    private fun showData(data: List<ConversationModel>) {
        val adapter = ConversationAdapter()
        adapter.setOnItemClickCallback(object : ConversationAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ConversationModel) {
                val intent = Intent(this@ConversationActivity, ChatActivity::class.java)
                intent.putExtra(NavigationUtils.EXTRA_CHAT, data.id)
                startActivity(intent)
            }
        })
        adapter.differ.submitList(data.toMutableList())
        binding.conversationRecyclerView.adapter = adapter
    }

}