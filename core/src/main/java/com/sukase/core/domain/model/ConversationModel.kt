package com.sukase.core.domain.model

data class ConversationModel(
    val id: String,
    val participantList: List<ParticipantModel>,
)
