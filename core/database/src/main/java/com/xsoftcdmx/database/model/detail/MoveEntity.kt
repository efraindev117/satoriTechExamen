package com.xsoftcdmx.database.model.detail

data class MoveEntity(
    val move: MoveEntityX,
    val version_group_details: List<VersionGroupDetailEntity>
)