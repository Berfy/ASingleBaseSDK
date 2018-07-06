package cn.berfy.sdk.mvpbase.util;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;

import cn.berfy.sdk.mvpbase.R;
import cn.berfy.sdk.mvpbase.config.Constant;

/**
 * Berfy 修改 2018.6.13
 * SoundPool 铃声尽量不要超过1M
 * 在不同的系统下 SoundPool 表现可能存在不一致
 */
public class VoicePlayer {

    public static final String TAG = "VoicePlayer";
    private Context mContext;
    private static VoicePlayer mInstance = null;
    private MediaPlayer mMediaPlayer;
    private boolean mLoop;
    private AudioManager mAudioManager;

    protected OnCompleteListener onCompleteListener;

    public enum RingerTypeEnum {
        TAB_CLICK;
    }

    public static VoicePlayer instance(Application application) {
        if (null == mInstance) {
            synchronized (VoicePlayer.class) {
                if (null == mInstance) {
                    mInstance = new VoicePlayer(application);
                }
            }
        }
        return mInstance;
    }

    private VoicePlayer(Context context) {
        mContext = context;
        mAudioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
    }

    public synchronized void play(RingerTypeEnum type) {
        int ringId = 0;
        switch (type) {
            case TAB_CLICK:
                ringId = R.raw.tab_click;
                mLoop = false;
                break;
        }
        if (ringId != 0) {
            play(ringId);
        }

    }

    public void stop() {
        if (null != mMediaPlayer) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
            }
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    private void play(int ringId) {
        initAndPlay(ringId);
    }

    private void initAndPlay(int ringId) {
        stop();
        //开启子线程播放音乐
        Constant.EXECUTOR.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    if (null == mMediaPlayer) {
                        mMediaPlayer = new MediaPlayer();
                        AssetFileDescriptor fd = mContext.getResources().openRawResourceFd(ringId);
                        mMediaPlayer.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(), fd.getLength());
                        //音量跟随打电话
                        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_RING);
                        mMediaPlayer.setLooping(mLoop);
                        mMediaPlayer.prepare();
                    }
                    if (!mMediaPlayer.isPlaying()) {
                        mMediaPlayer.seekTo(0);
                        mMediaPlayer.start();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                initComplete();
            }
        });
    }

    public interface OnCompleteListener {
        void completeListener();
    }

    public void setComListener(OnCompleteListener onCompleteListener) {
        this.onCompleteListener = onCompleteListener;
    }

    private void initComplete() {
        if (mMediaPlayer != null) {
            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if (onCompleteListener != null)
                        onCompleteListener.completeListener();
                }
            });
        }
    }
}
