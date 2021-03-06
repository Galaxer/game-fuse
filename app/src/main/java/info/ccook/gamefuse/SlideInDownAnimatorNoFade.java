package info.ccook.gamefuse;

import android.animation.ObjectAnimator;
import android.view.View;

import com.daimajia.androidanimations.library.BaseViewAnimator;

public class SlideInDownAnimatorNoFade extends BaseViewAnimator {

    @Override
    public void prepare(View target) {
        int distance = target.getTop() + target.getHeight();
        getAnimatorAgent()
                .playTogether(ObjectAnimator.ofFloat(target, "translationY", -distance, 0));
    }
}