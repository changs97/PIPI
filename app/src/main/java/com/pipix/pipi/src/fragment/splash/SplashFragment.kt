package com.pipix.pipi.src.fragment.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.navigation.fragment.findNavController
import com.pipix.pipi.R
import com.pipix.pipi.config.BaseFragment
import com.pipix.pipi.databinding.FragmentSplashBinding
import com.pipix.pipi.src.main.MainActivity


class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::bind, R.layout.fragment_splash) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        showLoadingDialog(context as MainActivity)



        Handler(Looper.getMainLooper()).postDelayed({
            if(MainActivity.userId != "default" && MainActivity.userName != "default"){
                findNavController().navigate(R.id.action_splashFragment_to_second_graph)
                findNavController().graph.startDestination = R.id.second_graph
            }else{
                findNavController().navigate(R.id.action_splashFragment_to_loggedOutFragment)
                findNavController().graph.startDestination = R.id.loggedOutFragment

            }

            dismissLoadingDialog()
        }, 2000)



    }



}