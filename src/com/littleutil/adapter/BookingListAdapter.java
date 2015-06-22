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
import com.littleutil.bean.BookingReqBean;
import com.littleutil.bean.SubServiceBean;

public class BookingListAdapter extends ArrayAdapter<BookingReqBean>{
	
	private Activity activity;
	private ViewHolder mHolder;
	public ArrayList<BookingReqBean> item = new ArrayList<BookingReqBean>();

	public BookingListAdapter(Activity activity, int textViewResourceId, ArrayList<BookingReqBean> items) {
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
			v = vi.inflate(R.layout.bookin_req_row, null);
			mHolder = new ViewHolder();
			v.setTag(mHolder);

			mHolder.tvOrderId = (TextView) v.findViewById(R.id.tv_order_id);
			mHolder.tvOrderdate = (TextView) v.findViewById(R.id.tv_booking_date);
			mHolder.tvStatus = (TextView) v.findViewById(R.id.tv_booking_status);

		} else {
			mHolder = (ViewHolder) v.getTag();
		}

		final BookingReqBean member = item.get(position);

		if (member != null) {
			mHolder.tvOrderId.setText(member.getID());
			mHolder.tvOrderdate.setText(member.getREQUEST_DATE());
			mHolder.tvStatus.setText(member.getSTATUS());
		}
		return v;
	}

	class ViewHolder {
		public TextView tvOrderId,tvOrderdate,tvStatus;
	}
}
