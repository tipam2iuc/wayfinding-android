package e.plass.acceuilwayfinding.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import e.plass.acceuilwayfinding.R;
import edmt.dev.advancednestedscrollview.MaxHeightRecyclerView;
import edmt.dev.advancednestedscrollview.MyViewGroupUtils;

public class CustumBehavior extends CoordinatorLayout.Behavior<NestedScrollView> {
    public CustumBehavior(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull NestedScrollView child, @NonNull View dependency) {
        return dependency.getId() == R.id.toolbar_container;
    }

    @Override
    public boolean onLayoutChild(@NonNull CoordinatorLayout parent, @NonNull NestedScrollView child, int layoutDirection) {
        parent.onLayoutChild(child,layoutDirection );
        
        setTopMargin(child.findViewById(R.id.card_view));

        int rvMaxHeight = child.getHeight() - child.findViewById(R.id.card_title).getHeight() -
                child.findViewById(R.id.card_sub_title).getHeight();

        MaxHeightRecyclerView rv = child.findViewById(R.id.card_recycler_view);
        rv.setMaxHeight(rvMaxHeight);

        View cardContainer = child.findViewById(R.id.card_container);
        int toolbarContainerHeight = parent.getDependencies(child).get(0).getHeight();
        setPaddingTop(cardContainer,rvMaxHeight-toolbarContainerHeight);
        ViewCompat.offsetTopAndBottom(child, toolbarContainerHeight);
        setPaddingBottom(rv,toolbarContainerHeight);

        return true;

    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull CoordinatorLayout parent, @NonNull NestedScrollView child, @NonNull MotionEvent ev) {
        return ev.getActionMasked() == MotionEvent.ACTION_DOWN
                && isTouchInChildSounds(parent,child,ev)
                && !isTouchInChildSounds(parent,child.findViewById(R.id.card_view),ev);
    }

    private boolean isTouchInChildSounds(ViewGroup parent, View child, MotionEvent ev) {

        return MyViewGroupUtils.isPointInChildBounds(parent, child, (int)ev.getX(), (int)ev.getY());

    }

    private void setTopMargin(View view) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if(layoutParams.topMargin != 0){
            layoutParams.topMargin = 0;
            view.setLayoutParams(layoutParams);
        }
    }

    private void setPaddingBottom(MaxHeightRecyclerView view, int bottom) {
        if(view.getPaddingBottom() != bottom){
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), bottom);
        }
    }

    private void setPaddingTop(View view, int top) {
        if(view.getPaddingTop() != top){
            view.setPadding(view.getPaddingLeft(), top, view.getPaddingRight(), view.getPaddingBottom());
        }
    }
}
