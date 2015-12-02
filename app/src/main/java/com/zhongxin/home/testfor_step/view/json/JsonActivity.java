package com.zhongxin.home.testfor_step.view.json;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.zhongxin.home.testfor_step.R;
import com.zhongxin.home.testfor_step.bean.AddressBean;
import com.zhongxin.home.testfor_step.bean.StudentBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Walter on 2015/10/24.
 */
public class JsonActivity extends Activity  implements
        View.OnClickListener
{
    private final String TAG = "JsonActivity";
    //widget
    private Button mButtonCJsonObject;
    private Button mButtonDJsonObject;
    private Button mButtonCJsonStringer;
    private Button mButtonDJsonStringer;
    //data
    private StudentBean studentBean;
    private String[] nums;
    private AddressBean addressBean;
    //interface
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_json);
        findView();
        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.step_btn_contruct_jsonObject:
                String json =
                getCJsonObject();
                Log.e(TAG,"json; " +json);
                break;
            case R.id.step_btn_destruct_jsonObject:
                getFromAssets();
                String json1 =
                        getCJsonObject();
                Log.e(TAG,"json; " +json1);
                String bean =
                getDJsonObject(json1);
                Log.e(TAG,"BEAN: " + bean);
                break;
            case R.id.step_btn_construct_jsonStringer:
                String json2 =  doCJsonStringer();
                Log.e(TAG,"JSON2:" + json2);
                break;
            case R.id.step_btn_desttruct_jsonStringer:
                String json3 = doCJsonStringer();
                Log.e(TAG,"JSON3:" + json3);
                String json4 = getDJsonStringer(json3);
                Log.e(TAG,"json4;" + json4);
                break;
        }
    }

    private void findView(){
        mButtonCJsonObject = (Button)this.findViewById(R.id.step_btn_contruct_jsonObject);
        mButtonDJsonObject = (Button)this.findViewById(R.id.step_btn_destruct_jsonObject);
        mButtonCJsonStringer = (Button)this.findViewById(R.id.step_btn_construct_jsonStringer);
        mButtonDJsonStringer = (Button)this.findViewById(R.id.step_btn_desttruct_jsonStringer);
    }

    private void initView(){
        mButtonCJsonObject.setOnClickListener(this);
        mButtonDJsonObject.setOnClickListener(this);
        mButtonDJsonStringer.setOnClickListener(this);
        mButtonCJsonStringer.setOnClickListener(this);
        getData();
    }

    private void getData(){
        studentBean = new StudentBean();
        studentBean.setName("liyun123");
        studentBean.setAge(25);
        studentBean.setMarried(false);
        nums = new String[2];
        nums[0] = "1234567890";
        nums[1] = "0987654321";
        studentBean.setNums(nums);
        addressBean = new AddressBean();
        addressBean.setProvince("Tianjin");
        addressBean.setCountry("Linghe");
        addressBean.setTown("Zhongxin");
        studentBean.setAddressBean(addressBean);
    }

    //使用JsonObject 的方式构建json
    private String getCJsonObject(){   //构建json
        StringBuilder stringBuilder = new StringBuilder();
        if(studentBean==null)
            return "{}";
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("name",studentBean.getName());
            jsonObject.put("age",studentBean.getAge());
            jsonObject.put("married",studentBean.isMarried());
            JSONArray jsonArray = new JSONArray();
            for(int i=0;i<studentBean.getNums().length;i++){
                jsonArray.put(studentBean.getNums()[i]);
            }
            jsonObject.put("nums",jsonArray);
            JSONObject objTemp = new JSONObject();
            objTemp.put("province",studentBean.getAddressBean().getProvince());
            objTemp.put("country",studentBean.getAddressBean().getCountry());
            objTemp.put("town",studentBean.getAddressBean().getTown());
            jsonObject.put("addressBean",objTemp);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    //使用JsonObject 的方式解析json
    private String getDJsonObject(String json){
        if(json.equals(""))
        return null;
        Log.e(TAG,"JSON:" + json);
        StudentBean studentBean = new StudentBean();
        JSONTokener jsonTokener = new JSONTokener(json);
        JSONObject jsonObject = null;
        try{
            jsonObject = (JSONObject)jsonTokener.nextValue();
            studentBean.setName(jsonObject.optString("name","defaultName"));
            studentBean.setAge(jsonObject.optInt("age",-1));
            studentBean.setMarried(jsonObject.optBoolean("married",false));
            JSONObject objTemp = jsonObject.getJSONObject("addressBean");
            AddressBean bean = new AddressBean();
            bean.setProvince(objTemp.optString("province","defaultProvince"));
            bean.setCountry(objTemp.optString("country", "defaultCountry"));
            bean.setTown(objTemp.optString("town", "defaultTown"));
            studentBean.setAddressBean(bean);
            JSONArray arrTemp = jsonObject.getJSONArray("nums");  // 如果这里不进行创建对象，就会报错，因为nums也是没有被创建，没有分配存储空间
            String[] nums = new String[arrTemp.length()];
            for(int i=0;i<arrTemp.length();i++){
                nums[i] = (String)arrTemp.opt(i);  //因为这里是基本数据类型，所以不用创建JSONObject 来处理数据，直接使用JsonArray.opt(index)就行
            }
            studentBean.setNums(nums);
        }catch (JSONException e){
          e.printStackTrace();
        }
        return studentBean.toString();
    }

    //使用jsonStringer 构建json
    private String doCJsonStringer(){
       if(studentBean==null)
           return "{}";
        JSONStringer jsonStringer = new JSONStringer();
        //json 存放在对象中
        try{
            jsonStringer.object();

            jsonStringer.key("name");
            jsonStringer.value(studentBean.getName());
            jsonStringer.key("age");
            jsonStringer.value(studentBean.getAge());
            jsonStringer.key("married");
            jsonStringer.value(studentBean.isMarried());
            //json 数据中含有数组，所以这里开启数组
            jsonStringer.key("nums");
            jsonStringer.array();
            for(int i=0;i<studentBean.getNums().length;i++){
                jsonStringer.value(studentBean.getNums()[i]);
            }
            jsonStringer.endArray();

            //对象中包含对象，需要再开启一个对象
            jsonStringer.key("addressBean");
            jsonStringer.object();
            jsonStringer.key("province");
            jsonStringer.value(studentBean.getAddressBean().getProvince());
            jsonStringer.key("country");
            jsonStringer.value(studentBean.getAddressBean().getCountry());
            jsonStringer.key("town");
            jsonStringer.value(studentBean.getAddressBean().getTown());
            jsonStringer.endObject();

            jsonStringer.endObject();
        }catch (JSONException e){}
        return jsonStringer.toString();
    }

    //使用jsonObject 的方法解析json
    private String getDJsonStringer(String json){
        if(json.equals(""))
            return "";
         JSONTokener jsonTokener = new JSONTokener(json);
        StudentBean bean = new StudentBean();
        try{
            JSONObject jsonObject = (JSONObject)jsonTokener.nextValue();
            bean.setName(jsonObject.optString("name","defaultName"));
            bean.setAge(jsonObject.optInt("age",-1));
            bean.setMarried(jsonObject.optBoolean("married",false));
            JSONArray array = jsonObject.getJSONArray("nums");
            String[] nums = new String[array.length()];
            for(int i=0;i<nums.length;i++){
                nums[i] = (String)array.opt(i);
            }
            bean.setNums(nums);
            JSONObject objTemp = jsonObject.getJSONObject("addressBean");
            AddressBean addressBean1 = new AddressBean();
            addressBean1.setProvince(objTemp.optString("province","defaultProvince"));
            addressBean1.setCountry(objTemp.optString("county","defaultCountry"));
            addressBean1.setTown(objTemp.optString("town","townDefault"));
            bean.setAddressBean(addressBean1);
        }catch (JSONException e){}

        return bean.toString();
    }

    private String getFromAssets(){
        InputStream is = null;
        Properties properties = new Properties();
        try{
            is = getAssets().open("student.json");
            properties.load(is);

        }catch (Exception e){}
        Log.e(TAG,"properties.toString():" + properties.toString());
        return properties.toString();
    }

}
