package com.sukase.core.data.mapper

import com.sukase.core.UserPreferences
import com.sukase.core.data.model.chat.entity.ChatEntity
import com.sukase.core.data.model.register.entity.AccountEntity
import com.sukase.core.domain.model.ChatModel
import com.sukase.core.domain.model.ReceiverModel
import com.sukase.core.domain.model.SenderModel


fun provideSenderModel(userPref: UserPreferences): SenderModel =
    SenderModel(userPref.uid, userPref.username)

fun provideReceiverList(account: AccountEntity): ReceiverModel =
    ReceiverModel(account.id.toString(), account.username)

fun chatEntityToModel(input: ChatEntity, username: String): ChatModel =
    ChatModel(
        id = input.id.toString(),
        message = input.message,
        datetime = input.datetime,
        sender = SenderModel(input.senderId.toString(), username),
        receiverList = input.receiverId.mapIndexedNotNull { index, i ->
            ReceiverModel(i.toString(), input.receiverName[index]).takeIf { i != input.senderId }
        }
    )

fun chatModelToEntity(input: ChatModel, conversationId: String): ChatEntity = ChatEntity(
    id = input.id.toInt(),
    conversationId = conversationId,
    message = input.message,
    datetime = input.datetime,
    senderId = input.sender.id.toInt(),
    receiverId = input.receiverList.map { it.id.toInt() },
    receiverName = input.receiverList.map { it.username }
)