package sodexo.pe.com.sodexo.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.domain.entity.CargoEntity;
import sodexo.pe.com.sodexo.domain.entity.OperatorEntity;

/**
 * Created by RONALD on 07/10/2016.
 */

public class OperatorAdapter extends BaseAdapter {
    private List<OperatorEntity> list;
    private LayoutInflater layoutInflater;

    public OperatorAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.list = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public OperatorEntity getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void addCards(List<OperatorEntity> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public int getPosition(String id) {
        int position = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(id)) {
                position = i;
                break;
            } else {
                position = -1;
            }
        }
        return position;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_card, null);
            holder = new ViewHolder();
            holder.tvcardItem = (TextView) convertView.findViewById(R.id.tv_card);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvcardItem.setText(list.get(i).getDescription());
        return convertView;
    }

    public static class ViewHolder {
        TextView tvcardItem;
    }
}
