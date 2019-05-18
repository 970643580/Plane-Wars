package jiabao.plane_wars;

//调用res下名为raw下的音频文件


import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class GameSoundPool {
    private SoundPool soundPool;
    private int s1;
    private int s2;

    public GameSoundPool(Context context) {
        this.soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC,0);
        s1 = soundPool.load(context,R.raw.shoot,1);
        s2 = soundPool.load(context,R.raw.explosion,1);
    }

    public void playSound(int s){
        switch (s){
            case 1:
                soundPool.play(s1,1,1,1,2,1.0f);
                break;
            case 2:
                soundPool.play(s2,1,1,1,2,1.0f);
                break;
        }



    }
}
