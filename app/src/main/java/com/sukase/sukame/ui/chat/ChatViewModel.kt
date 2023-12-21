package com.sukase.sukame.ui.chat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sukase.core.domain.base.DomainResource
import com.sukase.core.domain.model.ChatModel
import com.sukase.core.domain.model.UserModel
import com.sukase.core.domain.usecase.chat.ChatUseCase
import com.sukase.core.utils.UiText
import com.sukase.sukame.R
import com.sukase.sukame.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(private val chatUseCase: ChatUseCase) : BaseViewModel() {
    private var _user = MutableLiveData<UserModel>()
    val user = _user

    private var _data = MutableLiveData<List<ChatModel?>>()
    val data = _data

    init {
        getUser()
    }

    private fun getUser() {
        chatUseCase.getUser().onEach {
            when (it) {
                is DomainResource.Loading -> {
                    _eventMessage.send(UiText.StringResource(R.string.loading))
                }
                is DomainResource.Empty -> {
                    _eventMessage.send(UiText.StringResource(R.string.empty))
                }

                is DomainResource.Success -> {
                    _user = MutableLiveData(it.data)
                    _eventMessage.send(UiText.StringResource(R.string.success))
                }

                is DomainResource.Error -> {
                    _errorMessage.send(it.error)
                }

                else -> {
                    _errorMessage.send(UiText.StringResource(R.string.unknown_error))
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getChatList(token: String, conversationId: String) {
        chatUseCase.getChatList(token, conversationId).onEach {
            when (it) {
                is DomainResource.Loading -> {
                    _eventMessage.send(UiText.StringResource(R.string.loading))
                }
                is DomainResource.Empty -> {
                    _eventMessage.send(UiText.StringResource(R.string.empty))
                }

                is DomainResource.Success -> {
                    _data = MutableLiveData(it.data)
                    _eventMessage.send(UiText.StringResource(R.string.success))
                }

                is DomainResource.Error -> {
                    _errorMessage.send(it.error)
                }

                else -> {
                    _errorMessage.send(UiText.StringResource(R.string.unknown_error))
                }
            }
        }.launchIn(viewModelScope)
    }

    fun sendChat(token: String, conversationId: String, message: String) {
        chatUseCase.sendChat(token, conversationId, message)
    }
}