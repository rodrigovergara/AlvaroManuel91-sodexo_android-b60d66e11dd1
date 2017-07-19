package sodexo.pe.com.sodexo.presentation.adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.tismart.tsmviews.views.CustomTextView;

import java.util.ArrayList;
import java.util.List;

import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.data.repository.GoogleDataRepository;
import sodexo.pe.com.sodexo.domain.entity.PlaceEntity;

/**
 * Created by RONALD on 18/08/2016.
 */
public class PlaceAutocompleteAdapter extends BaseAdapter implements Filterable {

    private static final int MAX_RESULTS = 10;
    private Context context;
    private List<PlaceEntity> resultList = new ArrayList<>();

    public PlaceAutocompleteAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return resultList.size();
    }

    @Override
    public PlaceEntity getItem(int i) {
        return resultList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null || !(view.getTag() instanceof ViewHolder)) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_places, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvPlace.setText(getItem(i).getAddress());
        return view;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();
                if (charSequence != null) {
                    List<PlaceEntity> placeEntities = null;
                    placeEntities = findPlaces(charSequence.toString());
                    filterResults.values = placeEntities;
                    filterResults.count = placeEntities.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                if (filterResults != null && filterResults.count > 0) {
                    resultList = (List<PlaceEntity>) filterResults.values;
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };
    }

    private List<PlaceEntity> findPlaces(String address) {
        final GoogleDataRepository googleDataRepository = new GoogleDataRepository();
        return googleDataRepository.getAllAddress(address);
    }

    static class ViewHolder {
        CustomTextView tvPlace;

        ViewHolder(View itemView) {
            tvPlace = (CustomTextView) itemView.findViewById(R.id.tvPlace);
        }
    }
}
