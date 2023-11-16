package com.ramanoraglobal.SmilyApp.Adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.ramanoraglobal.SmilyApp.ModelClass.ModelClass;
import com.ramanoraglobal.SmilyApp.R;

import java.util.ArrayList;


/**
 * Created by admin on 20/03/2018.
 */

public class VisitorAdaptor extends RecyclerView.Adapter<VisitorAdaptor.DataHolder> {


    private Context mContext;


    public static ArrayList<ModelClass> visitorlist;



    public VisitorAdaptor(Context mContext, ArrayList<ModelClass> visitorlist) {
        this.mContext = mContext;
        this.visitorlist = visitorlist;


    }

    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.rowlayout, parent, false);
        DataHolder dataHolder = new DataHolder(view);
        return dataHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(DataHolder holder, final int position) {

        // final Form form = mArrayListForm.get(position);
        final ModelClass form = visitorlist.get(position);

        holder.name.setText(form.getVisitorname());
        holder.orgnization.setText(form.getVisitororgnizzation());

        holder.designation.setText(form.getVisitordesignation());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

    }

    @Override
    public int getItemCount() {
        return visitorlist.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }



/*
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    mFilteredList = mArrayListModel;
                } else {

                    ArrayList<Model> filteredList = new ArrayList<>();

                    for (Model model : mArrayListModel) {

                        if (model.getShareleadName().toLowerCase().contains(charString) || model.getShareleadName().toUpperCase().contains(charString))

                            filteredList.add(model);
                    }


                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<Model>) filterResults.values;

                notifyDataSetChanged();
            }
        };
    }
*/


    public class DataHolder extends RecyclerView.ViewHolder {
        TextView name, orgnization, designation;

        public DataHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            orgnization = itemView.findViewById(R.id.orgnization);
            designation = itemView.findViewById(R.id.designation);

        }
    }


}
