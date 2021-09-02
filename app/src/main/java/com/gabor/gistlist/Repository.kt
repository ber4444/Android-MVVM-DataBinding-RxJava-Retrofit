package com.gabor.gistlist

import com.gabor.gistlist.models.Gist
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by gabor on 12/3/17.
 */
interface GistApi {
    @GET("/gists/public?per_page=100")
    fun gistsObservable(): Single<Response<List<Gist>>>
}

@Singleton
class Repository @Inject constructor(private val api: GistApi) {

    fun getData() = api.gistsObservable()
}