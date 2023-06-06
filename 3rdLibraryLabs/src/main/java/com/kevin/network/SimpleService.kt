/*
 * Copyright (C) 2012 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kevin.network

import com.kevin.network.respond.GithubUserInfo
import com.kevin.network.respond.ReposListItem
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

const val API_URL = "https://api.github.com"

fun main() = runBlocking {
    // Create a very simple REST adapter which points the GitHub API.
    val retrofit = Retrofit.Builder()
        .baseUrl(API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Create an instance of our GitHub API interface.
    val github = retrofit.create(GitHub::class.java)

    // Create a call instance for looking up Retrofit contributors.
//        val call = github.contributors("square", "retrofit")

    // Fetch and print a list of the contributors to the library.
//        val contributors = call.execute().body()!!

    val contributors = github.contributors("square", "retrofit")

    for (contributor in contributors) {
        println(contributor.login + " (" + contributor.contributions + ")")
    }

    val userInfo = github.userInfo("lylwo317")
    println(userInfo)
}

class Contributor(val login: String, val contributions: Int)



interface GitHub {
    @GET("/users/{username}")
    suspend fun userInfo(@Path("username") userName: String?): GithubUserInfo

    @GET("/users/{username}/repos")
    suspend fun userRepos(@Path("username") userName: String?): List<ReposListItem>

    @GET("/repos/{owner}/{repo}/contributors")
    suspend fun contributors(@Path("owner") owner: String?, @Path("repo") repo: String?): List<Contributor>
}
