package sodexo.pe.com.sodexo.presentation.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tismart.tsmviews.views.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.domain.entity.PromoEntity;
import sodexo.pe.com.sodexo.presentation.SodexoApplication;
import sodexo.pe.com.sodexo.presentation.interfaces.MainView;
import sodexo.pe.com.sodexo.util.AlertUtil;

/**
 * Created by RONALD on 05/10/2016.
 */

public class RVPromoAdapter extends RecyclerView.Adapter<RVPromoAdapter.PromoViewHolder> {
    private List<PromoEntity> list;
    private List<PromoEntity> filteredList;
    private boolean filtering;
    private MainView mainView;

    public RVPromoAdapter(MainView mainView) {
        this.list = new ArrayList<>();
        this.filteredList = new ArrayList<>();
        this.filtering = false;
        this.mainView = mainView;
    }

    @Override
    public PromoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PromoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_promo, parent, false));
    }

    @Override
    public void onBindViewHolder(PromoViewHolder holder, int position) {
        final PromoEntity promo;
        if(!filtering)
            promo = list.get(position);
        else
            promo = filteredList.get(position);
        final Context context = holder.itemView.getContext();
        Glide.with(SodexoApplication.context).load(promo.getPhoto()).into(holder.ivBannerPromo);
        Glide.with(SodexoApplication.context).load(promo.getImageLogo()).into(holder.ivLogo);
        holder.tvDescription.setText(promo.getDescription());
        holder.btnShowPromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainView.openPromoDetail(promo, false);
            }
        });
        holder.ivWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertUtil.showMessageAcceptNegative(context, null, "¿Deseas agregar esta promoción al wishlist?", "Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mainView.addToWishList(promo);
                        dialogInterface.dismiss();
                    }
                }, "No", null);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(!filtering)
            return list.size();
        else
            return filteredList.size();
    }

    public void addPromoList(List<PromoEntity> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    class PromoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_banner_promo)
        ImageView ivBannerPromo;
        @BindView(R.id.iv_wishlist)
        RoundedImageView ivWishlist;
        @BindView(R.id.iv_logo)
        ImageView ivLogo;
        @BindView(R.id.tv_description)
        TextView tvDescription;
        @BindView(R.id.btn_show_promo_detail)
        Button btnShowPromo;
        public PromoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void showFilterPromo(boolean filtering, List<PromoEntity> promoEntities) {
        this.filtering = filtering;
        filteredList = promoEntities;
        notifyDataSetChanged();
    }
}
