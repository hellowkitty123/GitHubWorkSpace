package com.itheima.redbaby;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.itheima.redbaby.adapter.AddressManageAdapter;
import com.itheima.redbaby.adapter.AddressManageAdapter.OnItemButtonListener;
import com.itheima.redbaby.parser.AddressManageParser;
import com.itheima.redbaby.parser.SuccessParser;
import com.itheima.redbaby.util.NetUtil;
import com.itheima.redbaby.vo.AddressDetail;
import com.itheima.redbaby.vo.RequestVo;

/**
 * 地址管理
 * @author liu
 *
 */
public class AddressManageActivity extends BaseWapperActivity implements OnItemButtonListener, OnItemLongClickListener {

	private ListView addressItemlv;
	private AddressManageAdapter mAdapter;
 
	@Override
	public void onClick(View v) {

	}

	@Override
	protected void findViewById() {
		addressItemlv = (ListView) findViewById(R.id.address_manage_list);
		mAdapter = new AddressManageAdapter(this);
		mAdapter.setListener(this);
	}

	@Override
	protected void loadViewLayout() {
		setContentView(R.layout.address_manage_activity);
		setHeadRightText(R.string.address_manager_add);
		setHeadRightVisibility(View.VISIBLE);
		setTitle(R.string.address_manage);
	}

	@Override
	protected void onHeadRightButton(View v) {
		startActivityForResult(new Intent(this, AddAddressActivity.class), 200);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 200 && resultCode == 200) {
			// 更新地址列表
			ArrayList<AddressDetail> addressList = data.getParcelableArrayListExtra("addressList");
			mAdapter.clear();
			mAdapter.addAll(addressList);
		}
	}

	@Override
	protected void processLogic() {

		RequestVo reqVo = new RequestVo(R.string.url_addresslist, this, null, new AddressManageParser());
		getDataFromServer(reqVo, new DataCallback<List<AddressDetail>>() {

			@Override
			public void processData(List<AddressDetail> paramObject, boolean paramBoolean) {
				mAdapter.addAll(paramObject);
				addressItemlv.setAdapter(mAdapter);
			}
		});
	}

	@Override
	protected void setListener() {
		addressItemlv.setOnItemLongClickListener(this);
	}

	@Override
	public void onItemClick(View view, final int position) {
		switch (view.getId()) {
		case R.id.address_manage_update_btn:// 修改
			Intent intent = new Intent(this, AddAddressActivity.class);
			intent.putExtra("address", mAdapter.getItem(position));
			startActivityForResult(intent, 200);
			break;
		case R.id.address_manage_delete_btn:// 删除
			final AddressDetail item = mAdapter.getItem(position);
			Builder builder = new Builder(this);
			builder.setTitle(R.string.toast).setMessage("确定删除吗?");
			builder.setPositiveButton(R.string.yes, new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					HashMap<String, String> requestDataMap = new HashMap<String, String>();
					requestDataMap.put("id", item.getId() + "");
					RequestVo vo = new RequestVo(R.string.url_addressdelete, context, requestDataMap,
							new SuccessParser());
					Boolean bool = (Boolean) NetUtil.post(vo);
					if (bool != null) {
						if (bool) {
							mAdapter.remove(mAdapter.getItem(position));
							Toast.makeText(AddressManageActivity.this, R.string.delete_success, Toast.LENGTH_LONG)
									.show();
						}
					}
				}
			});
			builder.setNegativeButton(R.string.no, null);
			builder.show();
			break;
		}
	}

	@Override
	public void finish() {
		setResult(200);
		super.finish();
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
		final AddressDetail item = mAdapter.getItem(position);
		Builder builder = new Builder(this);
		builder.setTitle(R.string.toast).setMessage("确定设置默认地址吗?");
		builder.setPositiveButton(R.string.yes, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				HashMap<String, String> requestDataMap = new HashMap<String, String>();
				requestDataMap.put("id", item.getId() + "");
				RequestVo vo = new RequestVo(R.string.url_addressdefault, context, requestDataMap,
						new SuccessParser());
				Boolean bool = (Boolean) NetUtil.post(vo);
				if (bool != null) {
					if (bool) {
 						Toast.makeText(AddressManageActivity.this, R.string.set_success, Toast.LENGTH_LONG)
								.show();
					}
				}
			}
		});
		builder.setNegativeButton(R.string.no, null);
		builder.show();
 		return true;
	}

 
}
