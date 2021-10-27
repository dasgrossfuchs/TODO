package com.example.todotest;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    public ArrayList<elemento> mElementos;
    public ArrayList<elemento> mElementos_Checked;
    private ArrayList<todo_groups> mGrupos;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder{
        // Por grupo
        public TextView mTextView_todogroupTitle;
        public ImageView mImageView_todogrouppin;
        public CheckBox mCheckBox_estadoTodoItem;
        public TextView mTextView_realizadas;

        // por lista
        private RecyclerView.Adapter mAdapter_rv;
        public RecyclerView mRv;
        public RecyclerView.LayoutManager mLayoutManager;
        private RecyclerView.Adapter mAdapter_rv_checked;
        public RecyclerView mRv_checked;
        public RecyclerView.LayoutManager mLayoutManager_checked;



        public ExampleViewHolder(View itemView) {
            super( itemView );
            mTextView_todogroupTitle = itemView.findViewById( R.id.textView_todogrouptitulo );
            mTextView_realizadas = itemView.findViewById( R.id.textView_realizado );
            mImageView_todogrouppin = itemView.findViewById(  R.id.ImageView_todogroup_pin);
            mCheckBox_estadoTodoItem = itemView.findViewById( R.id.checkbox_todoitem );
            mRv = itemView.findViewById( R.id.recyclerView_todoitems );
            mRv_checked = itemView.findViewById( R.id.recyclerView_todoitem_done );
            mLayoutManager = new LinearLayoutManager(itemView.getContext());
            mLayoutManager_checked = new LinearLayoutManager(itemView.getContext());

        }
    }
    public ExampleAdapter(ArrayList<todo_groups> listaGrupos){
        mGrupos = listaGrupos;
        mWeakReference = new WeakReference<>(ExampleAdapter.this);
    }
    public static WeakReference<ExampleAdapter> mWeakReference;
    public static ExampleAdapter getInstance(){
        return mWeakReference.get();
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        todo_groups grupoActual = mGrupos.get( position );
        mElementos = grupoActual.getListaEl_unchecked();
        mElementos_Checked = grupoActual.getListaEl_checked();
        holder.mTextView_todogroupTitle.setText( grupoActual.getTitulo_grupo() );
        if (grupoActual.isPinned()){
            holder.mImageView_todogrouppin.setImageResource(R.drawable.ic_round_push_pin_24);
        }
        if(mElementos_Checked.size() !=  0){ holder.mTextView_realizadas.setVisibility(View.VISIBLE);}
        if(mElementos_Checked.size() ==  0){ holder.mTextView_realizadas.setVisibility(View.GONE );}
        // Aqui tengo que poner la madre para hacer elementos
        buildRecyclerView( holder );
//        holder.mAdapter_rv = new Adapter_item(mElementos);
//        holder.mRv.setLayoutManager( holder.mLayoutManager );
//        holder.mRv.setAdapter( holder.mAdapter_rv );
        // Lo de arriba pero con checkados
//        holder.mAdapter_rv_checked = new Adapter_item_check(mElementos_Checked);
//        holder.mRv_checked.setLayoutManager( holder.mLayoutManager_checked );
//        holder.mRv_checked.setAdapter( holder.mAdapter_rv_checked );


        //TODO EVENTOS
    }

    public void buildRecyclerView(ExampleViewHolder holder){
        holder.mAdapter_rv = new Adapter_item(mElementos);
        holder.mRv.setLayoutManager( holder.mLayoutManager );
        holder.mRv.setAdapter( holder.mAdapter_rv );
        holder.mAdapter_rv_checked = new Adapter_item_check(mElementos_Checked);
        holder.mRv_checked.setLayoutManager( holder.mLayoutManager_checked );
        holder.mRv_checked.setAdapter( holder.mAdapter_rv_checked );
    }

    public void changeStateItem(int index,int group,boolean estado){ // TODO eventos por item
        elemento tempel;
        if (estado) {
            tempel = mGrupos.get(group).getListaEl_checked().get( index );
            mGrupos.get(group).removeitemLista_checked( index );
            tempel.setEstado( !estado);
            mGrupos.get( group ).additemLista_unchecked( tempel );


        }

    }


    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext()).inflate( R.layout.example_todogroup, parent,false );
       ExampleViewHolder evh = new ExampleViewHolder(v);
       return evh;
    }

    @Override
    public int getItemCount() {
        return mGrupos.size();
    }


}
