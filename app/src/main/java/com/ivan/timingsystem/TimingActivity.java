package com.ivan.timingsystem;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ivan.timingsystem.model.GetBillListModel;
import com.ivan.timingsystem.util.HttpUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class TimingActivity extends AppCompatActivity {

    TextView etPhone;
    Button btnEnter, btnExit;
    com.handmark.pulltorefresh.library.PullToRefreshListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timing);
        etPhone = (TextView) findViewById(R.id.etPhone);
        btnEnter = (Button) findViewById(R.id.btnEnter);
        btnExit = (Button) findViewById(R.id.btnExit);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EnterFunciton();
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExitFunciton();
            }
        });
        listView = (com.handmark.pulltorefresh.library.PullToRefreshListView) findViewById(R.id.customerListview);


        InitData();
    }

    public void KeyBoardClick(View view) {
        switch (view.getId()) {
            case R.id.btnClear:
                etPhone.setText("");
                break;
            case R.id.btnNull:
                break;
            default:
                String tempstr = "" + etPhone.getText() + ((Button) view).getText();
                etPhone.setText(tempstr);
                break;

        }
    }

    private  void InitData()
    {
        HttpUtil hutil = new HttpUtil();
        HashMap<String, String> map = new HashMap<String, String>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = null;


        now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式


        String strnow = dateFormat.format(now);
//        map.put("dDate",strnow);
        map.put("dDate", "2017-10-30 09:00:00");
        hutil.requestAsyn("GetBillList", HttpUtil.TYPE_GET, map, new HttpUtil.ReqCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                Gson gson = new Gson();
                final GetBillListModel model = gson.fromJson(result.toString(), GetBillListModel.class);
                if (model.getResultStatus().equals("Success")) {
                    Log.d("onReqSuccess", "Success");
                    runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listView.setAdapter(new PullToRefreshListViewAdapter(TimingActivity.this,model.getResult()));
                    }
                });

//                   for (GetBillListModel.ResultBean bean:model.getResult()) {
//                       Log.d("onReqSuccess",bean.getBillCode());
//                   }
                }
                //    Log.d("onReqSuccess",result.toString());
            }

            @Override
            public void onReqFailed(String errorMsg) {

            }
        });
    }

    public void EnterFunciton() {
//        OkHttpClient mOkHttpClient=new OkHttpClient();
//        Request.Builder requestBuilder = new Request.Builder().url(Config.Url+"GetBillList");
//        //可以省略，默认是GET请求
//        requestBuilder.method("GET",null);
//        Request request = requestBuilder.build();
//        Call mcall= mOkHttpClient.newCall(request);
//        mcall.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                if (null != response.cacheResponse()) {
//                    String str = response.cacheResponse().toString();
//                    Log.i("wangshu", "cache---" + str);
//                } else {
//                    response.body().string();
//                    String str = response.networkResponse().toString();
//                    Log.i("wangshu", "network---" + str);
//                }
//                String result = response.body().string();
//                Log.i("wangshu", "result---" + result);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                     //   String str = response.body().string();
//                        Toast.makeText(getApplicationContext(), "请求成功", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });

        HttpUtil hutil = new HttpUtil();
        HashMap<String, String> map = new HashMap<String, String>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = null;


        now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式


        String strnow = dateFormat.format(now);
//        map.put("dDate",strnow);
        map.put("dDate", "2017-10-30 09:00:00");
        hutil.requestAsyn("GetBillList", HttpUtil.TYPE_GET, map, new HttpUtil.ReqCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                Gson gson = new Gson();
                GetBillListModel model = gson.fromJson(result.toString(), GetBillListModel.class);
                if (model.getResultStatus().equals("Success")) {
                    Log.d("onReqSuccess", "Success");
//                   for (GetBillListModel.ResultBean bean:model.getResult()) {
//                       Log.d("onReqSuccess",bean.getBillCode());
//                   }
                }
                //    Log.d("onReqSuccess",result.toString());
            }

            @Override
            public void onReqFailed(String errorMsg) {

            }
        });
    }


    public void ExitFunciton() {

    }


    class PullToRefreshListViewAdapter extends BaseAdapter {
        private Context context;
        private List<GetBillListModel.ResultBean> list;

        public PullToRefreshListViewAdapter(Context context, List<GetBillListModel.ResultBean> list) {
            this.context = context;
            this.list = list;
        }

        public void addLast(List<GetBillListModel.ResultBean> list) {
            this.list.addAll(list);
        }

        @Override
        public int getCount() {
            if (list == null) {
                return 0;
            }
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {

                // 获取组件布局
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(R.layout.timing_item, null);

                viewHolder = new ViewHolder();
                viewHolder.tvCarNo = (TextView) convertView.findViewById(R.id.tvCarNo);
                viewHolder.tvPhone = (TextView) convertView.findViewById(R.id.tvPhone);
                viewHolder.tvInTime = (TextView) convertView.findViewById(R.id.tvInTime);
                viewHolder.tvOutTime = (TextView) convertView.findViewById(R.id.tvOutTime);
                viewHolder.tvOvertime = (TextView) convertView.findViewById(R.id.tvOvertime);
//                viewHolder.tvPhone = (TextView) convertView.findViewById(R.id.title);
                // 这里要注意，是使用的tag来存储数据的。
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            // 绑定数据、以及事件触发
        //    Log.d("Timing",list.get(position).getCar_ID()+"");
            viewHolder.tvCarNo.setText(list.get(position).getCar_ID()+"");
            viewHolder.tvPhone.setText(list.get(position).getUser_Phone()+"");
            viewHolder.tvInTime.setText(list.get(position).getDStartTime());
            viewHolder.tvOutTime.setText(list.get(position).getDEndTime());
            viewHolder.tvOvertime.setText("0");


            return convertView;
        }

        class ViewHolder {
            TextView tvCarNo;
            TextView tvPhone;
            TextView tvInTime;
            TextView tvOutTime;
            TextView tvOvertime;
        }
    }
}
