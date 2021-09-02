package com.gabor.gistlist

import com.gabor.gistlist.models.Gist
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Single
import retrofit2.Response

class FakeApi : GistApi {
    override fun gistsObservable(): Single<Response<List<Gist>>> {
        val itemType = object : TypeToken<List<Gist>>() {}.type
        return Single.just(Response.success(
            Gson().fromJson<List<Gist>>(javaClass.classLoader
            .getResourceAsStream("response.json")
            .readBytes().toString(Charsets.UTF_8), itemType)))
    }
}