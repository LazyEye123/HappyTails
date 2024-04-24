package com.genuinecoder.springclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.genuinecoder.springclient.R;

public class EmployeeSortAdapter extends ArrayAdapter<String> {

    private static class ViewHolder {
        ImageView sortImage;
        TextView sort;
    }

    String[] sorts = {"Дешевле", "Много отзывов", "Выше рейтинг"};
    int[] sortImages = {R.drawable.price, R.drawable.review, R.drawable.rate};
    public EmployeeSortAdapter(Context context) {
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
            mViewHolder.sortImage = convertView.findViewById(R.id.sort_image);
            mViewHolder.sort = convertView.findViewById(R.id.sort_text);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        mViewHolder.sortImage.setImageResource(sortImages[position]);
        mViewHolder.sort.setText(sorts[position]);

        return convertView;
    }
}
