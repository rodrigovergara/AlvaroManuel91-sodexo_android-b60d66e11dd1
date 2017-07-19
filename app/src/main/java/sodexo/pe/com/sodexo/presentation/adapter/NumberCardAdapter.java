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
import sodexo.pe.com.sodexo.domain.entity.CardEntity;
import sodexo.pe.com.sodexo.domain.entity.CardEntityaasd;

/**
 * Created by RONALD on 07/10/2016.
 */

public class NumberCardAdapter extends BaseAdapter {
    private List<CardEntity> list;
    private LayoutInflater layoutInflater;

    public NumberCardAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.list = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CardEntity getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void addCards(List<CardEntity> list) {
        this.list.add(new CardEntity("0", "Elija una opción"));
        this.list.addAll(list);
        notifyDataSetChanged();
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

        holder.tvcardItem.setText(list.get(i).getCardCode());
        return convertView;
    }

    public static class ViewHolder {
        TextView tvcardItem;
    }
}
