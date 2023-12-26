package com.sukase.sukame.ui.conversation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sukase.core.domain.base.DomainResource
import com.sukase.core.domain.usecase.conversation.ConversationUseCase
import com.sukase.core.utils.UiText
import com.sukase.sukame.R
import com.sukase.sukame.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ConversationViewModel @Inject constructor(private val conversationUseCase: ConversationUseCase) :
    BaseViewModel() {

    private var _token = MutableLiveData<String?>()
    val token: LiveData<String?> = _token

    init {
        getToken()
    }

    private fun getToken() {
        conversationUseCase.getToken().onEach {
            when (it) {
                is DomainResource.Loading -> {
                    _eventMessage.send(UiText.StringResource(R.string.loading))
                }

                is DomainResource.Empty -> {
                    _eventMessage.send(UiText.StringResource(R.string.empty))
                }

                is DomainResource.Success -> {
                    _token.postValue((it.data))
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

    fun getConversationList(token: String) =
        conversationUseCase.getAllConversationList(token)
}