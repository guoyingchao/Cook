package com.xiaoguo.caipuapp.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.LoginFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaoguo.caipuapp.R;
import com.xiaoguo.caipuapp.bean.FlowLayout;
import com.xiaoguo.caipuapp.urls.RecordSQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchActivity extends AppCompatActivity {

    private EditText editText;
    private ImageView deleteImage;
    private FlowLayout mFlowLayout;
    private List<String> historySearch;
    private TextView search;
    private TextView historyClear;
    private RecordSQLiteOpenHelper helper = new RecordSQLiteOpenHelper(this);
    private SQLiteDatabase sd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        editText = (EditText) findViewById(R.id.search_content);
        /**
         * 历史搜索
         */
        mFlowLayout = (FlowLayout) findViewById(R.id.hestory_flowlayout);

        historySearch = new ArrayList<>();
        search = (TextView) findViewById(R.id.search);
        historyClear = (TextView) findViewById(R.id.hestory_clear);
        getAllNumbers();
        initData();
        loopHistory();
        //清空搜索历史的监听事件
        historyClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("666", "清空历史记录onClick: ");

                Log.i("666", "searchonClick: "+ historySearch.size());
                mFlowLayout.removeAllViews();
                deleteData();
                getAllNumbers();
                loopHistory();
            }
        });
        /**
         * 设置输入框
         */

        deleteImage = (ImageView) findViewById(R.id.delete_image);

        deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                temp=s;
                deleteImage.setVisibility(View.VISIBLE);

            }
            @Override
            public void afterTextChanged(Editable s) {
                if (temp.length() == 0) {
                    deleteImage.setVisibility(View.GONE);
                }
            }
        });

    }

    private void initData() {
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textInput = editText.getText().toString();//获取输入内容
                // 按完搜索键后将当前查询的关键字保存起来,如果该关键字已经存在就不执行保存
                boolean hasData = hasData(textInput.trim());
                if (!hasData) {
                    insertData(textInput.trim());
                    Log.i("666", "search,进来了: ");
                    Pattern pattern = Pattern.compile("[u4e00-u9fa5]");//判断输入的是不是汉字
                Matcher m = pattern.matcher(textInput);//将格式和内容进行连接
                Log.i("666", "onClick: " + textInput);
                Log.i("666", "m----------------->: " + m.matches());
                if ( ! m.matches() && !textInput.equals("")){
                    Intent intent = new Intent();
                    intent.setClass(SearchActivity.this,SearchResultActivity.class);
                    startActivity(intent);
                    getAllNumbers();
                    mFlowLayout.removeAllViews();
                    Log.i("666", "history++++++++: "+historySearch.size());
//                    removeDuplicate(historySearch);
                    loopHistory();
                    Log.i("666", "hestorySearch: " + historySearch.size());
                }else {
                    Toast.makeText(SearchActivity.this, "请输入搜索内容", Toast.LENGTH_SHORT).show();
                 }
                }else{
                          Pattern pattern = Pattern.compile("[u4e00-u9fa5]");//判断输入的是不是汉字
                Matcher m = pattern.matcher(textInput);//将格式和内容进行连接
                Log.i("666", "onClick: " + textInput);
                Log.i("666", "m----------------->: " + m.matches());
                if ( ! m.matches() && !textInput.equals("")){
                    Intent intent = new Intent();
                    intent.setClass(SearchActivity.this,SearchResultActivity.class);
                    startActivity(intent);
                  //  getAllNumbers();
                    Log.i("666", "history++++++++: "+historySearch.size());
//                    removeDuplicate(historySearch);
                  //  loopHistory();
                    Log.i("666", "hestorySearch: " + historySearch.size());
                }else {
                    Toast.makeText(SearchActivity.this, "请输入搜索内容", Toast.LENGTH_SHORT).show();
                }
                }

                //正则表达式
        /*        Pattern pattern = Pattern.compile("[u4e00-u9fa5]");//判断输入的是不是汉字
                Matcher m = pattern.matcher(textInput);//将格式和内容进行连接
                Log.i("666", "onClick: " + textInput);
                Log.i("666", "m----------------->: " + m.matches());
                if ( ! m.matches() && !textInput.equals("")){
                    Intent intent = new Intent();
                    intent.setClass(SearchActivity.this,SearchResultActivity.class);
                    startActivity(intent);
                    getAllNumbers();
                    Log.i("666", "history++++++++: "+historySearch.size());
//                    removeDuplicate(historySearch);
                    loopHistory();
                    Log.i("666", "hestorySearch: " + historySearch.size());
                }else {
                    Toast.makeText(SearchActivity.this, "请输入搜索内容", Toast.LENGTH_SHORT).show();
                }*/
            }
        });
//        Log.i("666", "hestorySearch:------------------> " + hestorySearch.size());
    }

    public void backImageClick(View view) {
        onBackPressed();
    }

    /**
     * 插入数据
     */
    public void insertData(String content) {
        sd = helper.getWritableDatabase();
        sd.execSQL("insert into records(name) values('"+ content + "')");
        sd.close();
    }
    /**
     * 判断数据库中是否有该条记录
     */
    public boolean hasData(String content) {
        Cursor cursor = helper.getReadableDatabase().rawQuery("select id as _id,name from records where name =?",new String[]{content});
        return cursor.moveToNext();
    }
    /**
     * 清空数据
     */
    public void deleteData() {
        sd = helper.getWritableDatabase();
        sd.execSQL("delete from records");
        sd.close();
    }

//    /**
//     * 为list中的数据去重
//     * @param list
//     * @return
//     */
//    public List<String> removeDuplicate(List<String> list) {
//        HashSet<String> h = new HashSet<>(list);
//        list.clear();
//        list.addAll(h);
//        return list;
//    }
    /**
     * 将sqlite中的数据通过list展示出来
     */
    public List<String> getAllNumbers() {
        historySearch.clear();
        loopHistory();
        Log.i("666", "getAllNumbers: 每调用一次先清空" + historySearch.size());
        sd = helper.getReadableDatabase();
        if (sd.isOpen()) {
            Log.i("666", "getAllNumbers: 调到了");
            Cursor cursor = sd.rawQuery("SELECT * FROM  RECORDS",null);
            while (cursor.moveToNext()) {
                String number_all = cursor.getString(1);
                Log.i("888","--------->"+editText);
                historySearch.add(number_all);

                Log.i("666", "getAllNumbers: 调到了，historySearch........"+ historySearch);
            }
            cursor.close();
            sd.close();
        }
        return historySearch;
    }
    public void loopHistory() {
        LayoutInflater mInflater = LayoutInflater.from(SearchActivity.this);

        for (int i = 0; i < historySearch.size(); i++)
        {
            final TextView tv = (TextView) mInflater.inflate(R.layout.every_text, mFlowLayout, false);
            tv.setText(historySearch.get(i));
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editText.setText(tv.getText());
                }
            });
            if (tv != null) {
                mFlowLayout.removeView((TextView) tv.getParent());
                mFlowLayout.addView(tv);
            }

        }
    }
}
