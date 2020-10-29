package com.example.onlineacadezy;

import android.app.usage.UsageEvents;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.content.Context;
import android.util.AttributeSet;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.ViewConfiguration;
import android.widget.ImageView;

import java.util.List;


//
//public class PaintView extends View {
//    private Bitmap bitmap;
//    private Canvas canvas;
//    private Path penPath;
//    private Paint penPaint;
//    private Paint otherPaint;
//    private Paint canvasPaint;
//    Bitmap mImage,mImage1;
//    Drawable mImageNew;
//    ImageView imageView;
//    float mImagex, mImageY;
//    float startX, startY, ammountX, ammountY;
//    GestureDetector gestureDetector;
//    Runnable mLongPressed;
//    Handler handler;
//    MotionEvent event;
//    int longPress = 0;
//    private float mScaleFactor = 1.f;
//    private static final int INVALID_POINTER_ID = -1;
//    private int mActivePointerId = INVALID_POINTER_ID;
//    private float mLastTouchX;
//    private float mLastTouchY;
//    Canvas cvs;
//
//    private ScaleGestureDetector mScaleDetector;
////    private PaintClient paintClient;
//
//    public PaintView(Context context, AttributeSet attributeSet) {
//        super(context, attributeSet);
//        mImageNew = getResources().getDrawable(R.drawable.asssessment);
//        mImageNew.setBounds(0, 0, mImageNew.getIntrinsicWidth(), mImageNew.getIntrinsicHeight());
//
//        setupPainting();
//        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
//        handler = new Handler();
//        mLongPressed = new Runnable() {
//            public void run() {
//                longPress = 1;
//            }
//        };
//    }
//
////    public void setPaintClient(PaintClient paintClient) {
////        this.paintClient = paintClient;
////    }
//
//    public void clearCanvas() {
//        this.canvas.drawColor(0, PorterDuff.Mode.CLEAR);
//        invalidate();
//    }
//
//    public void putImage() {
////        Bitmap mImage= BitmapFactory.decodeResource(getResources(),R.drawable.asssessment);
////        this.canvas.drawBitmap(mImage,0,0,null);
//        mImage = BitmapFactory.decodeResource(getResources(), R.drawable.asssessment);
//        cvs.scale(mScaleFactor, mScaleFactor);
//        cvs.drawBitmap(mImage, ammountX, ammountY, null);
//    }
//
//    public void paintPoint(Integer x, Integer y) {
//        this.canvas.drawPoint(x, y, this.otherPaint);
//        invalidate();
//    }
//
//    protected void setupPainting() {
//        this.bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
//        this.canvas = new Canvas(bitmap);
//
//        this.penPath = new Path();
//        mImagex = getWidth() / 2;
//        mImageY = getHeight() / 2;
//        this.penPaint = new Paint();
//        this.penPaint.setColor(Color.BLUE);
//        this.penPaint.setAntiAlias(true);
//        this.penPaint.setStrokeWidth(10);
//        this.penPaint.setStyle(Paint.Style.STROKE);
//        this.penPaint.setStrokeJoin(Paint.Join.ROUND);
//        this.penPaint.setStrokeCap(Paint.Cap.ROUND);
//
//        this.otherPaint = new Paint();
//        this.otherPaint.setColor(Color.GREEN);
//        this.otherPaint.setAntiAlias(true);
//        this.otherPaint.setStrokeWidth(10);
//        this.otherPaint.setStyle(Paint.Style.STROKE);
//        this.otherPaint.setStrokeJoin(Paint.Join.ROUND);
//        this.otherPaint.setStrokeCap(Paint.Cap.ROUND);
//        this.canvasPaint = new Paint(Paint.DITHER_FLAG);
//    }
//
//    @Override
//    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
//        super.onSizeChanged(width, height, oldWidth, oldHeight);
//        this.bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
//        this.canvas = new Canvas(bitmap);
//    }
//
//    @Override
//    protected void onDraw(Canvas cvs) {
//        cvs.drawBitmap(bitmap, 0, 0, canvasPaint);
//        cvs.drawPath(penPath, penPaint);
//        //       this.cvs=cvs;
//
////        this.canvas.save();
////        this.canvas.translate(ammountX, ammountY);
//
////        mImage = BitmapFactory.decodeResource(getResources(), R.drawable.asssessment);
////        cvs.scale(mScaleFactor, mScaleFactor);
////        cvs.drawBitmap(mImage, ammountX, ammountY, null);
//
//
// //       cvs.save();
////        cvs.translate(ammountX, ammountY);
////        cvs.scale(mScaleFactor, mScaleFactor);
////        mImageNew.draw(cvs);
// //       cvs.restore();
//
////        canvas.restore();
////        this.canvas.drawBitmap(bitmap,,rect,null);
//    }
//
////    @Override
////    public boolean onTouchEvent(MotionEvent ev) {
////        // Let the ScaleGestureDetector inspect all events.
////        mScaleDetector.onTouchEvent(ev);
////
////        final int action = ev.getAction();
////        switch (action & MotionEvent.ACTION_MASK) {
////            case MotionEvent.ACTION_DOWN: {
////                final float x = ev.getX();
////                final float y = ev.getY();
////
////                mLastTouchX = x;
////                mLastTouchY = y;
////                mActivePointerId = ev.getPointerId(0);
////                break;
////            }
////
////            case MotionEvent.ACTION_MOVE: {
////                final int pointerIndex = ev.findPointerIndex(mActivePointerId);
////                final float x = ev.getX(pointerIndex);
////                final float y = ev.getY(pointerIndex);
////
////                // Only move if the ScaleGestureDetector isn't processing a gesture.
////                if (!mScaleDetector.isInProgress()) {
////                    final float dx = x - mLastTouchX;
////                    final float dy = y - mLastTouchY;
////                        ammountX += dx;
////                        ammountY += dy;
////                        invalidate();
////                }
////
////                mLastTouchX = x;
////                mLastTouchY = y;
////
////                break;
////            }
////
////            case MotionEvent.ACTION_UP: {
////                mActivePointerId = INVALID_POINTER_ID;
////                break;
////            }
////
////            case MotionEvent.ACTION_CANCEL: {
////                mActivePointerId = INVALID_POINTER_ID;
////                break;
////            }
////
////            case MotionEvent.ACTION_POINTER_UP: {
////                final int pointerIndex = (ev.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK)
////                        >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
////                final int pointerId = ev.getPointerId(pointerIndex);
////                if (pointerId == mActivePointerId) {
////                    // This was our active pointer going up. Choose a new
////                    // active pointer and adjust accordingly.
////                    final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
////                    mLastTouchX = ev.getX(newPointerIndex);
////                    mLastTouchY = ev.getY(newPointerIndex);
////                    mActivePointerId = ev.getPointerId(newPointerIndex);
////                }
////                break;
////            }
////        }
////
////        return true;
////    }
//
//
//
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        // Let the ScaleGestureDetector inspect all events.
//        mScaleDetector.onTouchEvent(event);
//        PointF touchPoint = new PointF();
//        touchPoint.set(event.getX(), event.getY());
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                handler.postDelayed(mLongPressed, ViewConfiguration.getLongPressTimeout());
//                this.penPath.moveTo(touchPoint.x, touchPoint.y);
//                mActivePointerId = event.getPointerId(0);
//                break;
//
//            case MotionEvent.ACTION_MOVE:
//                handler.removeCallbacks(mLongPressed);
//                if (longPress == 1) {
//                    ammountX = event.getX() - startX;
//                    ammountY = event.getY() - startY;
//                    postInvalidate();
//                }
//                else {
//                    this.penPath.lineTo(touchPoint.x, touchPoint.y);
//                }
//                break;
//
//            case MotionEvent.ACTION_UP:
//                handler.removeCallbacks(mLongPressed);
//                longPress = 0;
//                this.canvas.drawPath(penPath, penPaint);
//                this.penPath.reset();
//                break;
//
//            default:
//                return false;
//        }
//
//        invalidate();
//        return true;
//    }
//
//    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
//        @Override
//        public boolean onScale(ScaleGestureDetector detector) {
////            mScaleFactor *= detector.getScaleFactor();
////
////            // Don't let the object get too small or too large.
////            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f));
//
//            invalidate();
//            return true;
//        }
//    }
//}
//
//
////                this.paintClient.emitPaint(Math.round(touchPoint.x), Math.round(touchPoint.y));
////                float x=event.getX();
////                float y=event.getY();
////                double dx=Math.pow(x-mImagex,2);
////                double dy=Math.pow(y-mImageY,2);
////                mImagex=x;
////                mImageY=y;
////                postInvalidate();
//
////    @Override
////    public boolean onTouchEvent(MotionEvent event) {
////                PointF touchPoint = new PointF();
////        touchPoint.set(event.getX(), event.getY());
////        if(event.getAction() == MotionEvent.ACTION_DOWN) {
////            this.penPath.moveTo(touchPoint.x, touchPoint.y);
////            handler.postDelayed(mLongPressed, ViewConfiguration.getLongPressTimeout());
////            return true;
////        }
////        if((event.getAction() == MotionEvent.ACTION_MOVE)|| (event.getAction() == MotionEvent.ACTION_UP)) {
////            Log.d("xsclksc","call removed");
////            handler.removeCallbacks(mLongPressed);
////            if( event.getAction() == MotionEvent.ACTION_MOVE) {
//////                if((longPress==1)) {
//////                    ammountX = event.getX() - startX;
//////                    ammountY = event.getY() - startY;
//////                    postInvalidate();
//////                    return true;
//////                }
//////                else
//////                {
////                    Log.d("xsclksc","writting");
////                    this.penPath.lineTo(touchPoint.x, touchPoint.y);
////                    return true;
//// //               }
////
////            }
////
////            return true;
////        }
////        return super.onTouchEvent(event);
////    }
























//package com.example.onlineacadezy;
//
//import android.app.usage.UsageEvents;
//import android.graphics.BitmapFactory;
//import android.graphics.Color;
//import android.graphics.Matrix;
//import android.graphics.PointF;
//import android.graphics.PorterDuff;
//import android.graphics.RectF;
//import android.graphics.drawable.Drawable;
//import android.os.Handler;
//import android.util.Log;
//import android.view.GestureDetector;
//import android.view.KeyEvent;
//import android.view.MotionEvent;
//import android.view.ScaleGestureDetector;
//import android.view.View;
//import android.content.Context;
//import android.util.AttributeSet;
//import android.graphics.Bitmap;
//import android.graphics.Canvas;
//import android.graphics.Paint;
//import android.graphics.Path;
//import android.view.ViewConfiguration;
//import android.widget.ImageView;
//
//import java.util.List;


public class PaintView extends View {
    private Bitmap bitmap;
    private Canvas canvas;
    private Path penPath;
    private Paint penPaint;
    private Paint otherPaint;
    private Paint canvasPaint;
    Bitmap mImage1,mImage2,mImage3,mImage4,mImage5,mImageResized;
    Drawable mImageNew;
    ImageView imageView;
    float mImagex, mImageY;
    float startX, startY, ammountX=40f, ammountY;
    GestureDetector gestureDetector;
    Runnable mLongPressed;
    Handler handler;
    MotionEvent event;
    int longPress = 0;
    private float mScaleFactor = 1f;
    private static final int INVALID_POINTER_ID = -1;
    private int mActivePointerId = INVALID_POINTER_ID;
    private float mLastTouchX;
    private float mLastTouchY;
    int status=0;
    int a=0;
    List<ImageDataModel> imageDataModelList;
    private ScaleGestureDetector mScaleDetector;
//    private PaintClient paintClient;
    int requiredHeight=500;
    int requiredWidth=500;

    public PaintView(Context context) {
        super(context);
        setupPainting();
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        handler = new Handler();
        mLongPressed = new Runnable() {
            public void run() {
                longPress = 1;
            }
        };
    }

//    public PaintView(Context context, AttributeSet attributeSet) {
//        super(context, attributeSet);
////        mImageNew.setBounds(0, 0, mImageNew.getIntrinsicWidth(), mImageNew.getIntrinsicHeight());
//        setupPainting();
//        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
//        handler = new Handler();
//        mLongPressed = new Runnable() {
//            public void run() {
//                longPress = 1;
//                Log.d("dcjsidc","longPressed");
//            }
//        };
//    }

//    public void setPaintClient(PaintClient paintClient) {
//        this.paintClient = paintClient;
//    }

    public void clearCanvas() {
        this.canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        invalidate();
    }

    public void putImage(List<ImageDataModel> imageDataModelList) {
        status=status+1;
        this.imageDataModelList=imageDataModelList;
        invalidate();
    }

    public void paintPoint(Integer x, Integer y) {
        this.canvas.drawPoint(x, y, this.otherPaint);
        invalidate();
    }

    protected void setupPainting() {
        this.bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        this.canvas = new Canvas(bitmap);

        this.penPath = new Path();
        mImagex = getWidth() / 2;
        mImageY = getHeight() / 2;
        this.penPaint = new Paint();
        this.penPaint.setColor(Color.BLUE);
        this.penPaint.setAntiAlias(true);
        this.penPaint.setStrokeWidth(10);
        this.penPaint.setStyle(Paint.Style.STROKE);
        this.penPaint.setStrokeJoin(Paint.Join.ROUND);
        this.penPaint.setStrokeCap(Paint.Cap.ROUND);

        this.otherPaint = new Paint();
        this.otherPaint.setColor(Color.GREEN);
        this.otherPaint.setAntiAlias(true);
        this.otherPaint.setStrokeWidth(10);
        this.otherPaint.setStyle(Paint.Style.STROKE);
        this.otherPaint.setStrokeJoin(Paint.Join.ROUND);
        this.otherPaint.setStrokeCap(Paint.Cap.ROUND);
        this.canvasPaint = new Paint(Paint.DITHER_FLAG);
//        mImage = BitmapFactory.decodeResource(getResources(), R.drawable.asssessment);
//        this.canvas.scale(mScaleFactor, mScaleFactor);
//        this.canvas.drawBitmap(mImage, ammountX, ammountY, null);
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        this.bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        this.canvas = new Canvas(bitmap);
    }

    @Override
    protected void onDraw(Canvas cvs) {
        switch (status)
        {
            case 1:
                mImage1=getResizedBitmap(imageDataModelList.get(0).getBitmap(),requiredWidth,requiredHeight);
                cvs.scale(mScaleFactor, mScaleFactor);
                cvs.drawBitmap(mImage1, imageDataModelList.get(0).getAmountX(), imageDataModelList.get(0).getAmountY(), null);
                break;

            case 2:

                mImage1=getResizedBitmap(imageDataModelList.get(0).getBitmap(),requiredWidth,requiredHeight);
                cvs.scale(mScaleFactor, mScaleFactor);
                cvs.drawBitmap(mImage1, imageDataModelList.get(0).getAmountX(), imageDataModelList.get(0).getAmountY(), null);

                mImage2=getResizedBitmap(imageDataModelList.get(1).getBitmap(),requiredWidth,requiredHeight);
                cvs.scale(mScaleFactor, mScaleFactor);
                cvs.drawBitmap(mImage2, imageDataModelList.get(1).getAmountX(), imageDataModelList.get(1).getAmountY(), null);
                break;
            case 3:
                mImage1=getResizedBitmap(imageDataModelList.get(0).getBitmap(),requiredWidth,requiredHeight);
                cvs.scale(mScaleFactor, mScaleFactor);
                cvs.drawBitmap(mImage1, imageDataModelList.get(0).getAmountX(), imageDataModelList.get(0).getAmountY(), null);

                mImage2=getResizedBitmap(imageDataModelList.get(1).getBitmap(),requiredWidth,requiredHeight);
                cvs.scale(mScaleFactor, mScaleFactor);
                cvs.drawBitmap(mImage2, imageDataModelList.get(1).getAmountX(), imageDataModelList.get(1).getAmountY(), null);

                mImage3=getResizedBitmap(imageDataModelList.get(2).getBitmap(),requiredWidth,requiredHeight);
                cvs.scale(mScaleFactor, mScaleFactor);
                cvs.drawBitmap(mImage3, imageDataModelList.get(2).getAmountX(), imageDataModelList.get(2).getAmountY(), null);
                break;
            case 4:
                mImage1=getResizedBitmap(imageDataModelList.get(0).getBitmap(),requiredWidth,requiredHeight);
                cvs.scale(mScaleFactor, mScaleFactor);
                cvs.drawBitmap(mImage1, imageDataModelList.get(0).getAmountX(), imageDataModelList.get(0).getAmountY(), null);

                mImage2=getResizedBitmap(imageDataModelList.get(1).getBitmap(),requiredWidth,requiredHeight);
                cvs.scale(mScaleFactor, mScaleFactor);
                cvs.drawBitmap(mImage2, imageDataModelList.get(1).getAmountX(), imageDataModelList.get(1).getAmountY(), null);

                mImage3=getResizedBitmap(imageDataModelList.get(2).getBitmap(),requiredWidth,requiredHeight);
                cvs.scale(mScaleFactor, mScaleFactor);
                cvs.drawBitmap(mImage3, imageDataModelList.get(2).getAmountX(), imageDataModelList.get(2).getAmountY(), null);

                mImage4=getResizedBitmap(imageDataModelList.get(3).getBitmap(),requiredWidth,requiredHeight);
                cvs.scale(mScaleFactor, mScaleFactor);
                cvs.drawBitmap(mImage4, imageDataModelList.get(3).getAmountX(), imageDataModelList.get(3).getAmountY(), null);
                break;
            case 5:
                mImage1=getResizedBitmap(imageDataModelList.get(0).getBitmap(),requiredWidth,requiredHeight);
                cvs.scale(mScaleFactor, mScaleFactor);
                cvs.drawBitmap(mImage1, imageDataModelList.get(0).getAmountX(), imageDataModelList.get(0).getAmountY(), null);

                mImage2=getResizedBitmap(imageDataModelList.get(1).getBitmap(),requiredWidth,requiredHeight);
                cvs.scale(mScaleFactor, mScaleFactor);
                cvs.drawBitmap(mImage2, imageDataModelList.get(1).getAmountX(), imageDataModelList.get(1).getAmountY(), null);

                mImage3=getResizedBitmap(imageDataModelList.get(2).getBitmap(),requiredWidth,requiredHeight);
                cvs.scale(mScaleFactor, mScaleFactor);
                cvs.drawBitmap(mImage3, imageDataModelList.get(2).getAmountX(), imageDataModelList.get(2).getAmountY(), null);

                mImage4=getResizedBitmap(imageDataModelList.get(3).getBitmap(),requiredWidth,requiredHeight);
                cvs.scale(mScaleFactor, mScaleFactor);
                cvs.drawBitmap(mImage4, imageDataModelList.get(3).getAmountX(), imageDataModelList.get(3).getAmountY(), null);

                mImage5=getResizedBitmap(imageDataModelList.get(4).getBitmap(),requiredWidth,requiredHeight);
                cvs.scale(mScaleFactor, mScaleFactor);
                cvs.drawBitmap(mImage5, imageDataModelList.get(4).getAmountX(), imageDataModelList.get(4).getAmountY(), null);
                break;
            case 6:
                break;
        }

        cvs.drawPath(penPath, penPaint);
        cvs.drawBitmap(bitmap, 0, 0, canvasPaint);
//        Constants.penPaint=penPaint;
//        Constants.penPath=penPath;
//        Constants.bitmap=bitmap;
//        Constants.canvasPaint=canvasPaint;
//        Constants.bitmapGlobal=bitmap;


//        cvs.save();
//        cvs.translate(ammountX, ammountY);
//        cvs.scale(mScaleFactor, mScaleFactor);
//        mImageNew.draw(cvs);
//        cvs.restore();
//        canvas.restore();
//        this.canvas.drawBitmap(bitmap,,rect,null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // Let the ScaleGestureDetector inspect all events.
        mScaleDetector.onTouchEvent(ev);

        final int action = ev.getAction();
        PointF touchPoint = new PointF();
       touchPoint.set(ev.getX(), ev.getY());
        switch (action & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN: {
                final float x = ev.getX();
                final float y = ev.getY();

                mLastTouchX = x;
                mLastTouchY = y;
                mActivePointerId = ev.getPointerId(0);
                handler.postDelayed(mLongPressed, 500);
                this.penPath.moveTo(touchPoint.x, touchPoint.y);
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                final int pointerIndex = ev.findPointerIndex(mActivePointerId);
                final float x = ev.getX(pointerIndex);
                final float y = ev.getY(pointerIndex);

                // Only move if the ScaleGestureDetector isn't processing a gesture.
                if (!mScaleDetector.isInProgress()) {
                    if((longPress==1)) {
//                    if((ammountX<ev.getX()) && (300f+ammountX)>ev.getX() && (ammountY<ev.getY()) && (300f+ammountY)>ev.getY()) {
//                        final float dx = x - mLastTouchX;
//                        final float dy = y - mLastTouchY;
//                        ammountX += dx;
//                        ammountY += dy;
//                        invalidate();
//                    }
                        if( (imageDataModelList.size()>=1) && imageDataModelList.get(0).getAmountX() <ev.getX() && (300f+imageDataModelList.get(0).getAmountX())>ev.getX() && (imageDataModelList.get(0).getAmountY()<ev.getY()) && (300f+imageDataModelList.get(0).getAmountY())>ev.getY()) {
                            final float dx = x - mLastTouchX;
                            final float dy = y - mLastTouchY;
                            imageDataModelList.get(0).setAmountX(imageDataModelList.get(0).getAmountX()+dx);
                            imageDataModelList.get(0).setAmountY(imageDataModelList.get(0).getAmountY()+dy);
                            invalidate();
                        }
                    else if((imageDataModelList.size()>=2) && imageDataModelList.get(1).getAmountX() <ev.getX() && (300f+imageDataModelList.get(1).getAmountX())>ev.getX() && (imageDataModelList.get(1).getAmountY()<ev.getY()) && (300f+imageDataModelList.get(1).getAmountY())>ev.getY())
                    {
                        final float dx = x - mLastTouchX;
                        final float dy = y - mLastTouchY;
                        imageDataModelList.get(1).setAmountX(imageDataModelList.get(1).getAmountX()+dx);
                        imageDataModelList.get(1).setAmountY(imageDataModelList.get(1).getAmountY()+dy);
                        invalidate();
                    }
                       else if((imageDataModelList.size()>=3) && imageDataModelList.get(2).getAmountX() <ev.getX() && (300f+imageDataModelList.get(2).getAmountX())>ev.getX() && (imageDataModelList.get(2).getAmountY()<ev.getY()) && (300f+imageDataModelList.get(2).getAmountY())>ev.getY())
                    {
                        final float dx = x - mLastTouchX;
                        final float dy = y - mLastTouchY;
                        imageDataModelList.get(2).setAmountX(imageDataModelList.get(2).getAmountX()+dx);
                        imageDataModelList.get(2).setAmountY(imageDataModelList.get(2).getAmountY()+dy);
                        invalidate();
                    }
                        else if((imageDataModelList.size()>=4) && imageDataModelList.get(3).getAmountX() <ev.getX() && (300f+imageDataModelList.get(3).getAmountX())>ev.getX() && (imageDataModelList.get(3).getAmountY()<ev.getY()) && (300f+imageDataModelList.get(3).getAmountY())>ev.getY())
                        {
                            final float dx = x - mLastTouchX;
                            final float dy = y - mLastTouchY;
                            imageDataModelList.get(3).setAmountX(imageDataModelList.get(3).getAmountX()+dx);
                            imageDataModelList.get(3).setAmountY(imageDataModelList.get(3).getAmountY()+dy);
                            invalidate();
                        }
                        else if((imageDataModelList.size()>=5) && imageDataModelList.get(4).getAmountX() <ev.getX() && (300f+imageDataModelList.get(4).getAmountX())>ev.getX() && (imageDataModelList.get(4).getAmountY()<ev.getY()) && (300f+imageDataModelList.get(4).getAmountY())>ev.getY())
                        {
                            final float dx = x - mLastTouchX;
                            final float dy = y - mLastTouchY;
                            imageDataModelList.get(4).setAmountX(imageDataModelList.get(4).getAmountX()+dx);
                            imageDataModelList.get(4).setAmountY(imageDataModelList.get(4).getAmountY()+dy);
                            invalidate();
                        }

                    }
                    else
                    {
                        this.penPath.lineTo(touchPoint.x, touchPoint.y);
                    }
                }
                mLastTouchX = x;
                mLastTouchY = y;
                a++;

                if(a>15) {
                    a=0;
                   handler.removeCallbacks(mLongPressed);
                }
                break;
            }

            case MotionEvent.ACTION_UP: {
                mActivePointerId = INVALID_POINTER_ID;
                longPress = 0;
                this.canvas.drawPath(penPath, penPaint);
                this.penPath.reset();
                a=0;
                handler.removeCallbacks(mLongPressed);
                break;
            }

            case MotionEvent.ACTION_CANCEL: {
                mActivePointerId = INVALID_POINTER_ID;
                break;
            }

            case MotionEvent.ACTION_POINTER_UP: {
                a=0;
                handler.removeCallbacks(mLongPressed);
                longPress = 0;
                this.canvas.drawPath(penPath, penPaint);
                this.penPath.reset();
                final int pointerIndex = (ev.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK)
                        >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
                final int pointerId = ev.getPointerId(pointerIndex);
                if (pointerId == mActivePointerId) {
                    // This was our active pointer going up. Choose a new
                    // active pointer and adjust accordingly.
                    final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
                    mLastTouchX = ev.getX(newPointerIndex);
                    mLastTouchY = ev.getY(newPointerIndex);
                    mActivePointerId = ev.getPointerId(newPointerIndex);
                }
                break;
            }
        }
        invalidate();
        return true;
    }




//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        // Let the ScaleGestureDetector inspect all events.
//        mScaleDetector.onTouchEvent(event);
//        PointF touchPoint = new PointF();
//        touchPoint.set(event.getX(), event.getY());
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                handler.postDelayed(mLongPressed, ViewConfiguration.getLongPressTimeout());
//                this.penPath.moveTo(touchPoint.x, touchPoint.y);
//                mActivePointerId = event.getPointerId(0);
//                break;
//            case MotionEvent.ACTION_MOVE:
//                handler.removeCallbacks(mLongPressed);
//                if (longPress == 1) {
//                    Log.d("dcjsidc", "1");
//                    Log.d("sjdisjdics","ammountX  : "+ammountX +" , event.getX() :  "+event.getX()+" ,  ammountX+300  : "+(300.00+ammountX)+ " , ammountY  : "+ammountY+" , event.getY() : "+event.getY());
//                    if((ammountX<event.getX()) && (300f+ammountX)>event.getX()
//                        && (ammountY<event.getY()) && (300f+ammountY>event.getY())) {
//                        Log.d("dcjsidc", "Switch inside long press");
//                        ammountX = event.getX() - startX;
//                        ammountY = event.getY() - startY;
//                        postInvalidate();
//                    }
//                }
//                else {
//                    Log.d("dcjsidc","0");
//                    this.penPath.lineTo(touchPoint.x, touchPoint.y);
//                }
//                break;
//
//            case MotionEvent.ACTION_UP:
//                handler.removeCallbacks(mLongPressed);
//                longPress = 0;
//                this.canvas.drawPath(penPath, penPaint);
//                this.penPath.reset();
//                break;
//
//            default:
//                return false;
//        }
//
//        invalidate();
//        return true;
//    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();
            // Don't let the object get too small or too large.
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f));
            invalidate();
            return true;
        }
    }

    public Bitmap getResizedBitmap(Bitmap bitmap, int reqWidth, int reqHeight)
    {
        Matrix matrix=new Matrix();
        RectF src=new RectF(0,0,bitmap.getWidth(),bitmap.getHeight());
        RectF req=new RectF(0,0,reqWidth,reqHeight);
        matrix.setRectToRect(src,req,Matrix.ScaleToFit.CENTER);

        return Bitmap.createBitmap(bitmap,0, 0, bitmap.getWidth(),bitmap.getHeight(),matrix,true);
    }

}


//                this.paintClient.emitPaint(Math.round(touchPoint.x), Math.round(touchPoint.y));
//                float x=event.getX();
//                float y=event.getY();
//                double dx=Math.pow(x-mImagex,2);
//                double dy=Math.pow(y-mImageY,2);
//                mImagex=x;
//                mImageY=y;
//                postInvalidate();

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//                PointF touchPoint = new PointF();
//        touchPoint.set(event.getX(), event.getY());
//        if(event.getAction() == MotionEvent.ACTION_DOWN) {
//            this.penPath.moveTo(touchPoint.x, touchPoint.y);
//            handler.postDelayed(mLongPressed, ViewConfiguration.getLongPressTimeout());
//            return true;
//        }
//        if((event.getAction() == MotionEvent.ACTION_MOVE)|| (event.getAction() == MotionEvent.ACTION_UP)) {
//            Log.d("xsclksc","call removed");
//            handler.removeCallbacks(mLongPressed);
//            if( event.getAction() == MotionEvent.ACTION_MOVE) {
////                if((longPress==1)) {
////                    ammountX = event.getX() - startX;
////                    ammountY = event.getY() - startY;
////                    postInvalidate();
////                    return true;
////                }
////                else
////                {
//                    Log.d("xsclksc","writting");
//                    this.penPath.lineTo(touchPoint.x, touchPoint.y);
//                    return true;
// //               }
//
//            }
//
//            return true;
//        }
//        return super.onTouchEvent(event);
//    }