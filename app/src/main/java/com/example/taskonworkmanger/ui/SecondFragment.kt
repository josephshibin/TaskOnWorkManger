package com.example.taskonworkmanger.ui

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.*
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.taskonworkmanger.R
import com.example.taskonworkmanger.databinding.FragmentSecond2Binding
import com.example.taskonworkmanger.viewmodels.DevByteViewModel
import com.example.taskonworkmanger.viewmodels.SharedViewModel

class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecond2Binding

    // initializing shared view model
    private val sharedViewModel : SharedViewModel by  activityViewModels()


    private val viewModel: DevByteViewModel by lazy {
        val activity = requireNotNull(this.activity) {

        }
        ViewModelProvider(this, DevByteViewModel.Factory(activity.application))
            .get(DevByteViewModel::class.java)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecond2Binding.inflate(layoutInflater, container, false)
        val view = binding.root
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)

        sharedViewModel.currentItemDetails.observe(viewLifecycleOwner){
          binding.title.setText(it.title)
            binding.description.setText(it.description)
            Log.i("data",it.title.toString())
        }
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_option, menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {


          //  R.id.delete -> deleteCurrentItem()

        }

        return true
    }

//    private fun deleteCurrentItem() {
//      //  TODO()
////viewModel.delete()
//    }
}