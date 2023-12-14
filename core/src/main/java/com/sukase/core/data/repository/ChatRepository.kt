package com.sukase.core.data.repository

import android.database.SQLException
import androidx.datastore.core.DataStore
import com.sukase.core.R
import com.sukase.core.UserPreferences
import com.sukase.core.data.base.ApiException
import com.sukase.core.data.base.BaseError
import com.sukase.core.data.base.DataResource
import com.sukase.core.data.base.DatabaseException
import com.sukase.core.data.mapper.chatEntityToModel
import com.sukase.core.data.mapper.chatModelToEntity
import com.sukase.core.data.mapper.provideReceiverList
import com.sukase.core.data.mapper.provideSenderModel
import com.sukase.core.data.source.database.SuKaMeDao
import com.sukase.core.domain.base.DomainResource
import com.sukase.core.domain.model.ChatModel
import com.sukase.core.domain.usecase.chat.IChatRepository
import com.sukase.core.utils.UiText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepository @Inject constructor(
    private val dao: SuKaMeDao,
    private val dataStore: DataStore<UserPreferences>
) : IChatRepository {
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getChatList(
        token: String,
        conversationId: String
    ): Flow<DomainResource<List<ChatModel?>>> = dao.getChatList(conversationId.toInt()).flatMapConcat {
        flow {
            emit(DataResource.Loading.mapToDomainResource())
            emit(DataResource.Success(it.map {
                if (it != null) {
                    chatEntityToModel(it.chat, dataStore.data.first().username)
                } else {
                    null
                }
            }).mapToDomainResource())
        }.catch {
            if (it.message.isNullOrBlank()) {
                emit(
                    DataResource.Error(
                        BaseError(
                            UiText.StringResource(R.string.unknown_error)
                        ).mapToDomainThrowable()
                    ).mapToDomainResource()
                )
            } else if (it is HttpException) {
                emit(
                    DataResource.Error(
                        ApiException(
                            it.code().toString(),
                            it.message()
                        ).mapToDomainThrowable()
                    ).mapToDomainResource()
                )
            } else if (it is SQLException) {
                emit(
                    DataResource.Error(
                        DatabaseException(
                            UiText.DynamicString(it.message.toString())
                        ).mapToDomainThrowable()
                    ).mapToDomainResource()
                )
            } else {
                emit(
                    DataResource.Error(
                        BaseError(
                            UiText.DynamicString(it.message.toString())
                        ).mapToDomainThrowable()
                    ).mapToDomainResource()
                )
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun sendChat(
        token: String,
        conversationId: String,
        message: String
    ): Flow<DomainResource<Boolean>> = flow {
        emit(DataResource.Loading.mapToDomainResource())
        dao.sendChat(
            chatModelToEntity(
                ChatModel(
                    id = conversationId,
                    message = message,
                    datetime = System.currentTimeMillis().toString(),
                    sender = provideSenderModel(dataStore.data.first()),
                    receiverList = dao.getConversation(conversationId.toInt())
                        .first().participants.map {
                        provideReceiverList(it)
                    }
                ),
                conversationId
            )
        )
        emit(DataResource.Success(true).mapToDomainResource())
    }.catch {
        if (it.message.isNullOrBlank()) {
            emit(
                DataResource.Error(
                    BaseError(
                        UiText.StringResource(R.string.unknown_error)
                    ).mapToDomainThrowable()
                ).mapToDomainResource()
            )
        } else if (it is HttpException) {
            emit(
                DataResource.Error(
                    ApiException(
                        it.code().toString(),
                        it.message()
                    ).mapToDomainThrowable()
                ).mapToDomainResource()
            )
        } else if (it is SQLException) {
            emit(
                DataResource.Error(
                    DatabaseException(
                        UiText.DynamicString(it.message.toString())
                    ).mapToDomainThrowable()
                ).mapToDomainResource()
            )
        } else {
            emit(
                DataResource.Error(
                    BaseError(
                        UiText.DynamicString(it.message.toString())
                    ).mapToDomainThrowable()
                ).mapToDomainResource()
            )
        }
    }
}