package com.hyg.video.ui.widget

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.RelativeLayout
import com.hyg.video.core.HPlayer
import com.hyg.video.core.OnPlayerListener
import com.hyg.video.core.PLayCore
import com.hyg.video.core.exo.ExoVideoPlayerFactory
import com.hyg.video.core.ijk.IjkVideoPlayerFactory
import com.hyg.video.ui.InterPlayer
import com.hyg.video.ui.R
import com.hyg.video.ui.ViewParams
import com.hyg.video.ui.annotation.PlaybackSpeed
import com.hyg.video.ui.annotation.RendererType
import com.hyg.video.ui.controller.*
import com.hyg.video.ui.helper.AnimatorHelper
import com.hyg.video.ui.helper.ClockHelper
import com.hyg.video.ui.manager.ControllerManagerImpl
import com.hyg.video.ui.manager.IControllerManager

/**
 * @Author 韩永刚
 * @Date 2021/07/28
 * @Desc
 */
class HVideoView : FrameLayout, InterPlayer {
    private val params = ViewParams()

    private val animatorHelper: AnimatorHelper
    private val clockHelper: ClockHelper
    private val player: HPlayer

    private val rootContainer: FrameLayout
    private val rendererContainer: RelativeLayout
    private val middleContainer: RelativeLayout
    private val topContainer: RelativeLayout

    /**
     * 控制器管理器
     */
    private val controllerManager: IControllerManager = ControllerManagerImpl()

    private val onPlayerListener: OnPlayerListener = object : OnPlayerListener {
        override fun onEvent(event: Int) {
            controllerManager.dispense(event)
        }

        override fun onError(code: Int, error: String, throwable: Throwable?) {
            controllerManager.dispense(code)
        }

    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val array: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.HVideoView)
        params.renderer = array.getInt(R.styleable.HVideoView_rendererType, RendererType.TEXTURE)
        array.recycle()
        player = createPlayer(params.playCore)
        initPlayer()
        rootContainer = createRootContainer()
        rendererContainer = createRendererContainer()
        middleContainer = createMiddleContainer()
        topContainer = createTopContainer()
        loadController()
        animatorHelper = AnimatorHelper(
            this,
            params.maxAnimatorValue,
            params.animatorDuration,
            params.animatorClock
        )
        clockHelper = ClockHelper(this)
    }


    private fun createPlayer(@PLayCore core: Int): HPlayer =
        if (core == PLayCore.IJK) {
            IjkVideoPlayerFactory()
        } else {
            ExoVideoPlayerFactory()
        }.create(context)

    private fun initPlayer() {
        player.setOnPlayerListener(onPlayerListener)
        player.initPlayer()
    }

    private fun createRootContainer(): FrameLayout {
        val rootContainer = FrameLayout(context)
        val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        addView(rootContainer, params)
        return rootContainer
    }

    private fun createRendererContainer(): RelativeLayout {
        val container = RelativeLayout(context)
        val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        rootContainer.addView(container, params)
        return container
    }

    private fun createMiddleContainer(): RelativeLayout {
        val container = RelativeLayout(context)
        val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        rootContainer.addView(container, params)
        return container
    }

    private fun createTopContainer(): RelativeLayout {
        val container = RelativeLayout(context)
        val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        rootContainer.addView(container, params)
        return container
    }

    /**
     * 加载控制器
     */
    private fun loadController() {
        //渲染控制器
        controllerManager.add(TopFunctionController(rendererContainer, this))
        //手势控制器
        controllerManager.add(GestureController(rendererContainer, this))
        //字幕

        //缓冲控制器
        controllerManager.add(LoadingController(middleContainer, this))
        //底部导航栏
        controllerManager.add(BottomFunctionController(topContainer, this))

        //顶部控制器
        controllerManager.add(TopFunctionController(topContainer, this))
    }

    override fun getPlayer(): HPlayer = player

    override fun play() {
        if (!isPlaying()) {
            player.play()
        }
    }

    override fun pause() {
        if (isPlaying()) {
            player.pause()
        }
    }

    override fun isPlaying(): Boolean = player.isPlaying()
    override fun updateSpeed(speed: PlaybackSpeed) {
        clockHelper.updateSpeed(speed)
    }

    override fun getRendererType(): Int = params.renderer
    override fun getControllerManager(): IControllerManager = controllerManager
    override fun getScreenOrientation(): Int = params.screenOrientation

    override fun onScreenChanged(orientation: Int) {
        TODO("Not yet implemented")
    }

    override fun onClock() {
        controllerManager.get(IController.BOTTOM_CONTROLLER_KEY)?.updateProgress()
    }

    override fun onDetachedFromWindow() {
        animatorHelper.release()
        clockHelper.stop()
        controllerManager.clear()
        super.onDetachedFromWindow()
    }
}