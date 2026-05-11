package unipd.esp2526.Simon.viewModel

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

import unipd.esp2526.Simon.R
import unipd.esp2526.Simon.ui.theme.ColorType

/**
 * ViewModel used to manage the audio feedback of the game.
 *
 * This class is responsible for loading, playing and controlling
 * sound effects associated with each colored button.
 */
class AudioPlayer() : ViewModel()
{
    private var pool: SoundPool? = null
    private var sounds = mutableMapOf<ColorType, Int>()

    /**
     * Flag indicating whether the audio playback is currently muted.
     * When true, any play action is ignored.
     */
    var isMuted by mutableStateOf(false)
        private set

    /**
     * Loads all sound resources into the SoundPool member.
     *
     * This method must be called before any play action is performed,
     * otherwise no sound will be played.
     *
     * @param context The context used to access raw resources
     */
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

    /**
     * Plays the tone associated with the given color.
     * If isMuted is true, the call is ignored.
     *
     * @param color The color whose tone should be played
     */
    public fun play(color: ColorType)
    {
        if(!isMuted)
            sounds[color]?.let { id -> pool?.play(id, 1.0f, 1.0f, 1, 0, 1.0f) }
    }

    /**
     * Toggles the mute state between on and off.
     * When muting, automatically pauses all currently playing sounds.
     */
    public fun toggleMute()
    {
        isMuted = !isMuted
        if(isMuted)
            pool?.autoPause()
    }

    /**
     * Pauses all currently playing sounds.
     */
    public fun pause()
    {
        pool?.autoPause()
    }

    /**
     * Resumes all previously paused sounds.
     */
    public fun resume()
    {
        pool?.autoResume()
    }

    /**
     * Override onCleared method.
     */
    override fun onCleared()
    {
        super.onCleared()
        pool?.release()
    }
}
