package com.supervital.rickandmorty.data.di

import com.supervital.rickandmorty.repository.CharacterRepository
import com.supervital.rickandmorty.data.api.CharacterService
import com.supervital.rickandmorty.data.repository.CharacterRepositoryImpl
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