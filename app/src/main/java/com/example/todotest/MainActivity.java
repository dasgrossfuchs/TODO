package com.example.todotest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button mAddButton;
    private RecyclerView mRv;
    private RecyclerView.Adapter mAdapter_rv;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<todo_groups> mListaGrupos;
    private ArrayList<elemento> mListaElementos;
    private TodoSorter ts;

    public static WeakReference<MainActivity> mWeakReference;
    public static MainActivity getInstance(){
        return mWeakReference.get();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        mWeakReference = new WeakReference<>(MainActivity.this);

        mAddButton = findViewById( R.id.button_addElement );

        createList();
        buildRecyclerView();
    }

    public void createList(){

        mListaGrupos = new ArrayList<>();
        mListaElementos = new ArrayList<>();
        mListaElementos.add(new elemento("Tarea nueva!1",0));
        mListaElementos.add(new elemento("Tarea nueva!2",1));
        mListaElementos.add(new elemento("Tarea nueva!3",1));
        mListaElementos.add(new elemento("Tarea nueva!4",2));
        mListaElementos.add(new elemento("Tarea nueva!5",2));
        mListaElementos.add(new elemento("Tarea nueva!6",2));
        mListaElementos.add(new elemento("Tarea nueva!7",3));
        mListaElementos.add(new elemento("Tarea nueva!8",3));
        mListaElementos.add(new elemento("Tarea nueva!9",3));
        mListaElementos.add(new elemento("Tarea nueva!10",3));
        mListaElementos.add(new elemento("Tarea nueva!11",4));
        mListaElementos.add(new elemento("Tarea nueva!12",4));
        mListaElementos.add(new elemento("Tarea nueva!13",4));


        mListaElementos.add(new elemento("Tarea nueva!1",true,0));
        mListaElementos.add(new elemento("Tarea nueva!2",true,0));
        mListaElementos.add(new elemento("Tarea nueva!3",true,1));
        mListaElementos.add(new elemento("Tarea nueva!4",true,1));
        mListaElementos.add(new elemento("Tarea nueva!5",true,3));
        mListaElementos.add(new elemento("Tarea nueva!6",true,4));
        mListaElementos.add(new elemento("Tarea nueva!7",true,4));

        mListaGrupos.add( new todo_groups( "Pendientes",0 ) );
        mListaGrupos.add( new todo_groups( "Lista 2",1) );
        mListaGrupos.add( new todo_groups( "Lista 2",2) );
        mListaGrupos.add( new todo_groups( "Lista 2",3) );
        mListaGrupos.add( new todo_groups( "Lista 2",4) );
        ts = new TodoSorter(mListaElementos,mListaGrupos);
        mListaGrupos = ts.getListaGrupo();
    }

    // TODO BORRAR SI INNECESARIO
//    public void addItem(@NonNull elemento e){
//        if(e.isEstado()){
//            mListaGrupos.get(e.getGroup()).additemLista_checked(e);
//        }
//        else{mListaGrupos.get(e.getGroup()).additemLista_unchecked(e);}
//    }
//    public void addItem(int index, @NonNull elemento e){
//        if(e.isEstado()){
//            mListaGrupos.get(e.getGroup()).additemLista_checked(e,index);
//        }
//        else{mListaGrupos.get(e.getGroup()).additemLista_unchecked(e,index);}
//    }
//    public void removeItem(int index,int grupo, boolean estado){
//        if(estado){
//        mListaGrupos.get(grupo).removeitemLista_checked(index);
//        }
//        else{mListaGrupos.get(grupo).removeitemLista_unchecked(index);}
//    }

    public void buildRecyclerView(){
        mRv = findViewById( R.id.recyclerView_todogroups );
        mRv.setHasFixedSize( false );
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter_rv = new ExampleAdapter( mListaGrupos );
        mRv.setLayoutManager( mLayoutManager );
        mRv.setAdapter( mAdapter_rv );
    }
    public void Logger(String s){
        Log.d("GAY",s);
    } // TODO solo es para ver desde mas adentro de la matrix



}

