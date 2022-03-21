package com.example.liveon_app.activities;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.liveon_app.databinding.ActivityOrderDetailBinding;
import com.example.liveon_app.models.OrderValue;
import com.example.liveon_app.models.Signature;
import com.example.liveon_app.models.Vehicle;
import com.example.liveon_app.models.VehicleImage;
import com.example.liveon_app.viewmodels.OrderDetailViewModel;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class OrderDetailActivity extends AppCompatActivity {

    private ActivityOrderDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getSupportActionBar()).hide();

        binding = ActivityOrderDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Integer orderId = getIntent().getIntExtra("order_id", 0);

        OrderDetailViewModel viewModel = new ViewModelProvider(this).get(OrderDetailViewModel.class);
        viewModel.getOrderDetail(orderId).observe(this, order -> {

            if (order == null) {
                Toast.makeText(this, "Pedido não encontrado!", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }

            Vehicle vehicle = order.getVehicle();
            Signature signature = order.getSignature();
            OrderValue orderValue = order.getOrderValue();

            // page title
            String mTitle = "DETALHES DO PEDIDO #"+ order.getOrder_id();
            binding.txtTitle.setText(mTitle);

            // carousel
            List<VehicleImage> vehicleImages = vehicle.getVehicleImages();

            Picasso.get().setLoggingEnabled(true);

            binding.carouselView.setPageCount(vehicleImages.size());
            binding.carouselView.setImageListener((position, imageView) -> {
                imageView.setMaxWidth(218);
                imageView.setMaxHeight(138);

                String url = vehicleImages.get(position).getImageUrl();
                Picasso.get().load(url).fit().into(imageView);
            });

            // vehicle informations

            String mYearAndBrand = vehicle.getYear() +" "+ vehicle.getBrand();
            binding.txtBrand.setText(mYearAndBrand);

            binding.txtModel.setText(vehicle.getModel());

            String mValue = formatCurrencyNumber(Double.valueOf(order.getOrderValue().getMonthlyPrice()));
            binding.txtMonthlyPrice.setText(mValue);

            binding.txtFuel.setText(vehicle.getFuelType());

            String mDoors = vehicle.getDoorsQtd() + " Portas";
            binding.txtQtdDoors.setText(mDoors);

            binding.txtEngineType.setText(vehicle.getEngineType());

            String mEngine = "Motor "+ vehicle.getEngine();
            binding.txtEngine.setText(mEngine);

            // signature informations

            binding.months.setText(String.valueOf(signature.getMonths()));

            binding.planType.setText(String.valueOf(signature.getPlanType()));

            String mAdditionalKm = formatCurrencyNumber(Double.valueOf(signature.getAdditionalFranchise()));
            binding.additional.setText(mAdditionalKm);

            // values

            String monthlyPrice = formatCurrencyNumber(Double.valueOf(orderValue.getMonthlyPrice()));
            binding.txtPrice.setText(monthlyPrice);

            String mExtras = formatCurrencyNumber(Double.valueOf(orderValue.getExtras()));
            binding.txtExtras.setText(mExtras);

            String mTotal = formatCurrencyNumber(Double.valueOf(orderValue.getTotalPrice()));
            binding.txtTotal.setText(mTotal);
        });

        binding.btnBack.setOnClickListener(view1 -> finish());
    }

    private String formatCurrencyNumber(Double mValue) {
        try {
            NumberFormat numberFormat = NumberFormat
                    .getCurrencyInstance(new Locale("pt", "BR"));

            return numberFormat.format(mValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}