package com.leowong.demo.multistatelayout;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.leowong.library.multistatelayout.MultiStateLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    @Bind(R.id.content_button)
    Button contentButton;
    @Bind(R.id.content_progress)
    Button contentProgress;
    @Bind(R.id.content_empty)
    Button contentEmpty;
    @Bind(R.id.content_error)
    Button contentError;
    @Bind(R.id.multistate)
    MultiStateLayout multiStateLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        textView = (TextView) findViewById(R.id.content);
        textView.setText("wanglg");
        multiStateLayout = (MultiStateLayout) findViewById(R.id.multistate);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                multiStateLayout.switchState(MultiStateLayout.State.STATE_CONTENT);
            }
        }, 3000);

        contentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                multiStateLayout.switchState(MultiStateLayout.State.STATE_CONTENT);
            }
        });
        contentProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                multiStateLayout.switchState(MultiStateLayout.State.STATE_PROGRESS);
            }
        });
        contentEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                multiStateLayout.switchState(MultiStateLayout.State.STATE_EMPTY);
            }
        });
        contentError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                multiStateLayout.switchState(MultiStateLayout.State.STATE_ERROR);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
