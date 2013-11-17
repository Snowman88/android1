package com.example.mydatabaseproject.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mydatabaseproject.R;
import com.example.mydatabaseproject.model.Area;

public class AreaListAdapter extends BaseAdapter {
	private Context context;
	private List<Area> list;
	private LayoutInflater layoutInflater = null;
	
	public AreaListAdapter(Context context, List<Area>list){
		super();
		this.context = context;
		this.list = list;
		layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Area area = (Area) getItem(position);
		convertView = layoutInflater.inflate(R.layout.list_item2, null);
		
		TextView tv = (TextView) convertView.findViewById(R.id.list_item);
		tv.setText(String.format("OK%d: %s %dçÀ", area.getId(), area.getName(), area.getAge()));
		return convertView;
	}

}
