package com.etna.mob3.mob3.classes;

/**
 * Created by kumatetsu on 18/12/2017.
 */

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.etna.mob3.mob3.R;

import java.util.ArrayList;

/**
 * Created by jeremydebelleix on 06/12/2017.
 */

public class CustomAdapterCharts extends ArrayAdapter {

    private ArrayList<ListColumn> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
    }

    public CustomAdapterCharts(ArrayList data, Context context) {
        super(context, R.layout.row_item, data);

        this.dataSet = data;
        this.mContext = context;

    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public ListColumn getItem(int position) {
        Log.d("get view", "get item");

        return (ListColumn) dataSet.get(position);
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;
        final View result;

        Log.d("get view", "get view");

        // Si la vue n'existe pas encore on la créé
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.txtName);

            result = convertView;
            convertView.setTag(viewHolder);
        }
        // Sinon on la récupère
        else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        // l'item selectionné
        ListColumn item = getItem(position);

        viewHolder.txtName.setText(item.getName());
        viewHolder.txtName.setTextColor(Color.WHITE);

        return result;
    }

}
