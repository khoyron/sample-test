package com.sample.data

import com.sample.data.callback.CallbackDataPost
import com.sample.data.callback.CallbackDetailPost
import com.sample.data.endpoint.GetDataPlaceholder
import com.sample.data.model.PostModel
import org.junit.Test

import java.util.concurrent.CountDownLatch

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {

    /**
     * test for get api data
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
     * test for get api data
     *
     * @tester khoyron
     */
    @Test
    fun test_getDetailPost() {
        val latch = CountDownLatch(1)

        GetDataPlaceholder()
            .getDetailPost(object : CallbackDetailPost {
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
}