package com.example.myapplication;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.lang.Math;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //创建Button对象   也就是activity_main.xml里所设置的ID
    Button btn_0,btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7,btn_8,btn_9,btn_pt;
    Button btn_mul,btn_div,btn_add,btn_sub,btn_sqrt;
    Button btn_clr,btn_opposite,btn_eq;
    EditText et_input;
    boolean clr_flag;    //判断et编辑文本框中是否清空

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //实例化对象
        setContentView(R.layout.activity_main);
        btn_0= (Button) findViewById(R.id.btn_0);
        btn_1= (Button) findViewById(R.id.btn_1);
        btn_2= (Button) findViewById(R.id.btn_2);
        btn_3= (Button) findViewById(R.id.btn_3);
        btn_4= (Button) findViewById(R.id.btn_4);
        btn_5= (Button) findViewById(R.id.btn_5);
        btn_6= (Button) findViewById(R.id.btn_6);
        btn_7= (Button) findViewById(R.id.btn_7);
        btn_8= (Button) findViewById(R.id.btn_8);
        btn_9= (Button) findViewById(R.id.btn_9);
        btn_pt= (Button) findViewById(R.id.btn_pt);
        btn_add= (Button) findViewById(R.id.btn_add);
        btn_sub= (Button) findViewById(R.id.btn_sub);
        btn_mul= (Button) findViewById(R.id.btn_mul);
        btn_div= (Button) findViewById(R.id.btn_div);
        btn_clr= (Button) findViewById(R.id.btn_clr);
        btn_opposite= (Button) findViewById(R.id.btn_opposite);
        btn_eq= (Button) findViewById(R.id.btn_eq);
        btn_sqrt= (Button) findViewById(R.id.btn_sqrt);
        et_input= (EditText) findViewById(R.id.et_input);

        //给按钮设置的点击事件
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_pt.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_sub.setOnClickListener(this);
        btn_mul.setOnClickListener(this);
        btn_div.setOnClickListener(this);
        btn_clr.setOnClickListener(this);
        btn_opposite.setOnClickListener(this);
        btn_eq.setOnClickListener(this);
        btn_sqrt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String str=et_input.getText().toString();
        if(v.getId()==R.id.btn_0|v.getId()==R.id.btn_1|v.getId()==R.id.btn_2|v.getId()==R.id.btn_3|v.getId()==R.id.btn_4|v.getId()==R.id.btn_5|v.getId()==R.id.btn_6|v.getId()==R.id.btn_7|v.getId()==R.id.btn_8|v.getId()==R.id.btn_9|v.getId()==R.id.btn_pt){
            if(clr_flag){
                clr_flag=false;
                str="";
                et_input.setText("");
            }
            et_input.setText(str+((Button)v).getText());
        } else if(v.getId()==R.id.btn_add|v.getId()==R.id.btn_sub|v.getId()==R.id.btn_mul|v.getId()==R.id.btn_div|v.getId()==R.id.btn_sqrt){
            if(clr_flag){
                clr_flag=false;
                str="";
                et_input.setText("");
            }
            if(str.contains("+")||str.contains("-")||str.contains("×")||str.contains("÷")||str.contains("√")) {
                str=str.substring(0,str.indexOf(" "));
            }
            et_input.setText(str+" "+((Button)v).getText()+" ");
        }else if(v.getId()==R.id.btn_clr){
            if(clr_flag)
                clr_flag=false;
                str=" ";
                et_input.setText("");
        }else if(v.getId()==R.id.btn_opposite){
            if(clr_flag){
                clr_flag=false;
                str="";
                et_input.setText("");
            }
            else if(str!=null&&!str.equals("")){
                if(str.contains("-")){
                    str=str.substring(1,str.length());
                    et_input.setText(str);
                }else {
                    str="-"+str;
                    et_input.setText(str);
                }
            }
        }else if(v.getId()==R.id.btn_eq) {
            getResult();
        }
    }

    private void getResult() {
        String exp=et_input.getText().toString();
        if(exp==null||exp.equals("")) return ;
        //因为没有运算符所以不用运算
        if(!exp.contains(" ")){
            return ;
        }
        if(clr_flag){
            clr_flag=false;
            return;
        }
        clr_flag=true;
        //截取运算符前面的字符串
        String s1=exp.substring(0,exp.indexOf(" "));
        //截取的运算符
        String op=exp.substring(exp.indexOf(" ")+1,exp.indexOf(" ")+2);
        //截取运算符后面的字符串
        String s2=exp.substring(exp.indexOf(" ")+3);
        double cnt=0.0;
        if(!s1.equals("")&&!s2.equals("")){
            double d1=Double.parseDouble(s1);
            double d2=Double.parseDouble(s2);
            if(op.equals("+")){
                cnt=d1+d2;
            }
            if(op.equals("-")){
                cnt=d1-d2;
            }
            if(op.equals("×")){
                cnt=d1*d2;
            }
            if(op.equals("÷")){
                if(d2==0) {
                    Toast.makeText(this, "除数不能为0！", Toast.LENGTH_SHORT).show();
                }
                else cnt=d1/d2;
            }
            if(!s1.contains(".")&&!s2.contains(".")&&!op.equals("÷")) {
                int res = (int) cnt;
                et_input.setText(res+"");
            }else {
                et_input.setText(cnt+"");}
        }
        //如果s1是不空    s2是空  就执行下一步
        else if(!s1.equals("")&&s2.equals("")){
            double d1=Double.parseDouble(s1);
            if(op.equals("+")){
                cnt=d1;
            }
            if(op.equals("-")){
                cnt=d1;
            }
            if(op.equals("×")){
                cnt=0;
            }
            if(op.equals("÷")){
                cnt=0;
            }

            if(!s1.contains(".")) {
                int res = (int) cnt;
                et_input.setText(res+"");
            }else {
                et_input.setText(cnt+"");}
            if(op.equals("√")){
                cnt=Math.sqrt(d1);
                et_input.setText(cnt+"");
            }
        }
        //如果s1是空    s2不是空  就执行下一步
        else if(s1.equals("")&&!s2.equals("")){
            double d2=Double.parseDouble(s2);
            if(op.equals("+")){
                cnt=d2;
            }
            if(op.equals("-")){
                cnt=0-d2;
            }
            if(op.equals("×")){
                cnt=0;
            }
            if(op.equals("÷")){
                cnt=0;
            }
            if(!s2.contains(".")) {
                int res = (int) cnt;
                et_input.setText(res+"");
            }else {
                et_input.setText(cnt+"");}
        }
        else {
            et_input.setText("");
        }
    }
}

