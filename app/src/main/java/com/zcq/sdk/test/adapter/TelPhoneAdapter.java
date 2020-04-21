package com.zcq.sdk.test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zcq.sdk.test.R;
import com.zcq.sdk.test.entity.PhoneBean;

import java.util.List;

public class TelPhoneAdapter extends BaseAdapter {
    private List<PhoneBean> phoneBeanList;
    private Context context;
    private LayoutInflater inflater;
    public TelPhoneAdapter(Context context, List<PhoneBean> phoneBeans){
        this.phoneBeanList = phoneBeans;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return phoneBeanList.size();
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
        PhoneBean bean = phoneBeanList.get(position);
        if(convertView==null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.tel_item,null);
            holder.tvName = convertView.findViewById(R.id.name);
            holder.tvNumber = convertView.findViewById(R.id.number);
            holder.tvStatus = convertView.findViewById(R.id.status);
            holder.tvTime = convertView.findViewById(R.id.time);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvName.setText(bean.getName());
        holder.tvNumber.setText(bean.getNumber());
        holder.tvStatus.setText(bean.getStatus()+"");
        holder.tvTime.setText(bean.getTime());
        return convertView;
    }

    public void setData(List<PhoneBean> phoneBeans){
        this.phoneBeanList = phoneBeans;
        notifyDataSetChanged();
    }

    static class ViewHolder{
        TextView tvName;
        TextView tvNumber;
        TextView tvTime;
        TextView tvStatus;
    }
}
