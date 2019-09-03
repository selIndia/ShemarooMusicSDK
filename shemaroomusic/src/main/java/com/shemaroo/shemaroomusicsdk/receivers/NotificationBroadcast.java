package com.shemaroo.shemaroomusicsdk.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;

import com.shemaroo.shemaroomusicsdk.activity.PlayerActivity;
import com.shemaroo.shemaroomusicsdk.mediaplayer.Controls;
import com.shemaroo.shemaroomusicsdk.mediaplayer.PlayerConstants;
import com.shemaroo.shemaroomusicsdk.mediaplayer.SongService;

public class NotificationBroadcast extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(Intent.ACTION_MEDIA_BUTTON)) {
            KeyEvent keyEvent = (KeyEvent) intent.getExtras().get(Intent.EXTRA_KEY_EVENT);
            if (keyEvent.getAction() != KeyEvent.ACTION_DOWN)
                return;

            switch (keyEvent.getKeyCode()) {
                case KeyEvent.KEYCODE_HEADSETHOOK:
                case KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE:
                	if(!PlayerConstants.SONG_PAUSED){
    					Controls.pauseControl(context);
                	}else{
    					Controls.playControl(context);
                	}
                	break;
                case KeyEvent.KEYCODE_MEDIA_PLAY:
                	break;
                case KeyEvent.KEYCODE_MEDIA_PAUSE:
                	break;
                case KeyEvent.KEYCODE_MEDIA_STOP:
                	break;
            }
		}  else{
            	if (intent.getAction().equals(SongService.NOTIFY_PLAY)) {
    				Controls.playControl(context);
        		} else if (intent.getAction().equals(SongService.NOTIFY_PAUSE)) {
    				Controls.pauseControl(context);
        		} else if (intent.getAction().equals(SongService.NOTIFY_DELETE)) {
					Intent i = new Intent(context, SongService.class);
					context.stopService(i);
					PlayerActivity.imgBack.performClick();
        		}
		}
	}
	
	public String ComponentName() {
		return this.getClass().getName(); 
	}
}
