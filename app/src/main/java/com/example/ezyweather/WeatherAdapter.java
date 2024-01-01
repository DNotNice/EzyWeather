package com.example.ezyweather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {
    private Context context;
    private ArrayList<weatherModel> weatherModelArrayList;

    public WeatherAdapter(Context context, ArrayList<weatherModel> weatherModelArrayList) {
        this.context = context;
        this.weatherModelArrayList = weatherModelArrayList;
    }
    @Override
    public ViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.weather_item , parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        weatherModel model = weatherModelArrayList.get(position);
        holder.Temp.setText((int)Double.parseDouble(model.getTemperature())+"°C");
        Picasso.get().load("https:".concat(model.getIcon())).into(holder.imageView);
        holder.LastUpdated.setText(model.getWindSpeed() + "km/h");
        SimpleDateFormat iput = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        SimpleDateFormat ouput = new SimpleDateFormat("hh:mm aa");
        try{
            Date t = iput.parse(model.getTime());
            holder.Time.setText(ouput.format(t));
        }catch (ParseException e){
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return weatherModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView Time ,Temp ,LastUpdated ;
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Time = itemView.findViewById(R.id.idTime);
            Temp = itemView.findViewById(R.id.idTemperature);
            LastUpdated = itemView.findViewById(R.id.LastUpdated);
            imageView = itemView.findViewById(R.id.idCondition);
        }
    }




}
