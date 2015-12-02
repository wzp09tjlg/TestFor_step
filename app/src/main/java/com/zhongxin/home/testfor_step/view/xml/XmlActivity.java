package com.zhongxin.home.testfor_step.view.xml;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.zhongxin.home.testfor_step.R;
import com.zhongxin.home.testfor_step.bean.AnimalBean;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Walter on 2015/10/25.
 */
public class XmlActivity extends Activity implements
        View.OnClickListener
{
    private final String TAG = "XmlActivity";
    private final String URL = Environment.getExternalStorageDirectory().getAbsolutePath();
    //widget
    private Button mButtonSaxEncode;
    private Button mButtonSaxUncode;
    private Button mButtonPullEncode;
    private Button mButtonPullUncode;
    //data
    private ArrayList<AnimalBean> list;
    private AnimalBean bean;
    //interface
    class MySaxHandler extends DefaultHandler{
        private ArrayList<AnimalBean> list;
        private AnimalBean bean;
        private String content;

        public MySaxHandler(ArrayList<AnimalBean> list){
            this.list = list;
        }

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            if("animal".equals(localName)){
                bean = new AnimalBean();
                content = attributes.getValue("id");
                bean.setId(Integer.parseInt(content));
            }else if("name".equals(localName)){
                bean.setName(content);
            }else if("age".equals(localName)){
                bean.setAge( 9);
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            if("animal".equals(localName)){
              list.add(bean);
                bean = null;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            content = new String(ch,start,length);
            Log.e(TAG,"CONTENT:" + ch.toString());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_xml);
        findView();
        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.step_btn_SAX_encode:
                doSaxEncodeXml();
                break;
            case R.id.step_btn_SAX_unencode:
                doSaxUncodeXml();
                break;
            case R.id.step_btn_PULL_encode:
                doPullEncodeXml();
                break;
            case R.id.step_btn_PULL_uncode:
                doPullUncodeXml();
                for(int i=0;i<list.size();i++){
                    Log.e(TAG,"BEAN:" + list.get(i).toString());
                }
                break;
        }
    }

    private void findView(){
        mButtonSaxEncode = (Button)this.findViewById(R.id.step_btn_SAX_encode);
        mButtonSaxUncode = (Button)this.findViewById(R.id.step_btn_SAX_unencode);
        mButtonPullEncode= (Button)this.findViewById(R.id.step_btn_PULL_encode);
        mButtonPullUncode= (Button)this.findViewById(R.id.step_btn_PULL_uncode);
    }

    private void initView(){
        mButtonSaxEncode.setOnClickListener(this);
        mButtonSaxUncode.setOnClickListener(this);
        mButtonPullEncode.setOnClickListener(this);
        mButtonPullUncode.setOnClickListener(this);
        getData();
    }

    private void getData(){
        list = new ArrayList<>();
    }

    //使用serializer 构建xml数据
    private void doSaxEncodeXml(){
        File file = new File(URL);
        if(!file.exists()){
            Log.e(TAG,"File is not exist!");
            return ;
        }
        try{
            OutputStream os = new FileOutputStream(URL + "/animals.xml");
            XmlSerializer serializer = Xml.newSerializer();
            serializer.setOutput(os,"utf-8");
            serializer.startDocument(null,true);
            serializer.startTag(null,"animals");

              serializer.startTag(null,"animal");
              serializer.attribute(null,"id",Integer.toString(1));
                 serializer.startTag(null, "name");
                 serializer.text("cat");
                 serializer.endTag(null, "name");

                 serializer.startTag(null,"age");
                 serializer.text("10");
                 serializer.endTag(null,"age");
              serializer.endTag(null, "animal");

            serializer.startTag(null,"animal");
            serializer.attribute(null, "id", Integer.toString(2));
            serializer.startTag(null, "name");
            serializer.text("dog");
            serializer.endTag(null, "name");

            serializer.startTag(null,"age");
            serializer.text("8");
            serializer.endTag(null,"age");
            serializer.endTag(null,"animal");

            serializer.endTag(null, "animals");
            serializer.endDocument();
            os.flush();
            os.close();
          Log.e(TAG,"SERIALIZER: " + serializer.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //使用Sax解析数据
    private void doSaxUncodeXml(){
        try{
            InputStream is = new FileInputStream(URL + "/animals.xml");
            InputSource is1 = new InputSource(is);
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            XMLReader xReader = sp.getXMLReader();
            MySaxHandler mySaxHandler = new MySaxHandler(list);
            xReader.setContentHandler(mySaxHandler);
            xReader.parse(is1);
            is.close();
            for(int i=0;i<list.size();i++){
                Log.e(TAG,"BEAN:" + list.get(i).toString());
            }
        }catch (Exception e){
e.printStackTrace();
        }
    }

    private void doPullEncodeXml(){
        File file  = new File(URL + "/animal_animal.xml");
        try{
            if(!file.exists()){
                file.createNewFile();
            }
        }catch (Exception e){
          e.printStackTrace();
        }
        try{
            OutputStream os = new FileOutputStream(URL + "/animal_animal.xml");
            //构建xml
            XmlSerializer serializer = Xml.newSerializer();
            serializer.setOutput(os,"utf-8");
            serializer.startDocument("utf-8",true);
            serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output",true);
            serializer.startTag("", "animals"); /*根节点*/

            /*构建了两个节点*/
            serializer.startTag("","animal");
            serializer.attribute("", "id", "100");
            serializer.startTag("", "name");
            serializer.text("dogdog");
            serializer.endTag("", "name");
            serializer.startTag("", "age");
            serializer.text("1000");
            serializer.endTag("","age");
            serializer.endTag("","animal");

            serializer.startTag("","animal");
            serializer.attribute("","id","100");
            serializer.startTag("","name");
            serializer.text("catcat");
            serializer.endTag("","name");
            serializer.startTag("","age");
            serializer.text("2000");
            serializer.endTag("","age");
            serializer.endTag("","animal");

            serializer.endTag("","animals");
            serializer.endDocument();
            os.flush();
            os.close();
        }catch (Exception e){

        }
    }

    private void doPullUncodeXml(){
       try{
           File file = new File(URL + "/animal_animal.xml");
           if(!file.exists()){
               file.createNewFile();
           }
           InputStream is  = new FileInputStream(URL + "/animal_animal.xml");
           //两种方式创建 PullParser
           XmlPullParserFactory pullFactory = XmlPullParserFactory.newInstance();
           XmlPullParser xmlPullParser = pullFactory.newPullParser();
           XmlPullParser xmlPullParser1 = Xml.newPullParser();
           xmlPullParser.setInput(is,"utf-8");
           int eventType = xmlPullParser.getEventType();
           boolean flag = true; //标志位 当不要再循环时 就可以停止下来
           while(flag){
             switch (eventType){
                 case XmlPullParser.START_DOCUMENT: //开始解析
                     break;
                 case XmlPullParser.END_DOCUMENT://解析完毕
                     flag = false;
                     break;
                 case XmlPullParser.START_TAG://解析每个节点
                     String name = xmlPullParser.getName();
                     if("animal".equals(name))
                     {
                         if(bean==null)
                             bean = new AnimalBean();
                         int id =Integer.parseInt( xmlPullParser.getAttributeName(0));
                         bean.setId(id);
                     }else if("name".equals(name)){
                         bean.setName(xmlPullParser.nextText());
                     }else if("age".equals(name)){
                         bean.setAge(Integer.parseInt(xmlPullParser.nextText()));
                     }
                     break;
                 case XmlPullParser.END_TAG: //解析每个节点完毕
                     list.add(bean);
                     bean = new AnimalBean();
                     break;
             }
               eventType = xmlPullParser.next();//这里需要替换下一个eventType
           }
       }catch (Exception e){
           e.printStackTrace();
       }
    }

    private String doXmlStrInput(String value){
        if(value.equals(""))
            return "\'"+"\'";
        return "\'"+value+"\'";
    }

}
