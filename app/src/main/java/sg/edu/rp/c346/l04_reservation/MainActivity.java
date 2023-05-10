package sg.edu.rp.c346.l04_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.sql.Time;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
Button comfirm,reset;
ToggleButton  address;
EditText username,userphone,userpax;
DatePicker dp;
TimePicker tp;
RadioGroup smokearea;
RadioButton smoke,nonsmoke;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        comfirm=findViewById(R.id.comfirm1);
        reset=findViewById(R.id.reset1);
        address=findViewById(R.id.address);
        username=findViewById(R.id.name);
        userphone=findViewById(R.id.phones);
        userpax=findViewById(R.id.paxs);
        dp=findViewById(R.id.datepicker);
        tp=findViewById(R.id.timepicker);
        smokearea=findViewById(R.id.smokearea);
        smoke=findViewById(R.id.smoke);
        nonsmoke=findViewById(R.id.nonsmoke);



        int minute= tp.getMinute();
        int hour= tp.getHour();
        int day=dp.getDayOfMonth();
        int month=dp.getMonth();
        int year=dp.getYear();
        tp.setHour(19);
        tp.setMinute(30);
        dp.updateDate(2020,5,1);
        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
                if(!(hour>=8&&hour<=21)){
                    tp.setHour(20);
                    tp.setMinute(0);
                }
            }
        });



        comfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int minute= tp.getMinute();
                int hour= tp.getHour();
                int day=dp.getDayOfMonth();
                int month=dp.getMonth();
                int year=dp.getYear();
                String user_pax=userpax.getText().toString();
                String user_name=username.getText().toString();
                String user_phone=userphone.getText().toString();
                if((user_pax.isEmpty()||user_name.isEmpty()||user_phone.isEmpty())){
                    Toast.makeText(MainActivity.this,"Please fill in all the blank fields.",Toast.LENGTH_SHORT).show();
                }
                else{
                    boolean smokearea = smoke.isChecked();
                    boolean gender=address.isChecked();
                    String smokeoutput;
                    if (smokearea)    {
                        smokeoutput="yes";
                    }
                    else{
                 smokeoutput="no";
                    }
                    String genderoutput;
                    if(gender){
                     genderoutput="Ms. ";
                    }
                    else{
                    genderoutput="Mr. ";
                    }String output =String.format("%s%s, your reservation is successful\nPhone:%s\nPax: %s\nDate: %d/%d/%d  %d:%d\nSmoking area: %s",genderoutput,user_name,user_phone,user_pax,day,month,year,hour,minute,smokeoutput);

                    Toast.makeText(MainActivity.this, output, Toast.LENGTH_SHORT).show();

                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tp.setHour(19);
                tp.setMinute(30);
                dp.updateDate(2020,5,1);
                username.setText("");
                userpax.setText("");
                userphone.setText("");
                address.setChecked(false);
                nonsmoke.setChecked(true);

            }
        });



    }
}