package team19.notes4u.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ImageView;
import android.os.AsyncTask;


import java.util.List;

import team19.notes4u.DB.User;
import team19.notes4u.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.net.URL;
import java.io.IOException;
import java.io.InputStream;

import java.net.HttpURLConnection;

/**
 * Created by Charga on 12/4/2016.
 */

public class ProfileViewRequestAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<User> objects;

    private Bitmap profilePic;

    private class ViewHolder {
        ImageView profilePic;
        TextView username;

    }

    public ProfileViewRequestAdapter(Context context, List<User> objects) {
        inflater = LayoutInflater.from(context);
        this.objects = objects;
    }

    public int getCount() {
        return objects.size();
    }

    public User getItem(int position) {
        return objects.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.listitemprofiles, null);
            holder.profilePic = (ImageView) convertView.findViewById(R.id.profilePicListItem);
            holder.username = (TextView) convertView.findViewById(R.id.usernameListItem);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        profilePic = objects.get(position).getProfile_image();
        holder.profilePic.setImageBitmap(profilePic);
        holder.username.setText(objects.get(position).getEmail());
        return convertView;
    }
}
