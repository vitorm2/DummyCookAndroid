package com.example.vitor.dummycook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.PessoaViewHolder>{

    private final List<Pessoa> mUsers;


    public MyAdapter(ArrayList users) {
        mUsers = users;
        Log.d("aqui", "ta aqui");
    }


    @Override
    public PessoaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PessoaViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_line_view, parent, false));
    }



    @Override
    public void onBindViewHolder(PessoaViewHolder holder, int position) {
        holder.title.setText(String.format(Locale.getDefault(), "%s, %d - %s",
                mUsers.get(position).getName(),
                mUsers.get(position).getAge(),
                mUsers.get(position).getCity()
        ));


        holder.moreButton1.setOnClickListener(view -> updateItem(holder, position));
    }

    @Override
    public int getItemCount() {
        return mUsers != null ? mUsers.size() : 0;
    }

    /**
     * Método publico chamado para atualziar a lista.
     *
     * @param user Novo objeto que será incluido na lista.
     */
    public void updateList(Pessoa user) {
        insertItem(user);
    }

    // Método responsável por inserir um novo usuário na lista e notificar que há novos itens.
    private void insertItem(Pessoa user) {
        mUsers.add(user);
        notifyItemInserted(getItemCount());
    }

    // Método responsável por atualizar um usuário já existente na lista.
    private void updateItem(PessoaViewHolder holder, int position) {
        //Pessoa userModel = mUsers.get(position);
        //userModel.incrementAge();
        //notifyItemChanged(position);
        //Log.d("123", "POSIÇÃO: "+ position);
        Intent i =  new Intent(holder.context, DetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("pessoa", mUsers.get(position));

        i.putExtras(bundle);

        holder.context.startActivity(i);
    }

    // Método responsável por remover um usuário da lista.
    private void removerItem(int position) {
        mUsers.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mUsers.size());
    }

    public static class PessoaViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView moreButton;
        public Button moreButton1;
        public final Context context;

        public PessoaViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.main_line_title);
            moreButton = (ImageView) itemView.findViewById(R.id.main_line_more);
            moreButton.setClipToOutline(true); //ativa bordas
            context = itemView.getContext();

            moreButton1 = (Button) itemView.findViewById(R.id.button);
        }
    }

}
