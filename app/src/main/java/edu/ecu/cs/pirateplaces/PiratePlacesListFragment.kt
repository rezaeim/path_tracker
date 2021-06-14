package edu.ecu.cs.pirateplaces

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.ecu.cs.pirateplaces.databinding.ListItemPlaceBinding
import java.util.*

class PiratePlacesListFragment: Fragment() {

    interface Callbacks {
        fun onPlaceSelected(placeId: UUID)
    }

    private var callbacks : Callbacks? = null

    private lateinit var piratePlacesRecyclerView: RecyclerView
    private var adapter: PlaceAdapter? = PlaceAdapter(emptyList())

    private val piratePlacesListViewModel : PiratePlacesListViewModel by lazy {
        ViewModelProviders.of(this).get(PiratePlacesListViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pirate_places_list, container, false)

        piratePlacesRecyclerView = view.findViewById(R.id.pirate_places_recycler_view) as RecyclerView
        piratePlacesRecyclerView.layoutManager = LinearLayoutManager(context)
        piratePlacesRecyclerView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        piratePlacesListViewModel.piratePlacesListLiveData.observe(
            viewLifecycleOwner,
            Observer { places ->
                places?.let {
                    updateUI(places)
                }
            }
        )
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_pirate_places_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.new_pirate_place -> {
                val place = PiratePlace()
                piratePlacesListViewModel.addPiratePlace(place)
                callbacks?.onPlaceSelected(place.id)
                true
            }
            R.id.open_places_map -> {
                val intent = Intent(context, PiratePlacesMapActivity::class.java)
                startActivity(intent)
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun updateUI(places: List<PiratePlace>) {
        adapter = PlaceAdapter(places)
        piratePlacesRecyclerView.adapter = adapter
    }

    companion object {
        fun newInstance() : PiratePlacesListFragment {
            return PiratePlacesListFragment()
        }
    }

    private inner class PlaceHolder(private val binding: ListItemPlaceBinding):
            RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        private lateinit var place: PiratePlace
        private var visitedWithTextView: TextView = itemView.findViewById(R.id.visited_with)
        private var dateTextView: TextView = itemView.findViewById(R.id.date_visited)
        private var timeTextView: TextView = itemView.findViewById(R.id.time_visited)
        private val viewModel = ListItemViewModel(
            DateFormat.getMediumDateFormat(requireContext()),
            DateFormat.getTimeFormat(requireContext())
        )

        init {
            binding.viewModel = viewModel
            itemView.setOnClickListener(this)
        }

        fun bind(place: PiratePlace) {
            this.place = place
            viewModel.place = place

            visitedWithTextView.text = place.visitedWith

            val dateString = DateFormat.getMediumDateFormat(requireContext()).format(place.lastVisited)
            val timeString = DateFormat.getTimeFormat(requireContext()).format(place.lastVisited)

            dateTextView.text = dateString
            timeTextView.text = timeString
        }

        override fun onClick(v: View) {
            callbacks?.onPlaceSelected(place.id)
        }
    }

    private inner class PlaceAdapter(var places:List<PiratePlace>):
            RecyclerView.Adapter<PlaceHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceHolder {
            val binding = DataBindingUtil.inflate<ListItemPlaceBinding>(
                layoutInflater,
                R.layout.list_item_place,
                parent,
                false
            )
            return PlaceHolder(binding)
        }

        override fun getItemCount() = places.size

        override fun onBindViewHolder(holder: PlaceHolder, position: Int) {
            holder.bind(places[position])
        }
    }
}