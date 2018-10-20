package vyacheslav.lukyanov.com.testtaskandroiddev;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

public class EditTextUrl extends AppCompatEditText {

    private static final String URL_PREFIX = "http://";
    private Rect urlPrefixRect = new Rect(); // actual prefix size

    public EditTextUrl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        getPaint().getTextBounds(URL_PREFIX, 0, URL_PREFIX.length(), urlPrefixRect);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(URL_PREFIX, super.getCompoundPaddingLeft(), getBaseline(), getPaint());
    }

    @Override
    public int getCompoundPaddingLeft() {
        return super.getCompoundPaddingLeft() + urlPrefixRect.width();
    }
}
