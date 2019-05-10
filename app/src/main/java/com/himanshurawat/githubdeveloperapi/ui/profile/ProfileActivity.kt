package com.himanshurawat.githubdeveloperapi.ui.profile

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.himanshurawat.githubdeveloperapi.R
import com.himanshurawat.githubdeveloperapi.adapter.RepositoryAdapter
import com.himanshurawat.githubdeveloperapi.databinding.ActivityProfileBinding
import com.himanshurawat.githubdeveloperapi.pojo.Repo
import com.himanshurawat.githubdeveloperapi.pojo.User
import com.himanshurawat.githubdeveloperapi.pojo.Users
import com.himanshurawat.githubdeveloperapi.utils.Constants

class ProfileActivity: AppCompatActivity(),ProfileActivityContract.View {



    private lateinit var binding: ActivityProfileBinding

    private lateinit var presenter: ProfileActivityContract.Presenter


    private lateinit var repoRecyclerView: RecyclerView
    private lateinit var repoAdapter: RepositoryAdapter
    private lateinit var repoList: MutableList<Repo>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
        setup()

    }

    private fun setup(){
        val users = intent.getSerializableExtra(Constants.MAIN_TO_PROFILE_INTENT_KEY) as Users?
        if(users == null){
            finish()
        }
        Glide.with(this).
            load(users?.profilePictureUrl).
            apply(RequestOptions.circleCropTransform()).
            into(binding.activityProfileImageView)

        repoRecyclerView = binding.activityProfileRepositoryRecyclerView
        repoList = mutableListOf()
        repoAdapter = RepositoryAdapter(this,repoList)
        repoRecyclerView.adapter = repoAdapter
        repoRecyclerView.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        repoRecyclerView.addItemDecoration(DividerItemDecoration(this,RecyclerView.VERTICAL))


        presenter = ProfileActivityPresenter(this)
        presenter.initModel(this)
        presenter.requestProfileData(users?.login as String)
        presenter.requestRepoData(users.login)

    }



    /* I can Break this Method into smaller modules only
        affecting the UI and moving the rest of the logic
        into the presenter.
     */

    override fun updateViews(user: User) {

        //Name TextView
        if(user.name != null){
            binding.activityProfileNameTextView.text = user.name
        }else{
            binding.activityProfileNameTextView.text = user.login
        }

        //Username
        if(user.login != null){
            binding.activityProfileUsernameTextView.text = "@${user.login}"
        }

        //Bio
        if(user.bio != null){
            binding.activityProfileBioTextView.text = user.bio
        }else{
            binding.activityProfileBioTextView.visibility = View.GONE
        }

        //Location
        if(user.location != null){
            binding.activityProfilePlaceTextView.text = user.location
        }else{
            binding.activityProfilePlaceTextView.visibility = View.GONE
            binding.activityProfilePlaceImageView.visibility = View.GONE
        }

        binding.activityProfileFollowingTextView.text = user.following.toString()
        binding.activityProfileFollowersTextView.text = user.followers.toString()

    }

    override fun updateRepo(repo: Repo) {
        repoList.add(repo)
        repoAdapter.notifyItemInserted(repoList.size - 1)
    }

}