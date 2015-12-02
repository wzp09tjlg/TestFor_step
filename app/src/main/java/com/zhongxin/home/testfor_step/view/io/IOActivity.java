package com.zhongxin.home.testfor_step.view.io;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.zhongxin.home.testfor_step.R;
import com.zhongxin.home.testfor_step.utils.ThreadPool;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by Walter on 2015/10/23.
 */
public class IOActivity  extends Activity  implements
        View.OnClickListener
    //有两点需要注意的是 一:无论是文件的读还是取 都应该在最后的时候关闭流
    // 二:文件的读取是很耗时间的，所以一般都是需要单开子线程 ，让耗时的操作在子线程中运行
{
    private final String TAG = "IOActivity";
    private final String URL_SOURCE = "/storage/sdcard0/source";
    private final String URL_DESTION = "/storage/sdcard0/destion";
    private final String FILE_VIDEO = "love.flv";
    private final String FILE_TEXT  = "source.txt";
    private final String URL = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
    //widget
    private Button mButtonFileInputStream;     //FileInputStream
    private Button mButtonFileOutputStream;    //FileOutputStream
    private Button mButtonBufferedInputStream; //BufferedInputStream
    private Button mButtonBufferedOutputStream;//BufferedOutputStream
    private Button mButtonDataInputStream;     //DataInputStream
    private Button mButtonDataOutputStream;    //DataOutputStream
    private Button mButtonInputStreamReader;   //InputStreamReader
    private Button mButtonOutputStreamWriter;  //OutputStreamWriter
    private Button mButtonBufferedReader;      //BufferedReader
    private Button mButtonBufferedWriter;      //BufferedWriter
    //data
    //interface

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_io);
        findView();
        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.step_btn_fileInputStream:
                doVideoFileStream();
                break;
            case R.id.step_btn_fileOutputStream:
                doTextFileStream();
                break;
            case R.id.step_btn_bufferedInputStream:
                doVideoBufferedStream();
                break;
            case R.id.step_btn_bufferedOutputStream:
                doTextBufferedStream();
                break;
            case R.id.step_btn_dataInputStream:
                doVideoDataStream();
                break;
            case R.id.step_btn_dataOutputStream:
                doTextDataStream();
                break;
            case R.id.step_btn_inputStreamReader:
                doVideoStream();
                break;
            case R.id.step_btn_outputStreamWriter:
                doTextStream();
                break;
            case R.id.step_btn_bufferedReader:
                doVideoBuffered();
                break;
            case R.id.step_btn_bufferedWriter:
                doTextBuffered();
                break;
        }
    }

    private void findView(){
        mButtonFileInputStream = (Button)this.findViewById(R.id.step_btn_fileInputStream);
        mButtonFileOutputStream = (Button)this.findViewById(R.id.step_btn_fileOutputStream);
        mButtonBufferedInputStream = (Button)this.findViewById(R.id.step_btn_bufferedInputStream);
        mButtonBufferedOutputStream = (Button)this.findViewById(R.id.step_btn_bufferedOutputStream);
        mButtonDataInputStream = (Button)this.findViewById(R.id.step_btn_dataInputStream);
        mButtonDataOutputStream = (Button)this.findViewById(R.id.step_btn_dataOutputStream);
        mButtonInputStreamReader = (Button)this.findViewById(R.id.step_btn_inputStreamReader);
        mButtonOutputStreamWriter = (Button)this.findViewById(R.id.step_btn_outputStreamWriter);
        mButtonBufferedReader = (Button)this.findViewById(R.id.step_btn_bufferedReader);
        mButtonBufferedWriter = (Button)this.findViewById(R.id.step_btn_bufferedWriter);
    }

    private void initView(){
        mButtonFileInputStream.setOnClickListener(this);
        mButtonFileOutputStream.setOnClickListener(this);
        mButtonBufferedInputStream.setOnClickListener(this);
        mButtonBufferedOutputStream.setOnClickListener(this);
        mButtonDataInputStream.setOnClickListener(this);
        mButtonDataOutputStream.setOnClickListener(this);
        mButtonInputStreamReader.setOnClickListener(this);
        mButtonOutputStreamWriter.setOnClickListener(this);
        mButtonBufferedReader.setOnClickListener(this);
        mButtonBufferedWriter.setOnClickListener(this);
    }

    private void doVideoFileStream(){
        try{
            Log.e(TAG,"1");
            int temp = -1;
            Log.e(TAG,"2");
            byte[] buffer = new byte[100];
            Log.e(TAG,"3");
            FileInputStream fis = new FileInputStream("" + URL_SOURCE + "/" +FILE_VIDEO);
            Log.e(TAG,"4");
//            File file = new File(URL + "/destion/FileInputStream/love.flv");
//            if(file.exists())
//            {
//                Log.e(TAG," FILE IS EXIST! ");
//                return;
//            }
            FileOutputStream fos = new FileOutputStream(URL + "/destion/FileInputStream/love.flv");
            Log.e(TAG,"6");
            while((temp=fis.read(buffer))!= -1){ //因为这里是循环，所并没有报什么错，但是在UI上进行点击的时候就会没有反应。这时出现的anr
                fos.write(buffer,0,temp);
                buffer = new byte[100];
                Log.e(TAG,"7");
            }
            Log.e(TAG,"8");
            //在文件的读取过程中 无论是读还是取 都应该在最后的时候关闭流，不然就会造成内存的泄露问题
            fos.close();
            fis.close();
        }catch (Exception e){
            e.printStackTrace();
            Log.e(TAG,"9");
        }
    }

    private void doTextFileStream(){
        try{
            Log.e(TAG,"11");
            int len = -1;
            Log.e(TAG,"12");
            byte[] buffer = new byte[100];
            Log.e(TAG,"13");
            FileInputStream fis = new FileInputStream("" + URL_SOURCE + "/" + FILE_TEXT);
            Log.e(TAG,"14");
            File file = new File(URL + "/destion/FileOutputStream/source.txt");
            if(file.exists())
            {
                Log.e(TAG," FILE IS EXIST! ");
                return;
            }
            FileOutputStream fos= new FileOutputStream(URL + "/destion/FileOutputStream/source.txt");
            Log.e(TAG,"16");
            while((len=fis.read(buffer))!=-1){
                fos.write(buffer,0,len);
                buffer = new byte[100];
                Log.e(TAG,"17");
            }
            Log.e(TAG,"18");
            fos.close();  //一定要记住类似Stream 无论是input 还是 output 都应该要要关闭，不然就会存在内存泄露问题
            fis.close();  // 一定要记住类似Stream 的无论是input 还是output 都应该要关闭，不然就会出现存在内存泄露问题
        }catch (Exception e){
            Log.e(TAG,"19");
        }
    }

    private void doVideoBufferedStream(){
        ThreadPool.execute(new DoVideoBufferedRunnable());
    }

    private void doTextBufferedStream(){
        ThreadPool.execute( new DoTextBufferedStreamRunnable());
    }

    private void doVideoDataStream(){
        ThreadPool.execute(new DoVideoDataStreamRunnable());
    }

    private void doTextDataStream(){
        ThreadPool.execute(new DoTextDataStreamRunnable());
    }

    private void doVideoStream(){
        ThreadPool.execute(new DoVideoStreamRunnable());
    }

    private void doTextStream(){
        ThreadPool.execute(new DoTextStreamRunnable());
    }

    private void doVideoBuffered(){
        ThreadPool.execute(new DoVideoBufferedReaderWriterRunnable());
    }

    private void doTextBuffered(){
        ThreadPool.execute(new DoTextBufferedReaderWriterRunnable());
    }

    class DoVideoBufferedRunnable implements Runnable{ //使用线程池 进行处理文件的读取
        @Override
        public void run() {
            int len = -1;
            byte[] buffer = new byte[100];
            InputStream is = null;
            OutputStream os = null;
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try{
               is =  new FileInputStream(URL +"/love.flv");
               bis = new BufferedInputStream(is);
                File file = new File(URL+"/destion/BufferedInputStream");
                if(!file.exists()){
                    Log.e(TAG,"无效路径!");
                    return;
                }
                os = new FileOutputStream(URL + "/destion/BufferedInputStream/love.flv");
                bos = new BufferedOutputStream(os);
                while((len=bis.read(buffer))!=-1){
                    bos.write(buffer,0,len);
                }
          }catch (Exception e){
              e.printStackTrace();
          }finally {  //对文件访问和读写时 一定记得要在finally中对文件流进行关闭 不然就会造成内存泄露问题
                try{
                    if(is!=null)
                    {    is.close();
                        Log.e(TAG,"A1");
                    }
                }catch (Exception e){
                }
                try{
                    if(os!=null){
                        os.close();Log.e(TAG,"A2");
                    }
                }
                catch (Exception e){}
                try{
                    if(bis!=null){
                        bis.close();Log.e(TAG,"A3");
                    }
                }catch (Exception e){}
                try{
                    if(bos!=null){
                        bos.close();
                        Log.e(TAG,"A4");
                    }
                }catch (Exception e){}
            }
        }
    }

    class DoTextBufferedStreamRunnable implements Runnable{
        @Override
        public void run() {
            int len = -1;
            byte[] buffer = new byte[100];
            InputStream is = null;
            OutputStream os = null;
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try{
                is = new FileInputStream(URL + "/source.txt");
                bis = new BufferedInputStream(is);
                File file = new File(URL + "/destion/BufferedOutputStream");
                if(!file.exists()){
                    Log.e(TAG,"dir is not exist!");
                    return ;
                }
                os = new FileOutputStream(URL + "/destion/BufferedOutputStream/source.txt");
                bos = new BufferedOutputStream(os);
                while((len=bis.read(buffer))!=-1){
                    bos.write(buffer,0,len);
                }
            }catch (Exception e){}
            finally {
                try{
                    if(is!=null){
                        is.close();
                        Log.e(TAG,"B1");
                    }
                }catch (Exception e){}
                try{
                    if(bis!=null){
                        bis.close();
                        Log.e(TAG,"B2");
                    }
                }catch (Exception e){}
                try{
                    if(os!=null){
                        os.close();
                        Log.e(TAG,"B3");
                    }
                }catch (Exception e){}
                try{
                    if(bos!=null){
                        bos.close();
                        Log.e(TAG,"B4");
                    }
                }catch (Exception e){}
            }
        }
    }

    class DoVideoDataStreamRunnable implements Runnable{
        @Override
        public void run() {
            int len = -1;
            byte[] buffer = new byte[100];
            InputStream is = null;
            DataInputStream dis = null;
            OutputStream os = null;
            DataOutputStream dos = null;
            try{
                is = new FileInputStream(URL +"/love.flv");
                dis = new DataInputStream(is);
                File file = new File(URL + "/destion/DataInputStream");
                if(!file.exists()){
                    Log.e(TAG,"File is not Exist!");
                    return;
                }
                os = new FileOutputStream(URL + "/destion/DataInputStream/love.flv");
                dos = new DataOutputStream(os);
                while((len=dis.read(buffer))!=-1){
                    dos.write(buffer,0,len);
                }
            }catch (Exception e){}
            finally{
                try{
                    if(is!=null){
                        is.close();
                        Log.e(TAG,"C1");
                    }
                }catch (Exception e){}
                try{
                    if(dis!=null)
                    {
                        dis.close();
                        Log.e(TAG,"C2");
                    }
                }catch (Exception e){}
                try{
                     if(os!=null){
                         os.close();
                         Log.e(TAG,"C3");
                     }
                }catch (Exception e){}
                try{
                    if(dos!=null){
                        dos.close();
                        Log.e(TAG,"C4");
                    }
                }catch (Exception e){}
            }
        }
    }

    class DoTextDataStreamRunnable implements Runnable{
        @Override
        public void run() {
            int len =-1;
            byte[] buffer = new byte[100];
            InputStream is = null;
            DataInputStream dis = null;
            OutputStream os = null;
            DataOutputStream dos = null;
            try{
                is = new FileInputStream(URL + "/source.txt");
                dis = new DataInputStream(is);
                File file = new File(URL + "/destion/DataOutputStream");
                if(!file.exists()){
                    Log.e(TAG,"File is not exist!");
                    return;
                }
                os = new FileOutputStream(URL +"/destion/DataOutputStream/source.txt");
                dos = new DataOutputStream(os);
                while((len=dis.read(buffer))!=-1){
                    dos.write(buffer,0,len);
                }
            }catch (Exception e){}
            finally{
                try{
                    if(is!=null)
                    {
                        is.close();
                        Log.e(TAG,"D1");
                    }
                }catch (Exception e){}
                try{
                    if(dis!=null){
                        dis.close();
                        Log.e(TAG,"D2");
                    }
                }catch (Exception e){}
                try{
                    if(os!=null){
                        os.close();
                        Log.e(TAG,"D3");
                    }
                }catch (Exception e){}
                try{
                    if(dos!=null)
                    {
                        dos.close();
                        Log.e(TAG,"D4");
                    }
                }catch (Exception e){}
            }

        }
    }

    class DoVideoStreamRunnable implements Runnable{
        @Override
        public void run() {
            int len = -1;
            char[] buffer = new char[100];
            InputStream is = null;
            InputStreamReader isReader = null;
            OutputStream os = null;
            OutputStreamWriter osWriter = null;
            try{
                is = new FileInputStream(URL + "/love.flv");
                isReader = new InputStreamReader(is);
                File file = new File(URL + "/destion/InputStreamReader");
                if(!file.exists()){
                    Log.e(TAG,"File is not exist!");
                    return ;
                }
                os = new FileOutputStream(URL + "/destion/InputStreamReader/love.flv");
                osWriter = new OutputStreamWriter(os);
                while((len=isReader.read())!=-1){
                  osWriter.write(buffer,0,len);
                }
            }catch (Exception e){}
            finally{
                try{
                    if(is!=null){
                        is.close();
                        Log.e(TAG,"E1");
                    }
                }catch (Exception e){}
                try{
                    if(isReader!=null){
                        isReader.close();
                        Log.e(TAG,"E2");
                    }
                }catch (Exception e){}
                try{
                    if(os!=null){
                        os.close();
                        Log.e(TAG,"E3");
                    }
                }catch (Exception e){}
                try{
                    if(osWriter!=null){
                        osWriter.close();
                        Log.e(TAG,"E4");
                    }
                }catch (Exception e){}
            }
        }
    }

    class DoTextStreamRunnable implements Runnable{
        @Override
        public void run() {
            int len =-1;
            char[] buffer = new char[100];
            InputStream is = null;
            InputStreamReader isReader = null;
            OutputStream os = null;
            OutputStreamWriter osWriter = null;
            try{
                is = new FileInputStream(URL + "/source.txt");
                isReader = new InputStreamReader(is);
                File file = new File(URL + "/destion/OutputStreamWriter");
                if(!file.exists()){
                    Log.e(TAG,"FILE IS NOT EXIST!");
                    return ;
                }
                os = new FileOutputStream(URL + "/destion/OutputStreamWriter/source.txt");
                osWriter = new OutputStreamWriter(os);
                while((len = isReader.read(buffer,0,buffer.length))!=-1){  //这种方式读取不成功 ，不知道为啥
                    osWriter.write(buffer,0,len);
                }
            }catch (Exception e){}
            finally {
                try{
                    if(is!=null){
                        is.close();
                        Log.e(TAG,"F1");
                    }
                }catch (Exception e){}
                try{
                    if(isReader!=null){
                        isReader.close();
                        Log.e(TAG,"F2");
                    }
                }catch (Exception e){}
                try{
                    if(os!=null){
                        os.close();
                        Log.e(TAG,"F3");
                    }
                }catch (Exception e){}
                try{
                    if(osWriter!=null){
                        osWriter.close();
                        Log.e(TAG,"F4");
                    }
                }catch (Exception e){}
            }
        }
    }

   class DoVideoBufferedReaderWriterRunnable implements Runnable{
       @Override
       public void run() {
         int len  = -1;
         char[] buffer = new char[100];
         InputStream is = null;
         InputStreamReader isReader = null;
         BufferedReader  bReader = null;
         OutputStream os = null;
         OutputStreamWriter osWriter = null;
         BufferedWriter bWriter =  null;
         try{
           is = new FileInputStream(URL + "/love.flv");
           isReader = new InputStreamReader(is);
           bReader = new BufferedReader(isReader);
           File file = new File(URL + "/destion/BufferedReader");
             if(!file.exists()){
                 Log.e(TAG,"FILE IS NOT EXIST!");
                 return ;
             }
           os = new FileOutputStream(URL + "/destion/BufferedReader/love.flv");
           osWriter = new OutputStreamWriter(os);
           bWriter = new BufferedWriter(osWriter);
           while((len=bReader.read(buffer))!=-1){
               bWriter.write(buffer,0,len);
           }
         }catch (Exception e){}
           finally{
             try{
                 if(is!=null){
                     is.close();
                     Log.e(TAG,"G1");
                 }
             }catch (Exception e){}
             try{
                if(isReader!=null)
                {
                 isReader.close();
                    Log.e(TAG,"G2");
                }
             }catch (Exception e){}
             try{
                 if(bReader!=null){
                     bReader.close();
                     Log.e(TAG,"G3");
                 }
             }catch (Exception e){}

             try{
                 if(bWriter!=null){
                     bWriter.close();
                     Log.e(TAG,"G6");
                 }
             }catch (Exception e){}


             try{
                 if(osWriter!=null){
                     osWriter.close();
                     Log.e(TAG,"G5");
                 }
             }catch (Exception e){}

             try{
                 if(os!=null){
                     os.close();
                     Log.e(TAG,"G4");
                 }
             }catch (Exception e){}
         }
       }
   }

    class DoTextBufferedReaderWriterRunnable implements Runnable{
        @Override
        public void run() {
            int len = -1;
            char[] buffer = new char[100];
            InputStream is = null;
            InputStreamReader isReader = null;
            BufferedReader bReader = null;
            OutputStream os = null;
            OutputStreamWriter osWriter = null;
            BufferedWriter bWriter = null;
            try{
                File tempFile  =  new File(URL + "/source.txt");
                if(!tempFile.exists())
                {
                    Log.e(TAG,"fILE IS NOT EXIST!");
                    return ;
                }
            is = new FileInputStream(URL + "/source.txt");
            isReader = new InputStreamReader(is);
            bReader = new BufferedReader(isReader);
            File file = new File(URL + "/destion/BufferedWriter");
            if(!file.exists()){
                Log.e(TAG,"File is not Exist!");
                return ;
            }
            os = new FileOutputStream(URL + "/destion/BufferedWriter/source.txt");
            osWriter = new OutputStreamWriter(os);
                bWriter = new BufferedWriter(osWriter);
            while((len=bReader.read(buffer))!=-1){
                bWriter.write(buffer,0,len);
            }
            }catch (Exception e){}
            finally{
                try{
                    if(is!=null){
                        is.close();
                        Log.e(TAG,"H1");
                    }
                }catch (Exception e){}
                try{
                    if(isReader!=null){
                        isReader.close();
                        Log.e(TAG,"H21");
                    }
                }catch (Exception e){}
                try{
                    if(bReader!=null){
                        bReader.close();
                        Log.e(TAG,"H3");
                    }
                }catch (Exception e){}

                try{
                    if(bWriter!=null){
                        bWriter.close();
                        Log.e(TAG,"H6");
                    }
                }catch (Exception e){}

                try{
                    if(osWriter!=null){
                        osWriter.close();
                        Log.e(TAG,"H5");
                    }
                }catch (Exception e){}

                try{
                    if(os!=null){
                        os.close();
                        Log.e(TAG,"H4");
                    }
                }catch (Exception e){}
            }
        }
    }
}
