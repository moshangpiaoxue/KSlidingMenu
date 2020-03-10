package com.mo.kslidingmenu;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mo.lib.SlidingMenu;

public class MainActivity extends AppCompatActivity {
    SlidingMenu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);   // 100dp
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.menu_left);
        TextView ddddddddddd = menu.findViewById(R.id.ddddddddddd);
        ddddddddddd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.toggle();
                Toast.makeText(MainActivity.this, "干啥！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_UP && event.getRepeatCount() == 0) {
                menu.toggle();
                return false;
            } else {
                return true;
            }
        }
        return super.onKeyUp(keyCode, event);
    }
    //     @Override
    //   public boolean onKeyUp(int keyCode, KeyEvent event) {
    //    // TODO Auto-generated method stub
    //    if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {  
    //       if (event.getAction() == KeyEvent.ACTION_UP && event.getRepeatCount() == 0) {
    //                //         showMenu();
    //                //         showContent();
    //        toggle();      
    //       } 
    //       return true;  
    //     }  
    //    return super.onKeyUp(keyCode, event);
    //   }
}
