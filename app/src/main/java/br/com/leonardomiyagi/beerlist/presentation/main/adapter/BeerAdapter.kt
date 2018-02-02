package br.com.leonardomiyagi.beerlist.presentation.main.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.leonardomiyagi.beerlist.R
import br.com.leonardomiyagi.beerlist.databinding.ListItemBeerBinding
import br.com.leonardomiyagi.beerlist.domain.model.Beer

/**
 * Created by lmiyagi on 01/02/18.
 */
class BeerAdapter (private val listener: OnClickListener) : RecyclerView.Adapter<BeerAdapter.BeerViewHolder>() {

    private val beers: MutableList<Beer> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BeerViewHolder {
        return BeerViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent?.context), R.layout.list_item_beer, parent, false))
    }

    override fun getItemCount(): Int {
        return beers.size
    }

    override fun onBindViewHolder(holder: BeerViewHolder?, position: Int) {
        holder?.format(beers[position])
    }

    fun setBeers(beers: List<Beer>) {
        this.beers.clear()
        this.beers.addAll(beers)
        notifyDataSetChanged()
    }

    inner class BeerViewHolder(private val binding: ListItemBeerBinding) : RecyclerView.ViewHolder(binding.root) {

        fun format(beer: Beer) {
            binding.beer = beer
            binding.beerContainer.setOnClickListener { listener.onClick(beer) }
        }
    }

    interface OnClickListener {
        fun onClick(beer: Beer)
    }
}