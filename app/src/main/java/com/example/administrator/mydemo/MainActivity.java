package com.example.administrator.mydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.greendao.dao.User;
import com.greendao.dao.UserDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private UserDao userDao;
    private Button addbut,querybut,alterbut,deletebut;
    private Long id;
    private User wendy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addbut=(Button)findViewById(R.id.addbut);
        querybut=(Button)findViewById(R.id.querybut);
        alterbut=(Button)findViewById(R.id.alterbut);
        deletebut=(Button)findViewById(R.id.deletebut);
        userDao = MyApplication.getInstances().getDaoSession().getUserDao();
        /*
         * 添加
         * */
        addbut.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {
                wendy = new User(null, "wendy");
                userDao.insert(wendy);//添加一个
                Log.d("添加了：===",""+ wendy);
            }
        });
        /*
         * 删除
         * */
        deletebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDao.deleteByKey(id);
                Log.d("删除了",""+id);
            }
        });
        /*
         * 修改
         * */
        alterbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wendy = new User(id, "wendy0803");
                userDao.update(wendy);
                Log.d("修改了：",""+wendy);
            }
        });
        /*
         *查询
         * */
        querybut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<User> users = userDao.loadAll();
                String userName = "";
                for (int i = 0; i < users.size(); i++) {
                    userName += users.get(i).getName()+",";
                    id = users.get(i).getId();
                }
                Log.d("查询全部数据==>",""+userName);

            }
        });
    }
}
