

package com.example.taskonworkmanger.ui

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskonworkmanger.R
import com.example.taskonworkmanger.database.DatabaseVideo
import com.example.taskonworkmanger.databinding.FragmentMainBinding
import com.example.taskonworkmanger.databinding.ItemBinding
import com.example.taskonworkmanger.domain.DevByteVideo
import com.example.taskonworkmanger.viewmodels.DevByteViewModel
import com.example.taskonworkmanger.viewmodels.SharedViewModel
import timber.log.Timber


class DevByteFragment : Fragment() {
    // initializing shared view model
    private val sharedViewModel : SharedViewModel by  activityViewModels()



    private val viewModel: DevByteViewModel by lazy {
        val activity = requireNotNull(this.activity) {

        }
        ViewModelProvider(this, DevByteViewModel.Factory(activity.application))
            .get(DevByteViewModel::class.java)
    }


    private var viewModelAdapter: DevByteAdapter? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.playlist.observe(viewLifecycleOwner, Observer<List<DevByteVideo>> { items ->
            items?.apply {
                viewModelAdapter?.videos = items
            }
        })
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentMainBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main,
            container,
            false)
        // Set the lifecycleOwner so DataBinding can observe LiveData
        binding.setLifecycleOwner(viewLifecycleOwner)

        binding.viewModel = viewModel

        viewModelAdapter = DevByteAdapter(ItemClick(this) {
            sharedViewModel.setCurrentDetails(it)
        })

        binding.root.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }


        // Observer for the network error.
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })

        return binding.root
    }


    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }


}


class ItemClick( private  val activity:DevByteFragment,val block: (DevByteVideo) -> Unit) {
   //  private val sharedViewModel= SharedViewModel()
    fun onClick(video: DevByteVideo) {
        block(video)
//        Timber.d(video.title)
//        // initializing shared view model
//        sharedViewModel.setCurrentDetails(video)
        activity.findNavController().navigate(R.id.action_devByteFragment_to_secondFragment)

    }
}


class DevByteAdapter(val callback: ItemClick) : RecyclerView.Adapter<DevByteViewHolder>() {

    var videos: List<DevByteVideo> = emptyList()
        set(value) {
            field = value

            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DevByteViewHolder {
        val withDataBinding: ItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            DevByteViewHolder.LAYOUT,
            parent,
            false)
        return DevByteViewHolder(withDataBinding)
    }

    override fun getItemCount() = videos.size


    override fun onBindViewHolder(holder: DevByteViewHolder, position: Int) {
        //val video=videos[position]
        holder.viewDataBinding.also {
            it.video = videos[position]
            it.videoCallback = callback

        }

    }

}


class DevByteViewHolder(val viewDataBinding: ItemBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.item
    }
}