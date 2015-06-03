package ca.uwaterloo.Lab1_205_05;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

class AccelerometerSensorEventListener implements SensorEventListener {
	float x;
	float y;
	float z;
	TextView output;
	float maxAccx;
	float maxAccy;
	float maxAccz;
	
	public AccelerometerSensorEventListener(TextView outputText){
		output = outputText;
	}
		
		public void onAccuracyChanged(Sensor s, int i){}
		
		public void onSensorChanged(SensorEvent se){
			MainActivity.graph.addPoint(se.values);
				
			if (se.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
				
				x = se.values[0];
				y = se.values[1];
				z = se.values[2];
				
				if(Math.abs(se.values[0]) > maxAccx ) {maxAccx = Math.abs(se.values[0]);}
				if(Math.abs(se.values[1]) > maxAccy ) {maxAccy = Math.abs(se.values[1]);}
				if(Math.abs(se.values[2]) > maxAccz ) {maxAccz = Math.abs(se.values[2]) ;}
				
				String[] num = new String[] {(String.format("%.3f", x)), (String.format("%.3f", y)), (String.format("%.3f", z))};
				String maxValues = String.format("(%.3f, %.3f, %.3f)", maxAccx, maxAccy, maxAccz);
								
				output.setText("--ACCELEROMETER (m/s^2)-- \n        X: " + num[0] + "\n"
						+ "        Y: " + num[1] + "\n        Z: " + num[2] +"\n"
						+ " Max :" + maxValues + "\n" );   
				}
			}
	}

