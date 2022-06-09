package com.example.bibliotekasmadrid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bibliotekasmadrid.R;
import com.example.bibliotekasmadrid.activitys.BusquedaLoc;
import com.example.bibliotekasmadrid.modelsPlaces.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListAdapterNoData extends RecyclerView.Adapter<ListAdapterNoData.ViewHolder> {
    private List<Graph> mData;
    private List<Graph> mDataCopy;
    private LayoutInflater mInflater;
    private Context context;
    private OnNoteListener mOnNoteListener;

    public ListAdapterNoData(List<Graph> itemList, Context context, OnNoteListener onNoteListener){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
        this.mOnNoteListener = onNoteListener;
        mDataCopy = new ArrayList<>();
        this.mDataCopy.addAll(mData);
    }

    @Override
    public int getItemCount() { return mData.size(); }

    @Override
    public ListAdapterNoData.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.list_element,null);
        return new ListAdapterNoData.ViewHolder(view,mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(final ListAdapterNoData.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));
    }

    public void reloadView(List<Graph> ListaNueva){
        mData.clear();
        mData.addAll(ListaNueva);
        notifyDataSetChanged();
    }

    public void setItems(List<Graph> items){mData = items; }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView iconImage;
        TextView name, city;
        OnNoteListener onNoteListener;

        ViewHolder(View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            iconImage = itemView.findViewById(R.id.iconImageView);
            name = itemView.findViewById(R.id.nameTextView);
            city = itemView.findViewById(R.id.cityTextView);
            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);
        }

        void bindData(final Graph item){
            name.setText(item.getTitle());
            city.setText("No se dispone de información");
            if (item.getTitle().contains("estudio"))
            iconImage.setImageResource(R.drawable.ic_studying_svgrepo_com);
            else if (item.getTitle().contains("Biblioteca"))
            iconImage.setImageResource(R.drawable.ic_college_studying_svgrepo_com);
        }

        @Override
        public void onClick(View view) {
            onNoteListener.onNoteClick(getBindingAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }

}
