package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {
    //所有的按钮，包括0-9，运算按钮，清除按钮等
    Button btn_0, btn_1, btn_2, btn_3, btn_4, btn_5,
            btn_6, btn_7, btn_8, btn_9, btn_den,btn_jie,
            btn_point, btn_add, btn_drc, btn_mul,btn_cos,
            btn_div, btn_ac, btn_xin, btn_zc,btn_gen,btn_sin;
    //输入、输出结果展示框
    EditText main_figure;

    boolean clear_flag;//是否全部清除展示区的内容

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_figure);//加载set_figure中的布局
        //0-9的数字按钮，获得布局文件中定义的元素
        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);
        //对数字操作的按钮
        btn_gen= (Button) findViewById(R.id.btn_gen);
        btn_sin = (Button) findViewById(R.id.btn_sin);
        btn_jie = (Button) findViewById(R.id.btn_jie);
        btn_cos= (Button) findViewById(R.id.btn_cos);
        btn_ac = (Button) findViewById(R.id.btn_ac);
        btn_den = (Button) findViewById(R.id.btn_den);
        btn_mul = (Button) findViewById(R.id.btn_mul);
        btn_div = (Button) findViewById(R.id.btn_div);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_point = (Button) findViewById(R.id.btn_point);
        btn_drc = (Button) findViewById(R.id.btn_drc);
        btn_zc = (Button) findViewById(R.id.btn_zc);
        btn_xin = (Button) findViewById(R.id.btn_xin);
        //结果展示区
        main_figure = (EditText) findViewById(R.id.main_figure);
        //为xml文件中定义的所有元素注册监听器，点击时执行click方法
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
        btn_den.setOnClickListener(this);
        btn_mul.setOnClickListener(this);
        btn_div.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_drc.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_zc.setOnClickListener(this);
        btn_xin.setOnClickListener(this);
        btn_ac.setOnClickListener(this);
        btn_gen.setOnClickListener(this);
        btn_sin.setOnClickListener(this);
        btn_jie.setOnClickListener(this);
        btn_cos.setOnClickListener(this);
        main_figure.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String str = main_figure.getText().toString();
        switch (v.getId()) {
            case R.id.btn_xin:
                if (clear_flag) {
                    clear_flag = false;
                    str = "";
                    main_figure.setText("");
                }
                main_figure.setText(str + ((Button) v).getText().charAt(2));
                break;
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_gen:
            case R.id.btn_sin:
            case R.id.btn_cos:
            case R.id.btn_point:
                if (clear_flag) {
                    clear_flag = false;
                    str = "";
                    main_figure.setText("");
                }
                main_figure.setText(str + ((Button) v).getText());
                break;
            case R.id.btn_jie:
            case R.id.btn_add:
            case R.id.btn_drc:
            case R.id.btn_mul:
            case R.id.btn_div:
            case R.id.btn_zc:
                if (clear_flag) {
                    clear_flag = false;
                    str = "";
                    main_figure.setText("");
                }
                main_figure.setText(str + " " + ((Button) v).getText() + " ");
                break;

            case R.id.btn_ac:
                clear_flag = false;
                main_figure.setText("");
                break;
            case R.id.btn_den:
                getResult();
                break;
            default:
                break;
        }
    }
   //获得计算结果的方法
   double result = 0;
    private void getResult() {
        String exp = main_figure.getText().toString();//从editview获得的字符串
        if(exp==null||exp.equals("")){
            return;
        }
        //当从ediview获得的字符没有“ ”时
        if(!exp.contains(" ")){
            if(exp.contains("√")){
                String str1=exp.replace("√","");
                double d = Math.sqrt(Double.parseDouble(str1));
                if(d>=0) {
                    exp= String.valueOf(d);

                }
                else{
                    Toast.makeText(FirstActivity.this,"开根号的数不可以小于0", Toast.LENGTH_SHORT).show();
                }
                main_figure.setText(exp);
            }
            if(exp.contains("sin")){
                String str1=exp.replace("sin","");
                double a=Double.parseDouble(str1);
                double d = Math.sin((a*Math.PI)/180);
                exp=String.valueOf(d);
                main_figure.setText(exp);
            }
            if(exp.contains("!")) {
                String str1 = exp.replace("!", "");
                int a = Integer.parseInt(str1);
                double d = jiec(a);
                exp = String.valueOf(d);
                main_figure.setText(exp);
            }
            return;

        }
        if(clear_flag){
            clear_flag=false;
            return ;
        }
        clear_flag = true;
        //当存在“ ”时

        String s1 = exp.substring(0, exp.indexOf(" "));
        String op = exp.substring(exp.indexOf(" ") + 1, exp.indexOf(" ") + 2);
        String s2 = exp.substring(exp.indexOf(" ") + 3);
        //如果出现开个号等操作时就需要获得计算后的数字
        if(s1.contains("√")){
            String str1=s1.replace("√","");
            double d = Math.sqrt(Double.parseDouble(str1));
            if(d>=0) {
                s1 = String.valueOf(d);
            }
            else{
                Toast.makeText(FirstActivity.this,"开根号的数不可以小于0", Toast.LENGTH_SHORT).show();
            }

        }
        if(s2.contains("√")){
            String str1=s2.replace("√","");
            double d = Math.sqrt(Double.parseDouble(str1));
            if(d>=0) {
                s2= String.valueOf(d);
            }
            else{
                Toast.makeText(FirstActivity.this,"开根号的数不可以小于0", Toast.LENGTH_SHORT).show();
            }

        }
         if(s1.contains("sin")){
             String str1=s1.replace("sin","");
             double a=Double.parseDouble(str1);
             double d = Math.sin((a*Math.PI)/180);

             s1=String.valueOf(d);

         }
        if(s2.contains("sin")){
            String str1=s2.replace("sin","");
            double a=Double.parseDouble(str1);
            double d = Math.sin((a*Math.PI)/180);
            s2=String.valueOf(d);

        }
        if(s1.contains("!")){
            String str1=s1.replace("!","");
            double d = jiec(Integer.parseInt(str1));
            s1=String.valueOf(d);
        }
        if(s2.contains("!")){
            String str1=s2.replace("!","");
            double d = jiec(Integer.parseInt(str1));
            s2=String.valueOf(d);

        }
        //操作数都不为空时
        if (!s1.equals("") && !s2.equals("")) {

            double d1 = Double.parseDouble(s1);
            double d2 = Double.parseDouble(s2);

            if (op.equals("+")) {
               result=add(d1,d2);
            } else if (op.equals("-")) {
               result=sub(d1,d2);
            } else if (op.equals("*")) {
               result=mul(d1,d2);

            }
            else if (op.equals("^")) {
                result=Math.pow(d1,d2);

            }else if (op.equals("%")) {
                if (d2 == 0) {

                   Toast.makeText(FirstActivity.this,"除数不可以为0", Toast.LENGTH_SHORT).show();
                } else {
                  result=zc(d1,d2);
                }
            }
            else if (op.equals("/")) {
                if (d2 == 0) {
                    Toast.makeText(FirstActivity.this,"除数不可以为0", Toast.LENGTH_SHORT).show();
                } else {
                   result=div(d1,d2);
                }
            }
            if (!s1.contains(".") && !s2.contains(".") && !op.equals("/")) {
                int r = (int) result;
                main_figure.setText(r + "");
            } else {
                main_figure.setText(result + "");
            }
        }
        //当第一个操作数不空，第二个为空的时候
        else if((!s1.equals("")&&s2.equals(""))){
            main_figure.setText(s1);
        }
        //当第一个为空，第二个不为空
        else if(s1.equals("")&&!s2.equals("")){
            double d2=Double.parseDouble(s2);
            if(op.equals("+")){
                result=0+d2;
            }else if(op.equals("-")){
                result=0-d2;
            }else if(op.equals("*")){
                result=0;
            }else if(op.equals("/")){
                result = 0;
            }
            if(!s2.contains(".")){
                int r=(int) result;
                main_figure.setText(r+" ");
            } else{
                main_figure.setText(result+" ");
            }
        }else{
            main_figure.setText("");
        }

    }
    //基本的运算（加减乘除，整除以及阶乘）
    private double add(double a,double b){
        return a+b;
    }
    public double sub(double a,double b){
        return a-b;
    }
    public double mul(double a,double b){
        return a*b;
    }
    public double div(double a,double b){
        return a/b;
    }
    public double zc(double a,double b){
        return a%b;
    }
    public int  jiec(int a){
        int  r=1;
      for(int i=1;i<=a;i++) {
          r = r * i;
      }
      return r;
    }
}