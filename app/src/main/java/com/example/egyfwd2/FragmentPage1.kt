package com.example.egyfwd2

import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.egyfwd2.databinding.FragmentPage1Binding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Suppress("UNREACHABLE_CODE")
class FragmentPage1 : Fragment() {


    lateinit var binding: FragmentPage1Binding
    lateinit var detailTranfer: DetailViewModel
    private val viewModelD by activityViewModels<DetailViewModel>()
    lateinit var viewModelastroid: viewModelAstroid


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPage1Binding.inflate(inflater, container, false)
        var viewModel = ViewModelProvider(this).get(ViewModel1::class.java)
        viewModelastroid = ViewModelProvider(this).get(viewModelAstroid::class.java)

        setHasOptionsMenu(true)
        viewModel.url.observe(viewLifecycleOwner, Observer {
            Picasso
                .get()
                .load(it)
                .fit()
                .centerCrop()
                .into(binding.imageview)

        })
        viewModel.title.observe(viewLifecycleOwner, Observer {
            binding.title.text = it
        })

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModelastroid.Rlist.observe(viewLifecycleOwner, Observer {
            binding.recyclerView.adapter = RVAdaptor(
                object : RVAdaptor.OnClickListener {
                    override fun onClick(item: AstroidHolder) {
                        println(item.name)
                        viewModelD.itemChoosed = item
                        fragmentManager?.beginTransaction()?.apply {
                            replace(
                                R.id.fragmentContainerView,
                                FragmentPage2(),
                                FragmentPage2::class.java.simpleName
                            ).addToBackStack(null).commit()
                        }
                    }
                }, it
            )
        })
        return binding.root
    }

    @Override
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Override
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.todayAstroid -> viewModelastroid.getThisDay()
            R.id.weekAstroid -> viewModelastroid.getWeek()
            R.id.savedAstroid -> viewModelastroid.getSaveed()
        }
        return true
    }
}