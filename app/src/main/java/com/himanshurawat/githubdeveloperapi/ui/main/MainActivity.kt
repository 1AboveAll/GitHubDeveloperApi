package com.himanshurawat.githubdeveloperapi.ui.main

import android.content.Context
import android.os.Bundle
import android.widget.AbsListView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.himanshurawat.githubdeveloperapi.R
import com.himanshurawat.githubdeveloperapi.adapter.UserAdapter
import com.himanshurawat.githubdeveloperapi.databinding.ActivityMainBinding
import com.himanshurawat.githubdeveloperapi.pojo.Users
import com.himanshurawat.githubdeveloperapi.utils.InternetConnectivity

class MainActivity: AppCompatActivity(), UserAdapter.OnUserItemClickListener,MainActivityContract.View {
    override fun addUser(user: Users) {
        userList.add(user)
        userAdapter.notifyItemInserted(userList.size - 1)
    }


    override fun onUserItemClick() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var binding: ActivityMainBinding


    private lateinit var presenter: MainActivityContract.Presenter

    private var isScrolling = false
    private var currentItems: Int = 0
    private var totalItems: Int = 0
    private var scrolledOutItems: Int = 0

    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userRecyclerViewLayoutManager: LinearLayoutManager
    private lateinit var userAdapter: UserAdapter
    private lateinit var userList: MutableList<Users>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
        setup()

    }

    private fun setup(){
        userRecyclerView = binding.activityMainRecyclerView
        userList = mutableListOf()
        userAdapter = UserAdapter(this@MainActivity,userList,this)
        userRecyclerView.adapter = userAdapter
        userRecyclerViewLayoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        userRecyclerView.layoutManager = userRecyclerViewLayoutManager
        presenter = MainActivityPresenter(this)
        presenter.initModel(this)
        presenter.requestData(0)
        InternetConnectivity.observe(this, Observer {
            if(it != null) {
                binding.isConnected = it
            }
        })

        userRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                currentItems = userRecyclerViewLayoutManager.childCount
                totalItems = userRecyclerViewLayoutManager.itemCount

                scrolledOutItems = userRecyclerViewLayoutManager.findFirstVisibleItemPosition()

                if(isScrolling && (currentItems + scrolledOutItems) == totalItems){
                    isScrolling = false
                    presenter.requestData(userList[userList.size - 1].id as Long)
                }
            }
        })
    }
}