package com.example.liveon_app.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liveon_app.activities.OrderDetailActivity;
import com.example.liveon_app.databinding.SignatureListItemBinding;
import com.example.liveon_app.models.Order;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/** RecyclerView adapter **/
public class SignatureListAdapter extends RecyclerView.Adapter<SignatureListAdapter.SignatureListViewHolder>{

    private final Context ctx;
    private List<Order> orders = new ArrayList<>();
    private SignatureListItemBinding binding;

    public SignatureListAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public SignatureListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = SignatureListItemBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false);

        return new SignatureListAdapter.SignatureListViewHolder(binding.getRoot(), this.ctx);
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
        private final Context ctx;
        private int itemPosition;

        public SignatureListViewHolder(@NonNull View itemView, Context ctx) {
            super(itemView);
            this.ctx = ctx;

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

            Intent intent = new Intent(ctx.getApplicationContext(), OrderDetailActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("order_id", order.getOrder_id());
            ctx.startActivity(intent);
        }
    }
}


