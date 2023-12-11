package com.sukase.core.domain.base

import com.sukase.core.data.base.ApiException
import com.sukase.core.data.base.DataResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

//fun <T> execute(
//    context: CoroutineContext = Dispatchers.Default,
//    block: suspend () -> T
//): Flow<T> {
//    return flow {
//        try {
//            val out = block.invoke()
//            emit(out)
//        } catch (e: ApiException) {
//            throw e.map()
//        }
//    }.flowOn(context)
//}

//fun <T: DataResource<S>, S> mapToDomainResource(resource: S): DomainResource<S> {
//    when(resource) {
//        is DataResource.Success<*> -> DataResource.Success(resource).mapToDomainResource()
//    }
//}