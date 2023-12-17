package com.example.foodapp.sharedpreference

import android.content.Context
import android.content.SharedPreferences
import com.example.foodapp.models.Blog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BlogStorage(private val context: Context) {

    private val PREFS_NAME = "BLOG_POSTS"
    private val gson = Gson()
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)



    fun saveBlogPost(blogPost: Blog) {
        val editor = sharedPreferences.edit()
        val blogPosts = getBlogPosts().toMutableList()
        blogPosts.add(blogPost)
        val json = gson.toJson(blogPosts)
        editor.putString(PREFS_NAME, json)
        editor.apply()
    }

    fun getBlogPosts(): List<Blog> {
        val json = sharedPreferences.getString(PREFS_NAME, "")
        return if (json.isNullOrEmpty()) {
            listOf()
        } else {
            val type = object : TypeToken<List<Blog>>() {}.type
            gson.fromJson(json, type)
        }
    }
}
