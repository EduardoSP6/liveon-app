package com.example.liveon_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liveon_app.databinding.SignatureListItemBinding;
import com.example.liveon_app.models.Order;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

/** RecyclerView adapter **/
public class SignatureListAdapter extends RecyclerView.Adapter<SignatureListAdapter.SignatureListViewHolder>{

    private final Context ctx;
    private final FragmentManager fm;
    private final List<Order> orders;
    private SignatureListItemBinding binding;

    public SignatureListAdapter(Context ctx, FragmentManager fm, List<Order> orders) {
        this.ctx = ctx;
        this.fm = fm;
        this.orders = orders;
    }

    @NonNull
    @Override
    public SignatureListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = SignatureListItemBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false);

        return new SignatureListAdapter.SignatureListViewHolder(binding.getRoot(), this.fm, this.ctx);
    }

    @Override
    public void onBindViewHolder(@NonNull SignatureListViewHolder holder, int position) {
        holder.bindElements(orders.get(position), position);
    }

    @Override
    public int getItemCount() {
        if (orders == null) {
            return 0;
        }
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

    /** RecyclerView viewholder **/
    class SignatureListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Context ctx;
        private FragmentManager fm;
        private int itemPosition;

        public SignatureListViewHolder(@NonNull View itemView, FragmentManager fm, Context ctx) {
            super(itemView);
            this.ctx = ctx;
            this.fm = fm;

            itemView.setOnClickListener(this);
        }

        public void bindElements(Order order, int position) {
            this.itemPosition = position;

            String orderDesc = "#"+ order.getOrder_id() +" "+ order.getSubmodel_name();
            binding.orderName.setText(orderDesc);
        }

        @Override
        public void onClick(View view) {

        }
    }
}


