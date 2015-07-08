package com.littleutil.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.littleutil.R;
import com.littleutil.bean.SubServiceBean;
import com.littleutil.util.ImageLoader;

public class SubServiceAdapter extends ArrayAdapter<SubServiceBean>{
	
	private Activity activity;
	private ViewHolder mHolder;
	public ImageLoader imageLoader;
	public ArrayList<SubServiceBean> item = new ArrayList<SubServiceBean>();
	
	public SubServiceAdapter(Activity activity, int textViewResourceId, ArrayList<SubServiceBean> items) {
		super(activity, textViewResourceId, items);
		this.item = items;
		this.activity = activity;
		imageLoader=new ImageLoader(activity);
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
			v = vi.inflate(R.layout.sub_service_row, null);
			mHolder = new ViewHolder();
			v.setTag(mHolder);

			mHolder.tv_name = (TextView) v.findViewById(R.id.tv_name);
			mHolder.llRowBg = (LinearLayout) v.findViewById(R.id.llRowBg);

		} else {
			mHolder = (ViewHolder) v.getTag();
		}

		final SubServiceBean member = item.get(position);

		if (member != null) {
			mHolder.tv_name.setText(member.getName());
			imageLoader.DisplayImage(member.getImage_path(),mHolder.llRowBg);
		}
		
		if((position+1) % 2 == 0){
			mHolder.llRowBg.setGravity(Gravity.CENTER|Gravity.LEFT);
		}else{
			mHolder.llRowBg.setGravity(Gravity.CENTER|Gravity.RIGHT);
		}
		return v;
	}

	class ViewHolder {
		public TextView tv_name;
		public LinearLayout llRowBg;
	}
}
