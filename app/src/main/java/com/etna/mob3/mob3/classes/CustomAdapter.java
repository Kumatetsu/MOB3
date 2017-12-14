package com.etna.mob3.mob3.classes;

/**
 * Created by jeremydebelleix on 06/12/2017.
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

import java.io.File;
import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    private ArrayList<DataModel> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
    }

    public CustomAdapter(ArrayList data, Context context) {
        super(context, R.layout.row_item, data);

        this.dataSet = data;
        this.mContext = context;

    }

    private void removeFile(String path) {
        Log.d("Remove file", "remove " + path);

        File file = new File(path);
        file.delete();

        for (int i = 0; i < dataSet.size(); i++) {

            if (dataSet.get(i).path == path) {
                dataSet.remove(i);
                Log.d("Remove file", "at " + path);
                this.notifyDataSetChanged();
            }

        }

    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public DataModel getItem(int position) {
        Log.d("get view", "get item");

        return (DataModel) dataSet.get(position);
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
        DataModel item = getItem(position);

        viewHolder.txtName.setText(item.name);
        viewHolder.txtName.setTextColor(Color.WHITE);

        return result;
    }

}
