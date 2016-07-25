package mposadar.com.thelastfm.ui;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.IntegerRes;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * define the size of offset between items of the RecyclerView
 * check display density to convert dp to px
 * Created by mposadar on 22/07/16.
 */
public class ItemOffsetDecoration extends RecyclerView.ItemDecoration {

    // size in px of spacecing
    int itemOffset;

    /**
     *
     * @param context
     * @param integerResId
     */
    public ItemOffsetDecoration(Context context, @IntegerRes int integerResId) {
        // get value for multiples density
        int itemOffsetDp = context.getResources().getInteger(integerResId);
        // converts dp to px
        itemOffset = convertToPx(itemOffsetDp, context.getResources().getDisplayMetrics());
    }

    /**
     * Convert DP to PX
     * @param offsetDp
     * @param metrics
     * @return
     */
    public int convertToPx (int offsetDp, DisplayMetrics metrics) {
        return offsetDp * (metrics.densityDpi / 160);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(itemOffset, itemOffset, itemOffset, itemOffset);
    }
}
