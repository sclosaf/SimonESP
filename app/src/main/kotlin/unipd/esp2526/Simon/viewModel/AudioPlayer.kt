package unipd.esp2526.Simon.viewModel

import android.app.Application
import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel

import unipd.esp2526.Simon.R
import unipd.esp2526.Simon.ui.theme.ColorType

class AudioPlayer(app: Application) : AndroidViewModel(app)
{
    private val context = getApplication<Application>()

    private var pool: SoundPool
    private var sounds = mutableMapOf<ColorType, Int>()

    var isMuted by mutableStateOf(false)
        private set

    init
    {
        val attributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        pool = SoundPool.Builder()
            .setMaxStreams(3)
            .setAudioAttributes(attributes)
            .build()

        loadSounds(context.applicationContext)
    }

    private fun loadSounds(context: Context)
    {
        sounds[ColorType.RED] = pool.load(context, R.raw.tone_r, 1)
        sounds[ColorType.GREEN] = pool.load(context, R.raw.tone_g, 1)
        sounds[ColorType.BLUE] = pool.load(context, R.raw.tone_b, 1)
        sounds[ColorType.MAGENTA] = pool.load(context, R.raw.tone_m, 1)
        sounds[ColorType.YELLOW] = pool.load(context, R.raw.tone_y, 1)
        sounds[ColorType.CYAN] = pool.load(context, R.raw.tone_c, 1)
    }

    public fun play(color: ColorType)
    {
        if(!isMuted)
            sounds[color]?.let { id -> pool.play(id, 1.0f, 1.0f, 1, 0, 1.0f) }
    }

    public fun toggleMute()
    {
        isMuted = !isMuted
        if(isMuted)
            pause()
    }

    public fun pause()
    {
        pool.autoPause()
    }

    public fun resume()
    {
        pool.autoResume()
    }

    override fun onCleared()
    {
        super.onCleared()
        release()
    }
}
