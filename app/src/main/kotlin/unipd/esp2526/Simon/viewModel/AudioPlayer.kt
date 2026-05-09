package unipd.esp2526.Simon.viewModel

import android.app.Application
import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

import unipd.esp2526.Simon.R
import unipd.esp2526.Simon.ui.theme.ColorType

class AudioPlayer() : ViewModel()
{
    private var pool: SoundPool? = null
    private var sounds = mutableMapOf<ColorType, Int>()

    var isMuted by mutableStateOf(false)
    private set

    public fun loadSounds(context: Context)
    {
        val attributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        pool = SoundPool.Builder()
            .setMaxStreams(3)
            .setAudioAttributes(attributes)
            .build()

        pool?.let { soundPool ->
            sounds[ColorType.RED] = soundPool.load(context, R.raw.tone_r, 1)
            sounds[ColorType.GREEN] = soundPool.load(context, R.raw.tone_g, 1)
            sounds[ColorType.BLUE] = soundPool.load(context, R.raw.tone_b, 1)
            sounds[ColorType.MAGENTA] = soundPool.load(context, R.raw.tone_m, 1)
            sounds[ColorType.YELLOW] = soundPool.load(context, R.raw.tone_y, 1)
            sounds[ColorType.CYAN] = soundPool.load(context, R.raw.tone_c, 1)
        }
    }

    public fun play(color: ColorType)
    {
        if(!isMuted)
            sounds[color]?.let { id -> pool?.play(id, 1.0f, 1.0f, 1, 0, 1.0f) }
    }

    public fun toggleMute()
    {
        isMuted = !isMuted
        if(isMuted)
            pool?.autoPause()
    }

    public fun pause()
    {
        pool?.autoPause()
    }

    public fun resume()
    {
        pool?.autoResume()
    }

    override fun onCleared()
    {
        super.onCleared()
        pool?.release()
    }
}
