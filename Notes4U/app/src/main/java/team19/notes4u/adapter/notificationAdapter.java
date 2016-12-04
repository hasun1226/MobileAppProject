package team19.notes4u.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import team19.notes4u.DB.Request;
import team19.notes4u.R;

/**
 * Created by Charga on 12/1/2016.
 */

public class notificationAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<Request> objects;

    private class ViewHolder {
        TextView message;
        TextView status;

    }

    public notificationAdapter(Context context, List<Request> objects) {
        inflater = LayoutInflater.from(context);
        this.objects = objects;
    }

    public int getCount() {
        return objects.size();
    }

    public Request getItem(int position) {
        return objects.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.listitem, null);
            holder.status = (TextView) convertView.findViewById(R.id.statusOfRequest);
            holder.message = (TextView) convertView.findViewById(R.id.messageOfRequest);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.status.setText(Request.changeStatus(objects.get(position).getStatus()));
        return convertView;
    }
}
