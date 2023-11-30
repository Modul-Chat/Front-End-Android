package com.sukase.core.data.repository

import android.database.sqlite.SQLiteConstraintException
import androidx.datastore.core.DataStore
import com.sukase.core.R
import com.sukase.core.UserPreferences
import com.sukase.core.data.model.register.entity.RegisterEntity
import com.sukase.core.data.source.database.SuKaMeDao
import com.sukase.core.domain.usecase.auth.IAuthRepository
import com.sukase.core.utils.Resource
import com.sukase.core.utils.UiText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val dao: SuKaMeDao,
    private val dataStore: DataStore<UserPreferences>
) : IAuthRepository {
    override suspend fun register(username: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading)
        try {
            dao.register(RegisterEntity(username))
            emit(Resource.Success(true))
        } catch (e: SQLiteConstraintException) {
            emit(Resource.Error(UiText.StringResource(R.string.username_already_exist)))
        }
    }.catch {
        if (it.message.isNullOrBlank()) {
            emit(Resource.Error(UiText.StringResource(R.string.unknown_error)))
        } else {
            emit(Resource.Error(UiText.DynamicString(it.message.toString())))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun login(username: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading)
        dataStore.updateData { it.toBuilder().setUsername(username).setUid("807841762").setToken("admin").build() }
        emit(Resource.Success(true))
    }.catch {
        if (it.message.isNullOrBlank()) {
            emit(Resource.Error(UiText.StringResource(R.string.unknown_error)))
        } else if(it is IOException) {
            emit(Resource.Error(UiText.DynamicString(it.message.toString())))
        }
    }

}