package com.example.foodapp.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.adapter.BlogAdapter
import com.example.foodapp.models.Blog
import com.example.foodapp.models.Meal
import com.example.foodapp.sharedpreference.BlogStorage
import com.google.android.material.floatingactionbutton.FloatingActionButton


class BlogFragment : Fragment() {
    private lateinit var blogAdapter: BlogAdapter
    private lateinit var blogStorage: BlogStorage
    private val blogList = mutableListOf<Blog>()
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        blogStorage = BlogStorage(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_blog, container, false)
        blogList.clear()
        blogList.addAll(blogStorage.getBlogPosts())

        recyclerView = rootView.findViewById(R.id.recyclerViewBlogs)
        blogAdapter = BlogAdapter(blogList)
        recyclerView.adapter = blogAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val fabAddBlog: FloatingActionButton = rootView.findViewById(R.id.fabAddBlog)
        fabAddBlog.setOnClickListener {
            showAddBlogDialog()
        }

        return rootView
    }


    @SuppressLint("MissingInflatedId")

    private fun showAddBlogDialog() {
        val dialogView = layoutInflater.inflate(R.layout.add_blog, null)

        val editTextTitle = dialogView.findViewById<EditText>(R.id.editTextTitle)
        val editTextContent = dialogView.findViewById<EditText>(R.id.editTextContent)

        val dialogBuilder = android.app.AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle("Add Blog")
            .setPositiveButton("Add") { dialog, _ ->
                val title = editTextTitle.text.toString().trim()
                val content = editTextContent.text.toString().trim()

                if (title.isNotEmpty() && content.isNotEmpty()) {
                    val newBlog = Blog(title, content)
                    addBlog(newBlog)
                }

                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }

        val dialog = dialogBuilder.create()
        dialog.show()
    }

    private fun addBlog(newBlog: Blog) {
        blogStorage.saveBlogPost(newBlog)
        blogList.add(newBlog)
        blogAdapter.notifyItemInserted(blogList.size - 1)
    }

    private val PREFS_NAME = "BLOG_POSTS"



    private fun displayBlogs() {
        val sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val blogSet = sharedPreferences.getStringSet("blogs", HashSet()) ?: HashSet()

        val blogList = blogSet.map { blogString ->
            val parts = blogString.split("::")
            if (parts.size == 2) {
                Blog(parts[0], parts[1])
            } else {
                Blog("Title", "Content")
            }
        }

    }
}