package com.sukase.sukame.ui.chat

import com.sukase.core.domain.usecase.chat.ChatUseCase
import com.sukase.sukame.ui.base.BaseViewModel
import javax.inject.Inject

class ChatViewModel @Inject constructor(private val chatUseCase: ChatUseCase) : BaseViewModel() {

}