package dk.adamino.metronome;

import android.content.Context;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import dk.adamino.metronome.BLL.BPMCalculator;
import dk.adamino.metronome.BLL.ExpressionService;
import dk.adamino.metronome.BLL.IExpressionService;

public class MetronomeActivity extends AppCompatActivity {

    private static final String TAG = "adamino";
    public static final String BPM_PREFIX = "BPM: ";
    public static final String EXPRESSION_PREFIX = "Expression: ";

    private TextView mBeatView,mExpressionView;
    private Button mBtnBeatIt;

    private int mBPM;

    private Vibrator mVibrator;

    private BPMCalculator mBPMCalculator;
    private IExpressionService mExpressionService;
    private SoundPool mSoundPool;
    private int mSoundId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metronome);

        initializeViews();

        mBPMCalculator = new BPMCalculator();
        mExpressionService = new ExpressionService();
        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Setup SoundPool for beat playback
        mSoundPool = new SoundPool.Builder()
                .setMaxStreams(10)
                .build();
        mSoundId = mSoundPool.load(this, R.raw.claves, 1);
    }

    private void initializeViews() {
        mBtnBeatIt = findViewById(R.id.btnTempo);
        // Register a user beat for calculation of BPM
        mBtnBeatIt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    handleBeat();
                    return true;
                }
                return false;
            }
        });
        mBeatView = findViewById(R.id.beatView);
        mExpressionView = findViewById(R.id.expressionView);
    }

    /**
     * Handle beat from user
     */
    private void handleBeat() {
        // Vibrate phone to indicate beat to user
        vibrate();
        // Play sound on beat
        mSoundPool.play(mSoundId, 1, 1, 1, 0, 1);
        // Make a record of the beat for BPM calculation
        mBPMCalculator.recordTime();
        updateBPMView();
    }

    /**
     * Set musical expression according to tempo
     */
    private void upateExpressionView() {
        String expression = mExpressionService.getExpression(mBPM);
        mExpressionView.setText(EXPRESSION_PREFIX + expression);
    }

    /**
     * Update view according to beat tracking progress
     */
    private void updateBPMView() {
        if (mBPMCalculator.readyForCalculation()) {
            mBPM = mBPMCalculator.getBPM();
            mBeatView.setText(BPM_PREFIX + mBPM);
            upateExpressionView();
        } else {
            mExpressionView.setText(R.string.PleaseTapAgain);
        }
    }

    /**
     * Vibrate phone
     */
    private void vibrate() {
        mVibrator.vibrate(50);
    }
}
