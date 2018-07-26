package com.example.vitor.dummycook;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class StepsListAdapter extends BaseAdapter {


    private final List<Step> steps;
    private final Activity act;

    public StepsListAdapter(List<Step> stepsList, Activity act) {
        this.steps = stepsList;
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
        Step stepSelected = steps.get(position);


        TextView text_numStepsList = (TextView) view.findViewById(R.id.text_numStepslist);
        TextView title_textStepsList = (TextView) view.findViewById(R.id.text_titleStepslist);
        TextView text_detailsStepList = (TextView) view.findViewById(R.id.text_detailsSteplist);

        text_numStepsList.setText(""+(position+1));
        title_textStepsList.setText(stepSelected.getTitleStep());
        text_detailsStepList.setText(stepSelected.getTextStep());

        return view;

    }
}
