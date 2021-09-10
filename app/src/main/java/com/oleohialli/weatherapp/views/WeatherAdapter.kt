package com.oleohialli.weatherapp.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.oleohialli.weatherapp.databinding.WeatherListItemBinding
import com.oleohialli.weatherapp.models.Weather

class WeatherAdapter : ListAdapter<Weather, WeatherAdapter.WeatherViewHolder>(WeatherComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding =
            WeatherListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null)
            holder.bind(currentItem)
    }


    class WeatherViewHolder(private val binding: WeatherListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(weather: Weather) {
            binding.apply {
                dayTextView.text = weather.dateText
                weatherTextView.text = weather.weather[0].description
                temp1TextView.text = weather.mainTemp.temp_max.toString()
                temp2TextView.text = weather.mainTemp.temp_min.toString()
            }
        }
    }

    class WeatherComparator : DiffUtil.ItemCallback<Weather>() {
        override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean =
            oldItem.date == newItem.date

        override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean =
            oldItem == newItem
    }
}