package ca.uwaterloo.Lab1_205_05;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

class MagnetoSensorEventListener implements SensorEventListener {
	float x;
	float y;
	float z;
	TextView output;
	float maxMagx;
	float maxMagy;
	float maxMagz;
	
	public MagnetoSensorEventListener(TextView outputText){
		output = outputText;
	}
	
		public void onAccuracyChanged(Sensor s, int i){}
		
		public void onSensorChanged(SensorEvent se){
			if (se.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
							
				x = se.values[0];
				y = se.values[1];
				z = se.values[2];
				
				if(Math.abs(se.values[0]) > maxMagx ) {maxMagx = Math.abs(se.values[0]);}
				if(Math.abs(se.values[1]) > maxMagy ) {maxMagy = Math.abs(se.values[1]);}
				if(Math.abs(se.values[2]) > maxMagz ) {maxMagz = Math.abs(se.values[2]) ;}
				
				String[] num = new String[] {(String.format("%.3f", x)), (String.format("%.3f", y)), (String.format("%.3f", z))};
				String maxValues = String.format("(%.3f, %.3f, %.3f)", maxMagx, maxMagy, maxMagz);
				
				output.setText("MAGNETOMETER (uT) \n        X: " + num[0] + "\n"
						+ "        Y: " + num[1] + "\n        Z: " + num[2] +"\n"
						+ " Max :" + maxValues + "\n" ); 
				
							
				}
			}

}
