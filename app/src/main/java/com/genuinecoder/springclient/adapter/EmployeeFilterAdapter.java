package com.genuinecoder.springclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.genuinecoder.springclient.R;

public class EmployeeFilterAdapter extends ArrayAdapter<String> {

    private static class ViewHolder {
        ImageView filterImage;
        TextView filter;
    }

    String[] sorts = {"Собака", "Кот"};
    int[] sortImages = {R.drawable.dog, R.drawable.cat};
    public EmployeeFilterAdapter(Context context) {
        super(context, R.layout.sort_spinner_row);
    }

    @Override
    public int getCount() {
        return sorts.length;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {

        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView,
                              ViewGroup parent) {
        ViewHolder mViewHolder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) getContext().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.sort_spinner_row, parent, false);
            mViewHolder.filterImage = convertView.findViewById(R.id.sort_image);
            mViewHolder.filter = convertView.findViewById(R.id.sort_text);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        mViewHolder.filterImage.setImageResource(sortImages[position]);
        mViewHolder.filter.setText(sorts[position]);

        return convertView;
    }
}
