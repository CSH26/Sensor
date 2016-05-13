package com.example.user.sensor;

import android.app.ListActivity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.List;

// 테스트 주석추가
public class MainActivity extends ListActivity {

    SensorManager manager = null;
    List<Sensor> sensorList = null;
    SensorListAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensorList = manager.getSensorList(Sensor.TYPE_ALL);

        adapter = new SensorListAdapter(this, R.layout.listitem, sensorList);
        setListAdapter(adapter);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Sensor sensor = sensorList.get(position);
        String sensorName = sensor.getName();

        Intent intent = new Intent(this, SensorDataActivity.class);
        intent.putExtra(SensorDataActivity.SENSOR_INDEX,position);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent serverIntent = null;

        int id = item.getItemId();

        if(id == R.id.action_settings){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
