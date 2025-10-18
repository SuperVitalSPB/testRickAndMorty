package com.supervital.rickandmorti.data.di

import com.supervital.rickandmorti.repository.CharacterRepository
import com.supervital.rickandmorti.data.api.CharacterService
import com.supervital.rickandmorti.data.repository.CharacterRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModuleRepositories {

    @Provides
    @Singleton
    fun provideCharacterRepository(characterService: CharacterService): CharacterRepository =
        CharacterRepositoryImpl(characterService)

}