package com.exam.upraxis.testcases

import com.exam.upraxis.domain.model.User
import com.exam.upraxis.domain.sealedclass.DataResult
import com.exam.upraxis.domain.usecase.UserUseCase
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Observable
import org.amshove.kluent.mock
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.net.UnknownHostException

@RunWith(MockitoJUnitRunner::class)
class UserUseCaseTest {

  @Test
  fun getAllUsersTest(){
    //GIVEN
    val mockUserUseCase = mock<UserUseCase>()

    doReturn(Observable.just(DataResult.Success(TestData.listOfUsers)))
      .`when`(mockUserUseCase)
      .getUsers()

    //WHEN
    val result = mockUserUseCase.getUsers().test()

    //THEN
    verify(mockUserUseCase).getUsers()

    result.assertNoErrors()
    result.assertComplete()
    result.assertValueCount(1)
    result.assertValue(DataResult.Success(TestData.listOfUsers))
  }

  @Test
  fun getAllUsersEmptyTest(){
    //GIVEN
    val mockUserUseCase = mock<UserUseCase>()

    doReturn(Observable.just(DataResult.Success(listOf<User>())))
      .`when`(mockUserUseCase)
      .getUsers()

    //WHEN
    val result = mockUserUseCase.getUsers().test()

    //THEN
    verify(mockUserUseCase).getUsers()

    result.assertNoErrors()
    result.assertComplete()
    result.assertValueCount(1)
    result.assertValue(DataResult.Success(listOf()))
  }

  @Test
  fun getAllUsersErrorTest(){
    //GIVEN
    val mockUserUseCase = mock<UserUseCase>()
    val throwable = UnknownHostException() // no internet

    doReturn(Observable.just(DataResult.Failed(throwable)))
      .`when`(mockUserUseCase)
      .getUsers()

    //WHEN
    val result = mockUserUseCase.getUsers().test()

    //THEN
    verify(mockUserUseCase).getUsers()

    result.assertNoErrors()
    result.assertComplete()
    result.assertValueCount(1)
    result.assertValue(DataResult.Failed(throwable))
  }
}