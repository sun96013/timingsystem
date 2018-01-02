package com.ivan.timingsystem;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.ivan.timingsystem.model.GetBillListModel;
import com.ivan.timingsystem.model.NomalModel;
import com.ivan.timingsystem.util.HttpUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class TimingActivity extends Activity {

    TextView etPhone, tvTime, tvTotlePeople, tvNowIn;
    Button btnEnter, btnExit;
    com.handmark.pulltorefresh.library.PullToRefreshListView listView;
    int refreshListIndex = 0;
    GetBillListModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏

        setContentView(R.layout.activity_timing);
        tvTime = (TextView) findViewById(R.id.tvTime);
        tvTotlePeople = (TextView) findViewById(R.id.tvTotlePeople);
        tvNowIn = (TextView) findViewById(R.id.tvNowIn);
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
                TextView tv = new TextView(TimingActivity.this);
                GetBillListModel.ResultBean bean = model.getResult().get(i-1);
                StringBuilder sb = new StringBuilder();
                sb.append("\n\r"+"预约人："+bean.getUser_Name() + "\n\r");
                sb.append("订单号："+bean.getBillCode() + "\n\r");
                sb.append("订单金额："+bean.getIAppMon() + "\n\r");
                sb.append("车牌："+bean.getCar_Brand() + "\n\r");
                sb.append("手机号："+bean.getUser_Phone() + "\n\r");
//                sb.append(bean.getUser_Name() + "\n\r");
//                sb.append(bean.getUser_Name() + "\n\r");


                tv.setText(sb.toString());
                tv.setTextSize(30);
                new AlertDialog.Builder(TimingActivity.this)
                        .setTitle("详情").setView(tv)
                        .setPositiveButton("确定", null)

                        .show();
//                Log.d("tag",BillNo);
//                Toast.makeText(TimingActivity.this," BillNo: "+BillNo,Toast.LENGTH_LONG).show();
            }
        });
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                InitData();
                listView.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                InitData();
                listView.onRefreshComplete();
            }
        });

        InitData();
        hideBottomUIMenu();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        handler.postDelayed(runnable, 1000);
    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (refreshListIndex == 60) {
                InitData();
                refreshListIndex = 0;
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");//可以方便地修改日期格式


            String date = dateFormat.format(new java.util.Date());
            tvTime.setText("当前时间：" + date);
            refreshListIndex++;
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

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date now = null;
//
//
//        now = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式


//        String strnow = dateFormat.format(now);
//        map.put("dDate", strnow);
//        map.put("dDate", "2017-10-30 09:00:00");
        hutil.requestAsyn("GetBillList", HttpUtil.TYPE_GET, map, new HttpUtil.ReqCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                //  Gson gson = new Gson();
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();


                model = gson.fromJson(result.toString(), GetBillListModel.class);
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


        HttpUtil hutil1 = new HttpUtil();
        HashMap<String, String> map1 = new HashMap<String, String>();

        hutil1.requestAsyn("GetBillCount", HttpUtil.TYPE_GET, map1, new HttpUtil.ReqCallBack<Object>() {
            @Override
            public void onReqSuccess(Object result) {
                //  Gson gson = new Gson();
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();


                final NomalModel model = gson.fromJson(result.toString(), NomalModel.class);
                if (model.getResultStatus().equals("Success")) {
                    Log.d("onReqSuccess", "Success");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String[] strarray = model.getResult().toString().split(",");
                            tvTotlePeople.setText(strarray[0] + "人");
                            tvNowIn.setText(strarray[1] + "人");
                            //   listView.setAdapter(new PullToRefreshListViewAdapter(TimingActivity.this, model.getResult()));
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
                                //  Toast.makeText(TimingActivity.this, model.getResult(), Toast.LENGTH_SHORT).show();
                                if (model.getResultStatus().equals("Success")) {
                                    etPhone.setText("");
                                    InitData();
                                } else {
                                    Toast.makeText(TimingActivity.this, model.getResult().toString(), Toast.LENGTH_SHORT).show();
                                }
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
                        hutil.requestAsyn("UpdateBillUseEnd", HttpUtil.TYPE_GET, map, new HttpUtil.ReqCallBack<Object>() {
                            @Override
                            public void onReqSuccess(Object result) {
                                Gson gson = new Gson();
                                NomalModel model = gson.fromJson(result.toString(), NomalModel.class);

                                if (model.getResultStatus().equals("Success")) {
                                    etPhone.setText("");
                                    InitData();

                                } else {
                                    Toast.makeText(TimingActivity.this, model.getResult().toString(), Toast.LENGTH_SHORT).show();
                                }
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
                viewHolder.tvRefName = (TextView) convertView.findViewById(R.id.tvRefName);
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
            viewHolder.tvRefName.setText(list.get(position).getUser_Name() + "");
            viewHolder.tvInTime.setText(list.get(position).getDStartTime());
            viewHolder.tvOutTime.setText(list.get(position).getDEndTime());
            Calendar calendar = Calendar.getInstance();
            String hour = list.get(position).getDEndTime().replace(":00", "");
            calendar.setTime(list.get(position).getdReservationDay());
            int inthour = Integer.parseInt(hour);
            calendar.set(Calendar.HOUR_OF_DAY, inthour);
            long end = calendar.getTimeInMillis();
            Calendar startcalendar = Calendar.getInstance();
            long begin = startcalendar.getTimeInMillis();
            long min = (begin - end) / (1000 * 60);
            if (min > 0) {
                viewHolder.tvOvertime.setText((int) min + "");
            } else {
                viewHolder.tvOvertime.setText("0");
            }
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//            String dateStr = sdf.format(calendar.getTime());


            return convertView;
        }

        class ViewHolder {
            TextView tvCarNo;
            TextView tvRefName;
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

        Log.d("onKeyDown", "shuzi" + keyCode);

        if ((keyCode - 7) >= 0 && (keyCode - 7) < 10) {
            etPhone.setText(etPhone.getText().toString() + (keyCode - 7));
        }

        return false;
        //return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return false;
        //  return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideBottomUIMenu();
    }

    /**
     * 隐藏虚拟按键，并且全屏
     */
    protected void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }
}
