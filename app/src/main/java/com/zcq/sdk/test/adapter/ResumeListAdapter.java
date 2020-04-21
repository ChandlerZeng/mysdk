package com.zcq.sdk.test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zcq.sdk.test.R;
import com.zcq.sdk.test.entity.ResumeInfo;

import java.util.List;

public class ResumeListAdapter extends RecyclerView.Adapter<ResumeListAdapter.ViewHolder> {
    private List<ResumeInfo> resumeList;
    private Context context;
    private LayoutInflater inflater;
    public ResumeListAdapter(Context context, List<ResumeInfo> resumeList){
        this.resumeList = resumeList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_resume_list,parent,false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ResumeInfo resumeInfo = resumeList.get(position);
        holder.tvName.setText(resumeInfo.getName());
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return resumeList.size();
    }


    public void setData(List<ResumeInfo> resumeList){
        this.resumeList = resumeList;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        TextView tvNumber;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);

        }
    }
}
