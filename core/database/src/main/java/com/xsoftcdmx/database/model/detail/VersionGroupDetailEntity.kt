package com.xsoftcdmx.database.model.detail

data class VersionGroupDetailEntity(
    val level_learned_at: Int,
    val move_learn_method: MoveLearnMethodEntity,
    val version_group: VersionGroupEntity
)