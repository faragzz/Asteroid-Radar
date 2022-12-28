package com.example.egyfwd2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.activityViewModels
import com.example.egyfwd2.databinding.FragmentPage2Binding
import com.squareup.picasso.Picasso

class FragmentPage2 : Fragment() {
    private lateinit var builder:AlertDialog.Builder
    lateinit var binding: FragmentPage2Binding
    private val viewModel by activityViewModels<DetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentPage2Binding.inflate(inflater, container, false)
        binding.infoDate.text = viewModel.itemChoosed.date
        binding.infoAbsoluteMagnitude.text = viewModel.itemChoosed.magnitude.toString()
        binding.infoEstimateDiameter.text = viewModel.itemChoosed.diameter.toString()
        binding.infoRelativeVelocity.text = viewModel.itemChoosed.velocity.toString()
        binding.infoDistanceFromEarth.text = viewModel.itemChoosed.distance.toString()
        if(viewModel.itemChoosed.hazardous == true){
            binding.state.contentDescription ="@string/image_safe"
            Picasso
                .get()
                .load(R.drawable.asteroid_hazardous)
                .fit()
                .centerCrop()
                .into(binding.state)
        }
        else{
            binding.state.contentDescription ="@string/image_Hazard"
            Picasso
                .get()
                .load(R.drawable.asteroid_safe)
                .fit()
                .centerCrop()
                .into(binding.state)
        }
        builder = AlertDialog.Builder(requireContext())

        binding.help.setOnClickListener {
            builder.setMessage("The astronomical unit (au) is a unit\nof length, roughly the distance from\nEarth to the Sun and equal to about\n 150 million\n kilometres (93 million miles)")
                .setCancelable(true)
                .setNegativeButton("OK"){dialogInterface,it->dialogInterface.cancel()}.show()
        }


        return binding.root
    }

}
