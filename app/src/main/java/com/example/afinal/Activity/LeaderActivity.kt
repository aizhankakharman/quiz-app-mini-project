package com.example.afinal.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.afinal.Adapter.LeaderAdapter
import com.example.afinal.Domain.UserModel
import com.example.afinal.R
import com.example.afinal.databinding.ActivityLeaderBinding

class LeaderActivity : AppCompatActivity() {
        lateinit var binding:ActivityLeaderBinding
        private val leaderAdapter by lazy { LeaderAdapter() }
        override fun onCreate(savedInstanceState: Bundle?){
            super.onCreate(savedInstanceState)
            binding= ActivityLeaderBinding.inflate(layoutInflater)
            setContentView(binding.root)

            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            )

            binding.apply {
                scoreTxt1.text=loadData().get(0).score.toString()
                scoreTxt2.text=loadData().get(1).score.toString()
                scoreTxt3.text=loadData().get(2).score.toString()
                titleTop1Txt.text=loadData().get(0).name
                titleTop2Txt.text=loadData().get(1).name
                titleTop3Txt.text=loadData().get(2).name

                val drawableResourceId1: Int=binding.root.resources.getIdentifier(
                    loadData().get(0).pic,"drawable", binding.root.context.packageName
                )
                val drawableResourceId2: Int=binding.root.resources.getIdentifier(
                    loadData().get(1).pic,"drawable", binding.root.context.packageName
                )
                val drawableResourceId3: Int=binding.root.resources.getIdentifier(
                    loadData().get(2).pic,"drawable", binding.root.context.packageName
                )

                Glide.with(root.context)
                    .load(drawableResourceId1)
                    .into(pic1)

                Glide.with(root.context)
                    .load(drawableResourceId2)
                    .into(pic2)

                Glide.with(root.context)
                    .load(drawableResourceId3)
                    .into(pic3)

                bottomMenu.setItemSelected(R.id.Board)
                bottomMenu.setOnItemSelectedListener {
                    if(it == R.id.home){
                        startActivity(Intent(this@LeaderActivity,MainActivity::class.java))
                    }
                }

                var list:MutableList<UserModel> = loadData()
                list.removeAt(0)
                list.removeAt(1)
                list.removeAt(2)
                leaderAdapter.differ.submitList(list)
                leaderView.apply{
                    layoutManager=LinearLayoutManager(this@LeaderActivity)
                    adapter = leaderAdapter
                }
            }
        }

    private fun loadData():MutableList<UserModel>{
        val users: MutableList<UserModel> = mutableListOf()
        users.add(UserModel(1, "Aizhan", "person1", 4850))
        users.add(UserModel(2, "Daniel", "person2", 4560))
        users.add(UserModel(3, "Dana", "person3", 3873))
        users.add(UserModel(4, "Yerkebulan", "person4", 3250))
        users.add(UserModel(5, "Madina", "person5", 3015))
        users.add(UserModel(6, "Olzhas", "person6", 2970))
        users.add(UserModel(7, "Zhanel", "person7", 2870))
        users.add(UserModel(8, "Yerkin", "person8", 2770))
        users.add(UserModel(9, "Umit", "person9", 2380))
        users.add(UserModel(10, "Anel", "person8", 1850))
        return users
    }
}