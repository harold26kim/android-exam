package com.exam.upraxis.domain.usecase

interface AppInitializationUseCase {

  fun init()

  fun onAppCreated()

  fun onAppDestroyed()

}