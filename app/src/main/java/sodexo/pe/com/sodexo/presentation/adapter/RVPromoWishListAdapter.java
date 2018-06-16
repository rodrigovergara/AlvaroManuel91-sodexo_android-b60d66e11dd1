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
import sodexo.pe.com.sodexo.presentation.fragment.intranet.PromoCommerceFragment;
import sodexo.pe.com.sodexo.presentation.interfaces.MainView;
import sodexo.pe.com.sodexo.util.AlertUtil;

/**
 * Created by RONALD on 05/10/2016.
 */

public class RVPromoWishListAdapter extends RecyclerView.Adapter<RVPromoWishListAdapter.PromoViewHolder> {
    private List<PromoEntity> list;
    private MainView mainView;
    private PromoCommerceFragment promoCommerceFragment;

    public RVPromoWishListAdapter(MainView mainView, PromoCommerceFragment promoCommerceFragment) {
        this.list = new ArrayList<>();
        this.mainView = mainView;
        this.promoCommerceFragment = promoCommerceFragment;
    }

    @Override
    public PromoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PromoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_promo, parent, false));
    }

    @Override
    public void onBindViewHolder(PromoViewHolder holder, int position) {
        final PromoEntity promo = list.get(position);
        final Context context = holder.itemView.getContext();
        Glide.with(SodexoApplication.context).load(promo.getPhoto()).into(holder.ivBannerPromo);
        Glide.with(SodexoApplication.context).load(promo.getImageLogo()).into(holder.ivLogo);
        holder.tvDescription.setText(promo.getDescription());
        holder.btnShowPromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainView.openPromoDetail(promo, true);
            }
        });
        holder.ivWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertUtil.showMessageAcceptNegative(context, null, "¿Deseas eliminar este comercio del wishlist?", "Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mainView.deletePromoWishList(promo, promoCommerceFragment);
                        dialogInterface.dismiss();
                    }
                }, "No", null);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addPromoList(List<PromoEntity> list) {
        this.list.clear();
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
}
