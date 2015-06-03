package ca.uwaterloo.Lab1_205_05;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

class LightSensorEventListener implements SensorEventListener {
	TextView output;
	float light;
	
	public LightSensorEventListener(TextView outputView){
		output = outputView;
		}

	public void onAccuracyChanged(Sensor s, int i){}
	
	public void onSensorChanged(SensorEvent se){
		
		if (se.sensor.getType() == Sensor.TYPE_LIGHT){
						
			light = se.values[0];
			
			String li = String.format("%.3f", light);
			
			output.setText("LIGHT SENSOR \n\n        value: " + li + " lux\n" );   
			}
		}
	}