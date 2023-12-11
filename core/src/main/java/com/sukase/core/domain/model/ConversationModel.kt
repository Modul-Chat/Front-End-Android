package com.sukase.core.domain.model

data class ConversationModel(
    val id: String,
    val name: String,
    val lastMessage: String,
    val participantList: List<ParticipantModel>,
)