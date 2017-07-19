package sodexo.pe.com.sodexo.presentation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.domain.entity.MovementEntity;

public class RVMovementAdapter extends RecyclerView.Adapter<RVMovementAdapter.MovementViewHolder> implements Filterable {

    private List<MovementEntity> filterList;
    private List<MovementEntity> list;
    private CustomFilter filter;

    public RVMovementAdapter() {
        this.filterList = new ArrayList<>();
        this.list = new ArrayList<>();
        this.filter = new CustomFilter();
    }

    @Override
    public MovementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovementViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movement, parent, false));
    }

    @Override
    public void onBindViewHolder(MovementViewHolder holder, int position) {
        MovementEntity entity = filterList.get(position);
        holder.tvDate.setText(entity.getDate());
        holder.tvCard.setText(entity.getCardNumber());
        holder.tvMount.setText(entity.getMount());
        holder.tvOperation.setText(entity.getOperation());
    }

    @Override
    public int getItemCount() {
        return filterList.size();
    }

    public void addList(List<MovementEntity> list) {
        this.filterList.addAll(list);
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return this.filter;
    }


    class MovementViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_card)
        TextView tvCard;
        @BindView(R.id.tv_operation)
        TextView tvOperation;
        @BindView(R.id.tv_mount)
        TextView tvMount;

        public MovementViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class CustomFilter extends Filter {

        private CustomFilter() {
            super();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            filterList.clear();
            final FilterResults results = new FilterResults();
            if (constraint.length() == 0) {
                filterList.addAll(list);
            } else {
                String filterPattern = constraint.toString().toUpperCase().trim();
                for (MovementEntity movement : list) {
                    if (movement.getOperation().contains(filterPattern)) {
                        filterList.add(movement);
                    }
                }
            }
            results.values = filterList;
            results.count = filterList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            notifyDataSetChanged();
        }
    }
}
