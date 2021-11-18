package com.pipix.pipi.src.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.pipix.pipi.R
import com.pipix.pipi.config.BaseActivity
import com.pipix.pipi.data.PRViewModel
import com.pipix.pipi.databinding.ActivityMainBinding
import com.pipix.pipi.testpackage.SoundController

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {


    companion object{
        lateinit var viewModel: PRViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(PRViewModel::class.java)

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

    override fun onPause() {
        super.onPause()
        showCustomToast("onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        showCustomToast("onDestroy")
    }
}