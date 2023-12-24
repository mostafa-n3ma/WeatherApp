package com.example.weatherapp.operations.data_management.data_utils

interface DefaultMapper<Domain, Entity> {
    fun mapToDomain(entity: Entity): Domain
    fun mapFromDomain(domain: Domain): Entity
    fun mapEntitiesList(list: List<Entity>): List<Domain>
}