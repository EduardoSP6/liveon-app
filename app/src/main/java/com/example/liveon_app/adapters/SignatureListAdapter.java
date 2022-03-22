package com.example.liveon_app.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liveon_app.activities.OrderDetailActivity;
import com.example.liveon_app.databinding.SignatureListItemBinding;
import com.example.liveon_app.models.Order;

import java.util.ArrayList;
import java.util.List;

/** RecyclerView adapter **/
public class SignatureListAdapter extends RecyclerView.Adapter<SignatureListAdapter.SignatureListViewHolder>{

    private final Activity parentActivity;
    private List<Order> orders = new ArrayList<>();
    private SignatureListItemBinding binding;

    public SignatureListAdapter(Activity parentActivity) {
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    public SignatureListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = SignatureListItemBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false);

        return new SignatureListAdapter.SignatureListViewHolder(binding.getRoot(), this.parentActivity);
    }

    @Override
    public void onBindViewHolder(@NonNull SignatureListViewHolder holder, int position) {
        holder.bindElements(orders.get(position), position);
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
        notifyDataSetChanged();
    }

    /** RecyclerView viewholder **/
    class SignatureListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final Activity parentActivity;
        private int itemPosition;

        public SignatureListViewHolder(@NonNull View itemView, Activity parentActivity) {
            super(itemView);
            this.parentActivity = parentActivity;

            itemView.setOnClickListener(this);
        }

        public void bindElements(Order order, int position) {
            this.itemPosition = position;

            String orderDesc = "#"+ order.getOrder_id() +" "+ order.getSubmodel_name();
            binding.orderName.setText(orderDesc);
        }

        @Override
        public void onClick(View view) {

            Order order = orders.get(itemPosition);

            Intent intent = new Intent(parentActivity.getApplicationContext(), OrderDetailActivity.class);
            intent.putExtra("order_id", order.getOrder_id());
            parentActivity.startActivity(intent);
        }
    }
}


