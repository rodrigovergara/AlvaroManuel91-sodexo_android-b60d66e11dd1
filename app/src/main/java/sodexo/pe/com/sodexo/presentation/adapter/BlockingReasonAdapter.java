package sodexo.pe.com.sodexo.presentation.adapter;

import android.app.Application;
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

/**
 * Created by Asahel on 05/09/2017.
 */

public class BlockingReasonAdapter extends BaseAdapter {
    private List<BlockingReasonEntity> list;
    private LayoutInflater layoutInflater;

    public BlockingReasonAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.list = new ArrayList<>();
    }

    public BlockingReasonAdapter(List<BlockingReasonEntity> list,Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.list = new ArrayList<>();
        this.list.add(new BlockingReasonEntity("0", "Elija una opción"));
        this.list.addAll(list);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public BlockingReasonEntity getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
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
