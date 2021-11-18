package com.pipix.pipi.src.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.pipix.pipi.R
import com.pipix.pipi.config.BaseActivity
import com.pipix.pipi.data.Old
import com.pipix.pipi.data.PRViewModel
import com.pipix.pipi.data.PureResult
import com.pipix.pipi.databinding.ActivityMainBinding
import com.pipix.pipi.testpackage.SoundController

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {


    companion object{
        lateinit var viewModel: PRViewModel
        lateinit var oldList: List<Old>
        lateinit var pureList: List<PureResult>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(PRViewModel::class.java)
        viewModel.readAllOld.observe(this ,{
            showCustomToast("start")
            oldList = it
        })
        viewModel.readAllPureData.observe(this ,{
            pureList = it
        })

        val bottomNavigationView = binding.bottomNav
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        NavigationUI.setupWithNavController(bottomNavigationView, navController)



        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.homeFragment || destination.id == R.id.searchFragment || destination.id == R.id.insertFragment) {
                bottomNavigationView.visibility = View.VISIBLE

            } else {
                bottomNavigationView.visibility = View.GONE
            }
        }

        //SoundController init
        SoundController.init(applicationContext)

    }

}