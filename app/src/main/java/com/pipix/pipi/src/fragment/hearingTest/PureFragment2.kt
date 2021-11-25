package com.pipix.pipi.src.fragment.hearingTest

import android.icu.text.SimpleDateFormat
import android.media.AudioManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.addCallback
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.pipix.pipi.R
import com.pipix.pipi.config.BaseFragment
import com.pipix.pipi.data.PRViewModel
import com.pipix.pipi.data.PureResult
import com.pipix.pipi.databinding.FragmentPure2Binding
import com.pipix.pipi.src.main.MainActivity
import com.pipix.pipi.testpackage.PureTest2
import com.pipix.pipi.testpackage.PureTest2ViewModel
import com.pipix.pipi.testpackage.SoundController
import kotlinx.coroutines.*
import java.util.*

class PureFragment2  : BaseFragment<FragmentPure2Binding>(FragmentPure2Binding::bind, R.layout.fragment_pure2) {

    private lateinit var result: MutableList<MutableList<Int>>
    private lateinit var viewModel: PRViewModel
    private lateinit var ptViewModel: PureTest2ViewModel
    private lateinit var pureTest: PureTest2
    private lateinit var hzText: TextView
    private lateinit var direcText: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var progressText: TextView
    private lateinit var scope: CoroutineScope
    private lateinit var dbText: TextView
    private  var isPause:Boolean = false

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        SoundController.isStopMusicOfOtherApps()

        viewModel = MainActivity.viewModel
        ptViewModel = ViewModelProvider(this).get(PureTest2ViewModel::class.java)
        pureTest = PureTest2(binding.pure2ButtonYes,binding.pure2ButtonNo,requireContext(), ptViewModel)

        // 볼륨 조절
        val st = AudioManager.STREAM_MUSIC
        SoundController.mAudioManager.setStreamVolume(st,15,1)

        hzText = binding.pure2TextviewText2
        direcText = binding.pure2TextviewText3
        progressBar = binding.progress2
        progressText = binding.pure2TextviewProgress
        dbText = binding.pure2TextviewDb

        ptViewModel.hzText.observe(viewLifecycleOwner, Observer {
            hzText.text = it
        })

        ptViewModel.direcText.observe(viewLifecycleOwner, Observer {
            direcText.text = it
        })

        ptViewModel.dB.observe(viewLifecycleOwner,{
            dbText.text = it.toString()+"dB"
        })

        ptViewModel.dB.postValue(30)    // 초기값 설정

        ptViewModel.progress.observe(viewLifecycleOwner, Observer {
            if(it<100){
                progressBar.progress = it
                progressText.text = "${it}%"
            }else {
                progressBar.progress = 100
                progressText.text = "100%"
            }
        })

        ptViewModel.setProgress(0)

        scope = CoroutineScope(CoroutineName("scope"))
        val testJob = scope.launch {
            if(pureTest.doTest(1) && pureTest.doTest(0)) {
                result = pureTest.getResult()
                val now = System.currentTimeMillis()
                val date =  Date(now)

                val pr = PureResult(MainActivity.viewModel.currentOldID, "userID", date, pureTest.getTpa(1),pureTest.getTpa(0)
                    , result[1][4], result[1][5], result[1][0],result[1][1],result[1][2],result[1][3]
                    , result[0][4], result[0][5], result[0][0],result[0][1],result[0][2],result[0][3])
                viewModel.addPureResult(pr)
                activity?.runOnUiThread {
                    isPause = true
                    findNavController().popBackStack()
                }
            }
        }

        binding.pure2ButtonPause.setOnClickListener {
            pureTest.pause()
            isPause = true
            findNavController().popBackStack()
            showCustomToast("순음청력검사가 취소 되었습니다.")
        }

        // backButton Pressed handle
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            pureTest.pause()
            isPause = true
            findNavController().popBackStack()
            showCustomToast("순음청력검사가 취소 되었습니다.")
        }
    }

    override fun onPause() {
        pureTest.pause()
        SoundController.mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC,7,1)
        if(!isPause)findNavController().popBackStack()
        super.onPause()
    }
}