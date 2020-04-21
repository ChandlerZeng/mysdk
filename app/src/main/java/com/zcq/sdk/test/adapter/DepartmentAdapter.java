package com.zcq.sdk.test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zcq.sdk.test.R;
import com.zcq.sdk.test.entity.Department;

import java.util.List;

public class DepartmentAdapter extends BaseAdapter {
    private List<Department> departList;
    private Context context;
    private LayoutInflater inflater;
    public DepartmentAdapter(Context context, List<Department> departList){
        this.departList = departList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return departList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Department bean = departList.get(position);
        if(convertView==null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.tel_item,null);
            holder.tvName = convertView.findViewById(R.id.name);
            holder.tvNumber = convertView.findViewById(R.id.number);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvName.setText(bean.getName());
        holder.tvNumber.setText(bean.getNumber());
        return convertView;
    }

    public void setData(List<Department> departList){
        this.departList = departList;
        notifyDataSetChanged();
    }

    static class ViewHolder{
        TextView tvName;
        TextView tvNumber;
    }
}
