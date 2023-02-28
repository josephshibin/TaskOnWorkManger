package com.example.taskonworkmanger.ui

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.*
import android.widget.EditText
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.taskonworkmanger.R
import com.example.taskonworkmanger.database.DatabaseVideo
import com.example.taskonworkmanger.database.asDomainModel
import com.example.taskonworkmanger.databinding.FragmentSecond2Binding
import com.example.taskonworkmanger.domain.DevByteVideo
import com.example.taskonworkmanger.viewmodels.DevByteViewModel
import com.example.taskonworkmanger.viewmodels.SharedViewModel

class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecond2Binding
    private lateinit var currentItemToDelete:DevByteVideo

    private var viewModelAdapter: DevByteAdapter? = null

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

        // To disable the delete option when the
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected) {
            setHasOptionsMenu(true)
        } else {
            setHasOptionsMenu(false)
        }


        sharedViewModel.currentItemDetails.observe(viewLifecycleOwner){
            currentItemToDelete=it
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


            R.id.delete -> deleteCurrentItem()

        }

        return true
    }

    private fun deleteCurrentItem() {


   // viewModel.delete(currentItemToDelete)

    }
}


