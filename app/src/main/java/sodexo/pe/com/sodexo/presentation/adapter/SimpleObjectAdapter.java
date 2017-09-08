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
import sodexo.pe.com.sodexo.domain.entity.BlockingReasonEntity;
import sodexo.pe.com.sodexo.domain.entity.CardEntity;
import sodexo.pe.com.sodexo.domain.entity.StringWithTag;

/**
 * Created by Asahel on 07/09/2017.
 */

public class SimpleObjectAdapter extends BaseAdapter {
    private List<StringWithTag> list;
    private LayoutInflater layoutInflater;

    public SimpleObjectAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.list = new ArrayList<>();
    }

    public SimpleObjectAdapter(Context context,List<StringWithTag> list) {
        this.layoutInflater = LayoutInflater.from(context);
        this.list = new ArrayList<>();
        this.list.add(new StringWithTag("0", "Elija una opción"));
        this.list.addAll(list);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public StringWithTag getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        BlockingReasonAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_card, null);
            holder = new BlockingReasonAdapter.ViewHolder();
            holder.tvcardItem = (TextView) convertView.findViewById(R.id.tv_card);
            convertView.setTag(holder);
        } else {
            holder = (BlockingReasonAdapter.ViewHolder) convertView.getTag();
        }

        holder.tvcardItem.setText(list.get(i).getTag().toString());
        return convertView;
    }

    public static class ViewHolder {
        TextView tvcardItem;
    }
}
