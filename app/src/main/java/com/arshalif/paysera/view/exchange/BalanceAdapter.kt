package com.arshalif.paysera.view.exchange

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.arshalif.paysera.databinding.ItemBalanceBinding
import com.arshalif.paysera.domain.model.BalanceCurrency

class BalanceAdapter(
    private val context: Context,
    val balances: ArrayList<BalanceCurrency>
) : RecyclerView.Adapter<BalanceAdapter.ViewHolder>() {
    private val layoutInflater = LayoutInflater.from(context)

    abstract inner class ViewHolder(viewBinding: ViewBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        abstract fun bind(balanceCurrency: BalanceCurrency)
    }

    inner class BalanceViewHolder(private val viewBinding: ViewBinding) : ViewHolder(viewBinding) {
        override fun bind(balanceCurrency: BalanceCurrency) {
            (viewBinding as? ItemBalanceBinding)?.apply {
                itemBalanceTxtValue.text = "${balanceCurrency.value} ${balanceCurrency.value}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return BalanceViewHolder(ItemBalanceBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(balances[position])
    }

    override fun getItemCount(): Int {
        return balances.size
    }
}