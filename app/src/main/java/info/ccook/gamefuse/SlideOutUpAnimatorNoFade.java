package info.ccook.gamefuse;

import android.animation.ObjectAnimator;
import android.view.View;

import com.daimajia.androidanimations.library.BaseViewAnimator;

public class SlideOutUpAnimatorNoFade extends BaseViewAnimator {

    @Override
    public void prepare(View target) {
        getAnimatorAgent().playTogether(ObjectAnimator
                        .ofFloat(target, "translationY", 0, -target.getBottom()));
    }
}