import java.nio.ByteBuffer;

import com.jogamp.openal.AL;
import com.jogamp.openal.ALFactory;
import com.jogamp.openal.util.ALut;

public class Audio {
      static AL al = ALFactory.getAL();
      static int[] buffer = new int[1];
      static int[] source = new int[1];
      static float[] sourcePos = { -4.0f, -4.0f, 0.0f};
      static float[] sourceVel = { 0.0f, 0.0f, 0.0f}; // source velocity
      static float[] listenerPos = {0.0f, 0.0f, 0.0f};
      static float[] listenerVel = {0.0f, 0.0f, 0.0f}; // velocity
      static float[] listenerOri = {0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f};
      static int loadALData(String fileName) {
            int[] format = new int[1];
            int[] size = new int[1];
            ByteBuffer[] data = new ByteBuffer[1];
            int[] freq = new int[1];
            int[] loop = new int[1];
            al.alGenBuffers(1, buffer, 0);
            if (al.alGetError() != AL.AL_NO_ERROR)
                  return AL.AL_FALSE;
            ALut.alutLoadWAVFile(fileName+".wav", format, data, size, freq, loop);
            al.alBufferData(buffer[0], format[0], data[0], size[0], freq[0]);
            al.alGenSources(1, source, 0);
            if (al.alGetError() != AL.AL_NO_ERROR)
                  return AL.AL_FALSE;
            al.alSourcei(source[0], AL.AL_BUFFER, buffer[0]);
            al.alSourcef(source[0], AL.AL_PITCH, 1.0f);
            al.alSourcef(source[0], AL.AL_GAIN, 1.0f);
            al.alSourcefv(source[0], AL.AL_POSITION, sourcePos, 0);
      al.alSourcefv(source[0], AL.AL_VELOCITY, sourceVel, 0);
      al.alSourcei(source[0], AL.AL_LOOPING, loop[0]);
      if (al.alGetError() == AL.AL_NO_ERROR)
            return AL.AL_TRUE;
      return AL.AL_FALSE;
            }

            static void setListenerValues() {
        al.alListenerfv(AL.AL_POSITION,	listenerPos, 0);
        al.alListenerfv(AL.AL_VELOCITY,    listenerVel, 0);
        al.alListenerfv(AL.AL_ORIENTATION, listenerOri, 0);
    }

    static void killALData() {
        al.alDeleteBuffers(1, buffer, 0);
        al.alDeleteSources(1, source, 0);
        ALut.alutExit();
    }

    public static void setAudio() {
      ALut.alutInit();
      al.alGetError();
      Runtime runtime = Runtime.getRuntime();
      runtime.addShutdownHook(new Thread(new Runnable() {
            public void run() {
                  killALData();
            }
      }));
    }
	public static void playSound(String fileName) {
		      if (loadALData(fileName) == AL.AL_FALSE)
            System.exit(-1);
      setListenerValues();
      al.alSourcePlay(source[0]);
	}
}
