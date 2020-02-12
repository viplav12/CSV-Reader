package com.rail.csvrenderer.csv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rail.csvrenderer.R;

import java.util.ArrayList;
import java.util.List;

public class ItemArrayAdapter extends ArrayAdapter<String[]> {
    private List<String[]> userList = new ArrayList<String[]>();
    Context context = getContext().getApplicationContext();

    public ItemArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    @Override
    public void add(String[] object) {
        userList.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.userList.size();
    }

    @Override
    public String[] getItem(int index) {
        return this.userList.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ItemViewHolder viewHolder;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.item_layout, parent, false);


            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                ((int) ViewGroup.LayoutParams.MATCH_PARENT, (int) ViewGroup.LayoutParams.MATCH_PARENT);

            int numberofCount = getCount();
        for(int i= 0; i<numberofCount;i++ ) {
            TextView  tv = (TextView)row.findViewById(R.id.tv_dummy);
            tv = new TextView (getContext());
            params.leftMargin = 5;
            params.topMargin = i * 50;
            params.gravity = i;
            tv.setText(getItem(i)[i]);
            tv.setTextSize((float) 10);
            tv.setPadding(20, 50, 20, 50);
            tv.setLayoutParams(params);
        }


//            viewHolder = new ItemViewHolder();
//            viewHolder.firstName = row.findViewById(R.id.first_name);
//            viewHolder.surName = row.findViewById(R.id.sur_name);
//            viewHolder.issueCount = row.findViewById(R.id.issue_count);
//            viewHolder.dateOfBirth = row.findViewById(R.id.dob);
//            row.setTag(viewHolder);
        } else {
            viewHolder = (ItemViewHolder) row.getTag();
        }
//        String[] stat = getItem(position);
//        viewHolder.firstName.setText(stat[0]);
//        viewHolder.surName.setText(stat[1]);
//        viewHolder.issueCount.setText(stat[2]);
//        viewHolder.dateOfBirth.setText(stat[3]);
        return row;
    }

    static class ItemViewHolder {
        TextView firstName;
        TextView surName;
        TextView issueCount;
        TextView dateOfBirth;

    }
}
