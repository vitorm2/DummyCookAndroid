package com.example.vitor.dummycook;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.RecipeViewHolder> {

    private final List<Recipe> mRecipes;
    public Resources mContext;


    public MyAdapter(ArrayList recipes, Resources context) {
        mRecipes = recipes;
        mContext = context;
    }


    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecipeViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_line_view, parent, false));
    }


    // Preenche a RecycleView
    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        holder.titleRecipe.setText(String.format(Locale.getDefault(),
                mRecipes.get(position).getName()
        ));
        holder.textCost.setText(mRecipes.get(position).getCost());
        holder.textServes.setText(mRecipes.get(position).getServes());
        holder.textTime.setText(mRecipes.get(position).getTime());

        int resID = mContext.getIdentifier(mRecipes.get(position).getRecipeImg(), "drawable", "com.example.vitor.dummycook");
        holder.imageRecipe.setImageResource(resID);

        //  Caso haja um click em um item da list realiza o metodo changeIntent
        holder.moreButton1.setOnClickListener(view -> changeIntent(holder, position));
    }

    @Override
    public int getItemCount() {
        return mRecipes != null ? mRecipes.size() : 0;
    }

    // Muda para para DetailsActivity e passa a receita selecionada por Bundle
    private void changeIntent(RecipeViewHolder holder, int position) {

        Intent i =  new Intent(holder.context, DetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("recipeSelected", mRecipes.get(position));

        i.putExtras(bundle);

        holder.context.startActivity(i);
    }


    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        public TextView titleRecipe;
        public TextView textCost;
        public TextView textServes;
        public TextView textTime;
        public ImageView imageRecipe;
        public Button moreButton1;
        public final Context context;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            titleRecipe = (TextView) itemView.findViewById(R.id.title_recipe);
            textCost = (TextView) itemView.findViewById(R.id.text_cost);
            textServes = (TextView) itemView.findViewById(R.id.text_serves);
            textTime = (TextView) itemView.findViewById(R.id.text_time);
            imageRecipe = (ImageView) itemView.findViewById(R.id.image_recipe);
            imageRecipe.setClipToOutline(true); //ativa bordas
            context = itemView.getContext();

            moreButton1 = (Button) itemView.findViewById(R.id.button);
        }
    }


}
