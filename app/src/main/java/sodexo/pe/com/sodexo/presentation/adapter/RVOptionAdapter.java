package sodexo.pe.com.sodexo.presentation.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.domain.entity.OptionEntity;
import sodexo.pe.com.sodexo.presentation.interfaces.OnClickOptions;
import sodexo.pe.com.sodexo.presentation.interfaces.OnClickOptionsMain;

public class RVOptionAdapter extends RecyclerView.Adapter<RVOptionAdapter.OptionViewHolder> {
    private List<OptionEntity> list;
    private Context context;
    private OnClickOptionsMain onClickOptions;

    public RVOptionAdapter(Context context, OnClickOptionsMain onClickOptions) {
        this.list = new ArrayList<>();
        this.context = context;
        this.onClickOptions = onClickOptions;
    }

    @Override
    public OptionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OptionViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_option, parent, false));
    }

    @Override
    public void onBindViewHolder(OptionViewHolder holder, int position) {
        final OptionEntity optionEntity = list.get(position);
        holder.btnOption.setBackground(ContextCompat.getDrawable(context, optionEntity.getIdImage()));
        holder.btnOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickOptions.clickListener(optionEntity.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addOptionList(List<OptionEntity> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    class OptionViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.btn_option)
        Button btnOption;

        public OptionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
