package com.example.liew.idelivery.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.liew.idelivery.Model.Order;
import com.example.liew.idelivery.R;
import com.example.liew.idelivery.ViewHolder.MyViewHolder;

import java.util.List;

/**
 * Created by kundan on 12/21/2017.
 */

public class OrderDetailAdapter extends RecyclerView.Adapter<MyViewHolder> {

    List<Order> myOrders;

    public OrderDetailAdapter(List<Order> myOrders) {
        this.myOrders = myOrders;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.order_detail_layout,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Order order = myOrders.get(position);
        holder.name.setText(String.format("Name : %s",order.getProductName()));
        holder.quantity.setText(String.format("Quantity : %s",order.getQuantity()));
        holder.price.setText(String.format("Price : %s",order.getPrice()));
        holder.discount.setText(String.format("Discount : %s",order.getDiscount()));
    }

    @Override
    public int getItemCount() {
        return myOrders.size();
    }
}
