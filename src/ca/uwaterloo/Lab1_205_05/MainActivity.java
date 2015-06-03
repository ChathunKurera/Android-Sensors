package ca.uwaterloo.Lab1_205_05;

import java.util.Arrays;

import android.app.Activity;
import android.app.Fragment;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends Activity {
	static LineGraphView graph;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        if (savedInstanceState == null) {
        	
        		getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();  
        		
        		graph = new LineGraphView(getApplicationContext(), 100, Arrays.asList("x", "y", "z"));
        		graph.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
    	
        public PlaceholderFragment(){
        	
        }
        

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            
            LinearLayout layout = (LinearLayout)rootView.findViewById(R.id.layout_Main);
            layout.setOrientation(LinearLayout.VERTICAL);
            TextView lightLabel = new TextView(rootView.getContext());
            TextView accelLabel = new TextView(rootView.getContext());
            TextView magntLabel = new TextView(rootView.getContext());
            TextView rotatLabel = new TextView(rootView.getContext());
                                 
            SensorManager sensorManager = (SensorManager) rootView.getContext().getSystemService(SENSOR_SERVICE);
                        
        	Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        	SensorEventListener l = new LightSensorEventListener(lightLabel);
        	sensorManager.registerListener(l, lightSensor,SensorManager.SENSOR_DELAY_NORMAL);
        	
        	Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        	SensorEventListener a = new AccelerometerSensorEventListener(accelLabel);
        	sensorManager.registerListener(a, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        	
        	Sensor magneto = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        	SensorEventListener m = new MagnetoSensorEventListener(magntLabel);
        	sensorManager.registerListener(m, magneto, SensorManager.SENSOR_DELAY_NORMAL);
        	
        	Sensor rotation = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        	SensorEventListener r = new RotationalSensorEventListener(rotatLabel);
        	sensorManager.registerListener(r, rotation, SensorManager.SENSOR_DELAY_NORMAL);
        	
        //learn to make a new output for the max rotational
        	
        	layout.addView(lightLabel);
        	layout.addView(accelLabel);
        	layout.addView(magntLabel);
        	layout.addView(rotatLabel);
        	
        	layout.addView(graph,0);
        	
        	return rootView; 
        }
    }
}
