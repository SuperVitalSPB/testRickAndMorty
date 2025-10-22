package com.supervital.rickandmorty.di

import com.supervital.rickandmorty.repository.CharacterRepository
import com.supervital.rickandmorty.usecase.CharacterGetDataUseCase
import com.supervital.rickandmorty.usecase.CharacterGetListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class ModuleUseCase {

    @Provides
    @ViewModelScoped
    fun provideCharacterGetDataUseCase(characterRepository: CharacterRepository) =
        CharacterGetDataUseCase(characterRepository)

    @Provides
    @ViewModelScoped
    fun provideCharacterGetListUseCase(characterRepository: CharacterRepository) =
        CharacterGetListUseCase(characterRepository)

}