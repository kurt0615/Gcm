package com.gbt.gcm.app;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class ListActivity extends Activity {

    private ArrayList<Map<String, Object>> dataSource;
    private Map map;
    private SimpleAdapter adapter;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        this.dataSource = new ArrayList();

        for (int i=0; i < 30; ++i){
            map = new LinkedHashMap();
            map.put("title","T" + String.valueOf(i));
            map.put("content","C" + String.valueOf(i));
            dataSource.add(map);
        }


        this.adapter = new ListAdapter(this,
                this.dataSource, R.layout.list_item,
                new String[] {
                        "title",
                        "content"
                },
                new int[] {
                        R.id.title,
                        R.id.content
                });
        lv = (ListView) this.findViewById(R.id.list);
        lv.setAdapter(this.adapter);

        Intent intent =  getIntent();
        String message = intent.getStringExtra("xx");
        message = intent.getStringExtra("yy");
        message = intent.getStringExtra("collapse_key");
        message = intent.getStringExtra("collapse_key");
        //message = intent.getStringExtra("data.x");
        //kurt add
       // this.registerReceiver(mMessageReceiver,new IntentFilter(this.getClass().getName()));
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter(this.getClass().getName()));


    }

    //kurt add
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String message = intent.getExtras().toString();
            map = new LinkedHashMap();
            map.put("title","T");
            map.put("content","C");
            dataSource.add(0,map);
            adapter.notifyDataSetChanged();
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class ListAdapter extends SimpleAdapter {
        private Context ctxt;
        private List<? extends Map<String, ?>> dataSource;
        public ListAdapter(Context context,
                               List<? extends Map<String, ?>> data, int resource,
                               String[] from, int[] to) {
            super(context, data, resource, from, to);
            this.ctxt = context;
            this.dataSource = data;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            final ViewHolder holder;
            if(convertView == null){
                view =  LayoutInflater.from(ctxt).inflate(R.layout.list_item,null);
                holder = new ViewHolder();
                holder.title = (TextView) view.findViewById(R.id.title);
                holder.content = (TextView) view.findViewById(R.id.content);
                view.setTag(holder);
            }else{
                holder = (ViewHolder) view.getTag();
            }

            Map map = this.dataSource.get(position);
            holder.title.setText(map.get("title").toString());
            holder.content.setText(map.get("content").toString());

            return view;
        }
    }

    private static class ViewHolder {
        TextView title;
        TextView content;
    }
}
