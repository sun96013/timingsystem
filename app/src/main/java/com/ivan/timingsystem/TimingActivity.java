package com.ivan.timingsystem;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ivan.timingsystem.model.GetBillListModel;
import com.ivan.timingsystem.model.NomalModel;
import com.ivan.timingsystem.util.HttpUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class TimingActivity extends Activity {

    TextView etPhone,tvTime;
    Button btnEnter, btnExit;
    com.handmark.pulltorefresh.library.PullToRefreshListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timing);
        tvTime=(TextView) findViewById(R.id.tvTime);
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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView temptvCardNo = (TextView) view.findViewById(R.id.tvCarNo);
                String BillNo = temptvCardNo.getTag().toString();

//              	new AlertDialog.Builder(TimingActivity.this)
//            .setTitle("您要选择的是").
//             setMultiChoiceItems(new String[] {"选项1","选项2"}, null, null)
//          	.setPositiveButton("确定", null)
//               .setNegativeButton("取消", null)
//              	.show();
//                Log.d("tag",BillNo);
//                Toast.makeText(TimingActivity.this," BillNo: "+BillNo,Toast.LENGTH_LONG).show();
            }
        });

        InitData();


        handler.postDelayed(runnable, 1000);
    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");//可以方便地修改日期格式



            String date=dateFormat.format(new java.util.Date());
            tvTime.setText("当前时间：" + date);
            handler.postDelayed(this, 1000);
        }
    };


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

    private void InitData() {
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
                            listView.setAdapter(new PullToRefreshListViewAdapter(TimingActivity.this, model.getResult()));
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


        new AlertDialog.Builder(TimingActivity.this)
                .setTitle("确认")
                .setMessage("确定入场吗？")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        HttpUtil hutil = new HttpUtil();
                        HashMap<String, String> map = new HashMap();

                        map.put("BillNo", etPhone.getText().toString());
                        hutil.requestAsyn("UpdateBillUseing", HttpUtil.TYPE_GET, map, new HttpUtil.ReqCallBack<Object>() {
                            @Override
                            public void onReqSuccess(Object result) {
                                Gson gson = new Gson();
                                NomalModel model = gson.fromJson(result.toString(), NomalModel.class);
                                Toast.makeText(TimingActivity.this, model.getResult(), Toast.LENGTH_SHORT).show();
//
                            }

                            @Override
                            public void onReqFailed(String errorMsg) {

                            }
                        });
                    }
                })
                .setNegativeButton("否", null)
                .show();


    }


    public void ExitFunciton() {
        new AlertDialog.Builder(TimingActivity.this)
                .setTitle("确认")
                .setMessage("确定退场吗？")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        HttpUtil hutil = new HttpUtil();
                        HashMap<String, String> map = new HashMap();

                        map.put("BillNo", etPhone.getText().toString());
                        hutil.requestAsyn("UpdateBillUseing", HttpUtil.TYPE_GET, map, new HttpUtil.ReqCallBack<Object>() {
                            @Override
                            public void onReqSuccess(Object result) {
                                Gson gson = new Gson();
                                NomalModel model = gson.fromJson(result.toString(), NomalModel.class);
                                Toast.makeText(TimingActivity.this, model.getResult(), Toast.LENGTH_SHORT).show();
//
                            }

                            @Override
                            public void onReqFailed(String errorMsg) {

                            }
                        });
                    }
                })
                .setNegativeButton("否", null)
                .show();
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
            viewHolder.tvCarNo.setTag(list.get(position).getBillCode() + "");
            viewHolder.tvCarNo.setText(list.get(position).getCar_ID() + "");
            viewHolder.tvPhone.setText(list.get(position).getUser_Phone() + "");
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



//    KEYCODE_0	按键'0'	7
//    KEYCODE_1	按键'1'	8
//    KEYCODE_2	按键'2'	9
//    KEYCODE_3	按键'3'	10
//    KEYCODE_4	按键'4'	11
//    KEYCODE_5	按键'5'	12
//    KEYCODE_6	按键'6'	13
//    KEYCODE_7	按键'7'	14
//    KEYCODE_8	按键'8'	15
//    KEYCODE_9	按键'9'	16
//    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        Log.d("onKeyDown", "shuzi"+keyCode);

        if((keyCode-7)>=0&&(keyCode-7)<10)
        etPhone.setText(etPhone.getText().toString() + (keyCode-7));
        return  false;
        //return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return  false;
      //  return super.onKeyUp(keyCode, event);
    }
}
