package com.exam.upraxis.data.remote.service

import com.exam.upraxis.data.remote.repo.UserRepo
import io.reactivex.Single
import retrofit2.http.*

interface UserApiService {
    @GET("v3/d6bd47fa-14f2-4889-ab63-66da67e84e3d")
    fun getUsers(): Single<List<UserRepo>>
}
