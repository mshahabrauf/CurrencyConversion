package com.golootlo.webview.currencyexchange.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.golootlo.webview.currencyexchange.R
import com.golootlo.webview.currencyexchange.models.ExchangeRate
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.exchange_rate_item.view.*
import java.util.ArrayList

class ExchangeRateAdapter(
        private val mContext: Context,
        private val dataList: ArrayList<ExchangeRate>?)
    : RecyclerView.Adapter<ExchangeRateAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.exchange_rate_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList?.size ?: kotlin.run { 0 }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.loadData(dataList?.get(position))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun loadData(exchangeRate: ExchangeRate?) {
            itemView.currencies.text = exchangeRate?.key
            itemView.exchangeRate.text = exchangeRate?.value.toString()
        }

    }
    fun swapData(records: List<ExchangeRate>){
        dataList?.clear()
        dataList?.addAll(records)
        notifyDataSetChanged()
    }
}
