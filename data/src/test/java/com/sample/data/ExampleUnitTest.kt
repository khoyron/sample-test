package com.sample.data

import com.sample.data.callback.*
import com.sample.data.endpoint.GetDataPlaceholder
import com.sample.data.model.*
import org.junit.Test

import java.util.concurrent.CountDownLatch

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {

    /**
     * test for get api data post
     *
     * @tester khoyron
     */
    @Test
    fun test_getDataPost() {
        val latch = CountDownLatch(1)

        GetDataPlaceholder().getDataPost(object : CallbackDataPost {
            override fun success(data: ArrayList<PostModel>) {
                data.forEach {
                    println("--------------------")
                    println("id "+it.idPost)
                    println("id post "+it.userId)
                    println("title "+it.title)
                    println("body "+it.body)
                }
                latch.await()
            }

            override fun failed(massege: String) {
                latch.await()
            }
        })

        try {
            latch.await()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * test for get api data detail post
     *
     * @tester khoyron
     */
    @Test
    fun test_getDetailPost() {
        val latch = CountDownLatch(1)

        GetDataPlaceholder()
            .getDetailPost("1",object : CallbackDetailPost {
            override fun success(data: PostModel) {
                println("id "+data.idPost)
                println("id post "+data.userId)
                println("title "+data.title)
                println("body "+data.body)
                latch.await()
            }

            override fun failed(massege: String) {
                latch.await()
            }
        })

        try {
            latch.await()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * test for get api data post comment
     *
     * @tester khoyron
     */
    @Test
    fun test_getPostComment() {
        val latch = CountDownLatch(1)

        GetDataPlaceholder()
            .getPostComment("1",object : CallbackCommentPost {
                override fun success(data: ArrayList<CommentModel>) {
                    println("----------")
                    data.forEach {
                        println(it.autorName)
                        println(it.body)
                    }
                    latch.await()
                }
                override fun failed(massege: String) {
                    latch.await()
                }
            })

        try {
            latch.await()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * test for get api data user profile
     *
     * @tester khoyron
     */
    @Test
    fun test_getUser() {
        val latch = CountDownLatch(1)

        GetDataPlaceholder()
            .getUser("1",object : CallbackUser {
                override fun success(data: UserModel) {
                    println("----------")
                    println(data.address)
                    println(data.company)
                    println(data.email)
                    println(data.username)
                    latch.await()
                }

                override fun failed(massege: String) {
                    latch.await()
                }
            })

        try {
            latch.await()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * test for get api data list albums
     *
     * @tester khoyron
     */
    @Test
    fun test_getAlbums() {
        val latch = CountDownLatch(1)

        GetDataPlaceholder()
            .getAlbums("1",object : CallbackAlbums {
                override fun success(data: ArrayList<AlbumModel>) {
                    println("----------")
                    data.forEach {
                        println(it.idAlbum)
                        println(it.title)
                    }
                    latch.await()
                }

                override fun failed(massege: String) {
                    latch.await()
                }
            })

        try {
            latch.await()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * test for get api data list photo
     *
     * @tester khoyron
     */
    @Test
    fun test_getPotho() {
        val latch = CountDownLatch(1)

        GetDataPlaceholder()
            .getPhoto("1",object : CallbackPhoto {
                override fun success(data: ArrayList<PhotoModel>) {
                    println("----------")
                    data.forEach {
                        println(it.url)
                        println(it.thumbnail)
                        println(it.title)
                    }
                    latch.await()
                }

                override fun failed(massege: String) {
                    latch.await()
                }
            })

        try {
            latch.await()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}