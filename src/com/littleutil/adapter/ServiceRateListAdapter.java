package com.littleutil.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.littleutil.R;
import com.littleutil.bean.ServiceRatesBean;

public class ServiceRateListAdapter extends ArrayAdapter<ServiceRatesBean>{
	
	private Activity activity;
	private ViewHolder mHolder;
	public ArrayList<ServiceRatesBean> item = new ArrayList<ServiceRatesBean>();

	
	public ServiceRateListAdapter(Activity activity, int textViewResourceId, ArrayList<ServiceRatesBean> items) {
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
			v = vi.inflate(R.layout.service_rate_row, null);
			mHolder = new ViewHolder();
			v.setTag(mHolder);

			mHolder.tv_servicename = (TextView) v.findViewById(R.id.tv_servicename);
			mHolder.tv_rate = (TextView) v.findViewById(R.id.tv_rate);

		} else {
			mHolder = (ViewHolder) v.getTag();
		}

		final ServiceRatesBean member = item.get(position);

		if (member != null) {
			mHolder.tv_servicename.setText(member.getName());
			mHolder.tv_rate.setText(member.getRates());
		}
		return v;
	}

	class ViewHolder {
		public TextView tv_servicename,tv_rate;
	}
}
