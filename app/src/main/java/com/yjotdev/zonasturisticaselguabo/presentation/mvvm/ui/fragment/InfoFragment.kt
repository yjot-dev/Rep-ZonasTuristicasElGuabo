package com.yjotdev.zonasturisticaselguabo.presentation.mvvm.ui.fragment

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import kotlin.getValue
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import com.yjotdev.zonasturisticaselguabo.MainActivity
import com.yjotdev.zonasturisticaselguabo.databinding.FragmentInfoBinding
import com.yjotdev.zonasturisticaselguabo.presentation.mvvm.viewmodel.UiViewModel

@AndroidEntryPoint
class InfoFragment : Fragment() {

    private lateinit var binding: FragmentInfoBinding
    private val viewModel: UiViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModelState()
    }

    private fun observeViewModelState(){
        binding.txtDescriptionPlace.movementMethod = ScrollingMovementMethod()
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    (activity as? MainActivity)?.binding?.includeToolbar?.toolbar?.title = uiState.title
                    Picasso.get().load(uiState.imageUrl).into(binding.imgPlace)
                    binding.txtDescriptionPlace.text = uiState.description
                }
            }
        }
    }
}