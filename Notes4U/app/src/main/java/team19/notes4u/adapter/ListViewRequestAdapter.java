package team19.notes4u.adapter;

import android.content.Context;
import android.graphics.Color;
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

public class ListViewRequestAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<Request> objects;

    private class ViewHolder {
        TextView courseCode;
        TextView status;
        TextView dateTime;
        TextView location;

    }

    public ListViewRequestAdapter(Context context, List<Request> objects) {
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
            holder.courseCode = (TextView) convertView.findViewById(R.id.courseCodeOfRequest);
            holder.status = (TextView) convertView.findViewById(R.id.statusOfRequest);
            holder.dateTime = (TextView) convertView.findViewById(R.id.dateTimeOfRequest);
            holder.location = (TextView) convertView.findViewById(R.id.locationOfRequest);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.courseCode.setText(objects.get(position).getCourse());
        holder.status.setText(Request.changeStatus(objects.get(position).getStatus()));
        if (objects.get(position).getStatus().equals("0")) {
            holder.status.setTextColor(Color.RED); //this is green color
        } else if (objects.get(position).getStatus().equals("1")) {
            holder.status.setTextColor(Color.YELLOW);
        } else if (objects.get(position).getStatus().equals("2")) {
            holder.status.setTextColor(Color.GREEN);
        }
        holder.location.setText(objects.get(position).getLocation());
        holder.dateTime.setText(objects.get(position).getDatetime());
        return convertView;
    }
}
