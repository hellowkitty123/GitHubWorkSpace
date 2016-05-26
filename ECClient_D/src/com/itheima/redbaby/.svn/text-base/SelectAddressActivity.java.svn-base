package com.itheima.redbaby;

import java.util.List;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.itheima.redbaby.adapter.SelectAdressAdapter;
import com.itheima.redbaby.parser.AddressManageParser;
import com.itheima.redbaby.vo.AddressDetail;
import com.itheima.redbaby.vo.RequestVo;

/**
 * 地址选择，点击栏目返回<br>
 * data.getParcelableExtra("address") 获取数据 为AddressDetail 对象
 * 
 * @author liu
 * 
 */
public class SelectAddressActivity extends BaseWapperActivity implements OnItemClickListener {

	private ListView addressItemlv;
	private SelectAdressAdapter mAdapter;

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.head_back_text:
			finish();
			break;

		}
	}

	@Override
	protected void findViewById() {
		addressItemlv = (ListView) findViewById(R.id.address_manage_list);

		mAdapter = new SelectAdressAdapter(SelectAddressActivity.this);
		addressItemlv.setAdapter(mAdapter);
	}

	@Override
	protected void loadViewLayout() {
		setContentView(R.layout.address_manage_activity);
		setTitle(R.string.select_address);
		setHeadLeftBackgroundResource(R.drawable.new_head_normal_selector);
		setHeadLeftText(R.string.check_out);
		setHeadRightText(R.string.address_manage);
		setHeadRightVisibility(View.VISIBLE);

	}

	@Override
	protected void processLogic() {
		RequestVo reqVo = new RequestVo(R.string.url_addresslist, this, null, new AddressManageParser());
		getDataFromServer(reqVo, new DataCallback<List<AddressDetail>>() {

			@Override
			public void processData(List<AddressDetail> paramObject, boolean paramBoolean) {
				mAdapter.addAll(paramObject);
			}
		});
	}

	@Override
	protected void setListener() {
		addressItemlv.setOnItemClickListener(this);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 200 && resultCode == 200) {
			processLogic();
		}
	}

	@Override
	protected void onHeadRightButton(View v) {
		startActivityForResult(new Intent(this, AddressManageActivity.class), 200);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent data = new Intent();
		data.putExtra("address", mAdapter.getItem(position));
		setResult(200, data);
		finish();
	}
}
