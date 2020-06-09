package com.example.meddata;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;

public class VisitAdapter extends ArrayAdapter<Visit> {
    public VisitAdapter(Context context, int resource, List<Visit> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.summarized_visit, parent, false);
        }

        TextView date = (TextView) convertView.findViewById(R.id.date_textView);
        TextView hospital= (TextView) convertView.findViewById(R.id.hospital_textView);

        Visit visit = getItem(position);

        date.setVisibility(View.VISIBLE);
        hospital.setVisibility(View.VISIBLE);
        date.setText(visit.getDate());
        hospital.setText(visit.getHospital());
        return convertView;
    }
}

