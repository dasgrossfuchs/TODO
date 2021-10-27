package com.example.todotest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_item extends RecyclerView.Adapter<Adapter_item.ItemViewHolder> {
    private ArrayList<elemento> mElementos;


    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView_todoitem;
        public CheckBox mCheckBox_estadoTodoItem;


        public ItemViewHolder(View itemView) {
            super( itemView );
            mTextView_todoitem = itemView.findViewById(R.id.textView_todoitemtitulo );
            mCheckBox_estadoTodoItem = itemView.findViewById( R.id.checkbox_todoitem );

        }
    }

    public Adapter_item(ArrayList<elemento> ListaElementos){
        mElementos = ListaElementos;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        elemento elementoActual = mElementos.get( position );
        holder.mTextView_todoitem.setText( elementoActual.getTitulo() );
        if (elementoActual.isEstado()){
            holder.mCheckBox_estadoTodoItem.setChecked( true );
        }
        else{holder.mCheckBox_estadoTodoItem.setChecked( false );}
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate( R.layout.example_todoitem, parent,false );
        ItemViewHolder evh = new ItemViewHolder(v);
        return evh;
    }

    @Override
    public int getItemCount() {
        return mElementos.size();
    }


}
