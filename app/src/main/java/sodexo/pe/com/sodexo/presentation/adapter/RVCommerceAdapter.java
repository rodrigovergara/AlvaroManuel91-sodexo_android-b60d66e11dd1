package sodexo.pe.com.sodexo.presentation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.domain.entity.CommerceEntity;
import sodexo.pe.com.sodexo.presentation.SodexoApplication;
import sodexo.pe.com.sodexo.presentation.interfaces.MainView;

/**
 * Created by RONALD on 05/10/2016.
 */

public class RVCommerceAdapter extends RecyclerView.Adapter<RVCommerceAdapter.CommerceViewHolder> {
    private List<CommerceEntity> list;
    private List<CommerceEntity> filteredList;
    private MainView mainView;
    private boolean filtering;

    public RVCommerceAdapter(MainView mainView) {
        list = new ArrayList<>();
        filteredList  = new ArrayList<>();
        filtering = false;
        this.mainView = mainView;
    }

    @Override
    public CommerceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommerceViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_commerce, parent, false));
    }

    @Override
    public void onBindViewHolder(CommerceViewHolder holder, int position) {
        final CommerceEntity commerceEntity;
        if(!filtering)
            commerceEntity = list.get(position);
        else
            commerceEntity = filteredList.get(position);
        Glide.with(SodexoApplication.context).load(commerceEntity.getLogo()).into(holder.ivItemCommerce);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mainView != null) {
                    mainView.openCommerce(commerceEntity, false,false);
                }
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

    public void addCommerces(List<CommerceEntity> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void showFilterCommerce(boolean filtering, List<CommerceEntity> commerceEntities) {
        this.filtering = filtering;
        filteredList = commerceEntities;
        notifyDataSetChanged();
    }

    class CommerceViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_item_commerce)
        ImageView ivItemCommerce;

        public CommerceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
