package com.example.ejercicio3_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>
{
    private List<ListEmployees> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapter(List<ListEmployees> itemList, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount()
    {
        return mData.size();
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = mInflater.inflate(R.layout.list_employees, null);
        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
        holder.bindData(mData.get(position));
    }

    public void setItems(List<ListEmployees> employees)
    {
        mData = employees;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, puesto, apellido;

        ViewHolder(View itemView)
        {
            super(itemView);
            apellido = itemView.findViewById(R.id.ApellidoEmp);
            nombre = itemView.findViewById(R.id.NombreEmp);
            puesto = itemView.findViewById(R.id.PuestoEmp);
        }

        void bindData(final ListEmployees employees)
        {
            apellido.setText(employees.getApellido());
            nombre.setText(employees.getNombre());
            puesto.setText(employees.getPuesto());
        }
    }
}