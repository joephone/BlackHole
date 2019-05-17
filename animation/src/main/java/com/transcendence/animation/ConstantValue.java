package com.transcendence.animation;

/**
 * @author Joephone on 2019/5/16 11:12
 * @E-Mail Address：joephonechen@gmail.com
 * @Desc
 */

public interface ConstantValue {
// ---------------------------简单动画页面------------------------------------
    /**
     *  a1 由完全显示 --> 完全透明   1 - 0
     *  a2 由完全透明 --> 完全显示   0 - 1
     */
    int[] simple = { R.anim.a1, R.anim.a2, R.anim.alpha_rotate,
                    R.anim.alpha_scale, R.anim.alpha_scale_rotate5,
                    R.anim.alpha_scale_translate, R.anim.alpha_scale_translate_rotate,
                    R.anim.alpha_translate, R.anim.alpha_translate_rotate,
                    R.anim.drawroll_ani_in_10, R.anim.drawroll_ani_out,
    };


    /*

            R.anim.drawroll_ani_in_10, R.anim.drawroll_ani_out, R.anim.fade,
            R.anim.gallery_in, R.anim.hold, R.anim.hyperspace_in,
            R.anim.hyperspace_out, R.anim.left_in, R.anim.left_out,
            R.anim.my_alpha_action, R.anim.my_scale_action,
            R.anim.myanimation_set, R.anim.myown_design, R.anim.push_left_in,
            R.anim.push_left_out, R.anim.push_up_in, R.anim.push_up_out,
            R.anim.right_in, R.anim.right_out, R.anim.rotate, R.anim.scale,
            R.anim.scale_rotate, R.anim.scale_translate,
            R.anim.scale_translate_rotate, R.anim.slide_down_out,
            R.anim.slide_left, R.anim.slide_right, R.anim.slide_up_in,
            R.anim.translate, R.anim.translate_rotate, R.anim.wave_scale,
            R.anim.zoom_enter, R.anim.zoom_exit
     */


    // ---------------------------复杂动画页面------------------------------------
    /**
     *  a1 由完全显示 --> 完全透明
     *  a2 由完全透明 --> 完全显示
     */
    int[] complex = { R.anim.block_move_right, R.anim.grow_from_top};

}
