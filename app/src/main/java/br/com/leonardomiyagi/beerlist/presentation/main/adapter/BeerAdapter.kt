package br.com.leonardomiyagi.beerlist.presentation.main.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import br.com.leonardomiyagi.beerlist.R
import br.com.leonardomiyagi.beerlist.databinding.ListItemBeerBinding
import br.com.leonardomiyagi.beerlist.domain.model.Beer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


/**
 * Created by lmiyagi on 01/02/18.
 */
class BeerAdapter(private val listener: OnClickListener) : RecyclerView.Adapter<BeerAdapter.BeerViewHolder>(), Filterable {

    private val beers: MutableList<Beer> = ArrayList()
    private var filteredBeers: MutableList<Beer> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BeerViewHolder {
        return BeerViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent?.context), R.layout.list_item_beer, parent, false))
    }

    override fun getItemCount(): Int {
        return filteredBeers.size
    }

    override fun onBindViewHolder(holder: BeerViewHolder?, position: Int) {
        holder?.format(filteredBeers[position])
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val charString = charSequence.toString()
                if (TextUtils.isEmpty(charString)) {
                    filteredBeers = beers
                } else {
                    filteredBeers = beers.filter { it.name?.contains(charString) ?: false }.toMutableList()
                }

                val filterResults = FilterResults()
                filterResults.values = filteredBeers
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence?, results: FilterResults?) {
                filteredBeers = results?.values as MutableList<Beer>
                notifyDataSetChanged()
            }
        }
    }

    fun setBeers(beers: List<Beer>) {
        this.beers.clear()
        this.beers.addAll(beers)
        filteredBeers.clear()
        filteredBeers.addAll(beers)
        notifyDataSetChanged()
    }

    inner class BeerViewHolder(private val binding: ListItemBeerBinding) : RecyclerView.ViewHolder(binding.root) {

        fun format(beer: Beer) {
            binding.beer = beer
            Glide.with(binding.root.context)
                    .load(beer.imageUrl)
                    .apply(RequestOptions().fitCenter().placeholder(R.drawable.ic_beer_placeholder))
                    .into(binding.thumbnailImageView)
            binding.beerContainer.setOnClickListener { listener.onClick(beer) }
        }
    }

    interface OnClickListener {
        fun onClick(beer: Beer)
    }
}