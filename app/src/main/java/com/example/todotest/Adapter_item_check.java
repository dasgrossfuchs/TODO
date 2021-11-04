package com.example.todotest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_item_check extends RecyclerView.Adapter<Adapter_item_check.ItemViewHolder> {
    private ArrayList<elemento> mElementos;

    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView_todoitem;
        public CheckBox mCheckBox_estadoTodoItem;
        public TextView mTextView_hiddenId;


        public ItemViewHolder(View itemView) {
            super( itemView );
            mTextView_todoitem = itemView.findViewById(R.id.textView_todoitemtitulo );
            mTextView_hiddenId = itemView.findViewById( R.id.textView_todohiddenId );
            mCheckBox_estadoTodoItem = itemView.findViewById( R.id.checkbox_todoitem );

        }
    }

    public Adapter_item_check(ArrayList<elemento> ListaElementos){
        mElementos = ListaElementos;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        elemento elementoActual = mElementos.get( position );
        holder.mTextView_todoitem.setText( elementoActual.getTitulo() );
        holder.mTextView_hiddenId.setText( String.valueOf(elementoActual.getOrden()) );
        if (elementoActual.isEstado()){
            holder.mCheckBox_estadoTodoItem.setChecked( true );
        }
        else{holder.mCheckBox_estadoTodoItem.setChecked( false );}

        holder.mCheckBox_estadoTodoItem.setOnClickListener( new View.OnClickListener() { // TODO BORRAR SI INNECESARIO
            @Override
            public void onClick(View view) { // TODO EVENTOS POR ITEM
//                int x = Integer.parseInt(holder.mTextView_hiddenId.getText().toString());
//                elemento temp_element = mElementos.get(x);
//                int grupo = mElementos.get( x ).getGroup();
//                MainActivity.getInstance().Logger("Se cambio?, deberia ser grupo 0 : "+grupo+" ..posicion 0:"+x+"false:"+temp_element.isEstado());
//                MainActivity.getInstance().removeItem(x-1,grupo,true);
//                temp_element.setEstado(!temp_element.isEstado());
//                MainActivity.getInstance().addItem( temp_element );

            }
        } );
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
