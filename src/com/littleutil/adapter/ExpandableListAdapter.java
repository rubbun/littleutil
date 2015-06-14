package com.littleutil.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.littleutil.R;
import com.littleutil.bean.ServiceBean;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
	private Context _context;
	//private HashMap<String, List<String>> _listDataChild;
	private ArrayList<ServiceBean> list = new ArrayList<ServiceBean>();

	public ExpandableListAdapter(Context context, ArrayList<ServiceBean> list) {
		this._context = context;
		this.list = list;
	}

	@Override
	public Object getChild(int groupPosition, int childPosititon) {
		return list.get(groupPosition).getList().get(childPosititon);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

		//final String childText = (String) getChild(groupPosition, childPosition);

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.child_service, null);
		}
		ImageView iv_child_img = (ImageView)convertView.findViewById(R.id.iv_child_img);
		TextView txtListChild = (TextView) convertView.findViewById(R.id.tv_child_name);

		txtListChild.setText(list.get(groupPosition).getList().get(childPosition).name);
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return list.get(groupPosition).getList().size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return list.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return list.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		//String headerTitle = (String) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.root_service, null);
		}

		ImageView iv_grp_img = (ImageView)convertView.findViewById(R.id.iv_grp_img);
		if (isExpanded) {
			iv_grp_img.setImageResource(R.drawable.list_indicator);
        } else {
        	iv_grp_img.setImageResource(R.drawable.group_indicator_plus);
        }

		TextView lblListHeader = (TextView) convertView.findViewById(R.id.tv_root_name);
		lblListHeader.setText(list.get(groupPosition).service_name);

		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}
