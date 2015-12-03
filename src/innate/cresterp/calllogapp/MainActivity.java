package innate.cresterp.calllogapp;

import java.util.Date;

import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.app.Activity;
import android.database.Cursor;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = (TextView)findViewById(R.id.textView);
        getCalls();
    }
    
    private void getCalls(){
          StringBuffer sb = new StringBuffer();
    	
    	Cursor managedCursor = managedQuery(Uri.parse("content://call_log/calls"), null, null, null, null);
    	
    	int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
    	int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
    	int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
    	int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);
    	
    	sb.append("Call Log: ");
    	
    	while(managedCursor.moveToNext()){
    		String phNumber = managedCursor.getString(number);
    		String callType = managedCursor.getString(type);
    		String callDate = managedCursor.getString(date);
    		Date callDayTime = new Date(Long.valueOf(callDate));
    		String callDuration = managedCursor.getString(duration);
    		
    		String dir = null;
    		
    		int dircode = Integer.parseInt(callType);
    		switch(dircode){
    		case CallLog.Calls.INCOMING_TYPE: dir = "INCOMING";
    		break;
    		
    		case CallLog.Calls.OUTGOING_TYPE: dir = "OUTGOING";
    		break;
    		
    		
    		}
    		
    		sb.append("\nPhone Number: " + phNumber + "\nCallType: " + dir +
    				"\nCall Date: " + callDayTime + "\nCall Duration in sec: " + callDuration);
    		sb.append("\n-------------------------------------");
    	}
    	display.setText(sb);
    }
    
    
    
}
