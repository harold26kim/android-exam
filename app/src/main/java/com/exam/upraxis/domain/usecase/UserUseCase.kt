package com.exam.upraxis.domain.usecase

import com.exam.upraxis.domain.model.User
import com.exam.upraxis.domain.sealedclass.DataResult
import io.reactivex.Observable

interface UserUseCase {
  fun getUsers(): Observable<DataResult<List<User>>>
}