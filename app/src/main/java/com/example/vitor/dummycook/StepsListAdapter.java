package com.example.vitor.dummycook;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class StepsListAdapter extends BaseAdapter {


    private final List<String> steps;
    private final Activity act;

    public StepsListAdapter(List<String> steps, Activity act) {
        this.steps = steps;
        this.act = act;
    }

    @Override
    public int getCount() {
        return steps.size();
    }

    @Override
    public Object getItem(int position) {
        return steps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater()
                .inflate(R.layout.line_steps_list, parent, false);
        String curso = steps.get(position);


        TextView text_numStepsList = (TextView) view.findViewById(R.id.text_numStepslist);
        TextView title_textStepsList = (TextView) view.findViewById(R.id.text_titleStepslist);
        TextView text_detailsStepList = (TextView) view.findViewById(R.id.text_detailsSteplist);

        text_numStepsList.setText(""+position);
        title_textStepsList.setText(curso);
        text_detailsStepList.setText(curso);

        return view;

    }
}
