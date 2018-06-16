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
import sodexo.pe.com.sodexo.data.model.QuestionOptionsEntityData;

/**
 * Created by ronaldvelasquez on 14/12/16.
 */
public class SpinnerOptionAdapter extends BaseAdapter {
    private List<QuestionOptionsEntityData> list;
    private LayoutInflater layoutInflater;

    public SpinnerOptionAdapter(Context context) {
        this.list = new ArrayList<>();
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public QuestionOptionsEntityData getItem(int i) {
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
            holder.tvOption = (TextView) convertView.findViewById(R.id.tv_card);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvOption.setText(list.get(i).getTitle());
        return convertView;
    }

    public void addOptions(List<QuestionOptionsEntityData> options) {
        list.addAll(options);
        notifyDataSetChanged();
    }

    public static class ViewHolder {
        TextView tvOption;
    }
}
