package com.littleutil.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.littleutil.R;
import com.littleutil.bean.ServiceBean;

public class ServiceAdapter extends ArrayAdapter<ServiceBean>{
	
	private Activity activity;
	private ViewHolder mHolder;
	public ArrayList<ServiceBean> item = new ArrayList<ServiceBean>();

	public ServiceAdapter(Activity activity, int textViewResourceId, ArrayList<ServiceBean> items) {
		super(activity, textViewResourceId, items);
		this.item = items;
		this.activity = activity;
	}

	@Override
	public int getCount() {
		return item.size();
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.service_row, null);
			mHolder = new ViewHolder();
			v.setTag(mHolder);

			mHolder.tvServicename = (TextView) v.findViewById(R.id.tvServicename);
			mHolder.ll_service = (LinearLayout) v.findViewById(R.id.ll_service);

		} else {
			mHolder = (ViewHolder) v.getTag();
		}

		final ServiceBean bean = item.get(position);
		if(position != 0){
			if (bean != null) {
				mHolder.tvServicename.setText(bean.getService_name());
				if((position) % 2 == 0 ){
					mHolder.ll_service.setBackgroundResource(R.drawable.home_servicebg);
				}else {
					mHolder.ll_service.setBackgroundResource(R.drawable.general_servicebg);
				}
			}
		}
		return v;
	}

	class ViewHolder {
		public TextView tvServicename;
		public LinearLayout ll_service;
	}
}
