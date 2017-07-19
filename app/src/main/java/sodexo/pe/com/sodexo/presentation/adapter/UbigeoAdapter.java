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
import sodexo.pe.com.sodexo.data.model.UbigeoEntityData;
import sodexo.pe.com.sodexo.domain.entity.CardEntity;

/**
 * Created by RONALD on 07/10/2016.
 */

public class UbigeoAdapter extends BaseAdapter {
    private List<UbigeoEntityData> list;
    private LayoutInflater layoutInflater;
    public UbigeoAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.list = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public UbigeoEntityData getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void addUbigeo(List<UbigeoEntityData> list) {
        this.list.addAll(list);
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

        holder.tvcardItem.setText(list.get(i).getName());
        return convertView;
    }

    public int getPosition(String department, String provincie, String district) {
        int position = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getDepartment().equals(department) && list.get(i).getProvince().equals(provincie) && list.get(i).getDistrict().equals(district)) {
                position = i;
                break;
            } else {
                position = -1;
            }
        }
        return position;
    }

    public static class ViewHolder {
        TextView tvcardItem;
    }
}
