package edu.uco.ralhamami.program8;

import java.util.Random;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.frame);
		final BubbleView bubbleView = new BubbleView(getApplicationContext(),
				BitmapFactory.decodeResource(getResources(), R.drawable.circ1),
				BitmapFactory.decodeResource(getResources(), R.drawable.circ2),
				BitmapFactory.decodeResource(getResources(), R.drawable.square2),
				BitmapFactory.decodeResource(getResources(), R.drawable.square1));
		relativeLayout.addView(bubbleView);
		setContentView(relativeLayout);
	}

	private class BubbleView extends SurfaceView implements SurfaceHolder.Callback {
		private final Bitmap mBitmap, mBitmap2,mBitmap3,mBitmap4;
		private final int dim1=60, dim2=75, dim3=35, dim4=50;
		private final DisplayMetrics mDisplay;
		private final int mDisplayWidth, mDisplayHeight;
		private float mX, mY, mX2, mY2, mX3, mY3, mX4, mY4;
		private final SurfaceHolder mSurfaceHolder;
		private final Paint mPainter = new Paint();
		private Thread mDrawingThread;
		Random r = new Random();

		private static final int MOVE_STEPY = 5;
		private static final int MOVE_STEPX = 3;

		private int dY, dY2, dY3, dY4;
		private int dX, dX2, dX3, dX4;

		public BubbleView(Context context, Bitmap bitmap, Bitmap bitmap2,Bitmap bitmap3,Bitmap bitmap4) {
			super(context);
			this.mBitmap = Bitmap.createScaledBitmap(bitmap,
					dim1, dim1, false);
			this.mBitmap2 = Bitmap.createScaledBitmap(bitmap2,
					dim2, dim2, false);
			this.mBitmap3 = Bitmap.createScaledBitmap(bitmap3,
-					dim3, dim3, false);
			this.mBitmap4 = Bitmap.createScaledBitmap(bitmap4,
					dim4, dim4, false);

			mDisplay = new DisplayMetrics();
			MainActivity.this.getWindowManager().getDefaultDisplay().getMetrics(mDisplay);
			mDisplayWidth = mDisplay.widthPixels;
			mDisplayHeight = 688;

			mX = r.nextInt(((mDisplayWidth-dim1) - dim1) + 1) + dim1;
			mY = r.nextInt(((mDisplayHeight-dim1) - dim1) + 1) + dim1;
			mX2 = r.nextInt(((mDisplayWidth-dim2) - dim2) + 1) + dim2;
			mY2 = r.nextInt(((mDisplayHeight-dim2) - dim2) + 1) + dim2;
			mX3 = r.nextInt(((mDisplayWidth-dim3) - dim3) + 1) + dim3;
			mY3 = r.nextInt(((mDisplayHeight-dim3) - dim3) + 1) + dim3;
			mX4 = r.nextInt(((mDisplayWidth-dim4) - dim4) + 1) + dim4;
			mY4 = r.nextInt(((mDisplayHeight-dim4) - dim4) + 1) + dim4;
			dY=dY2=dY3=dY4=MOVE_STEPY;
			dX=dX2=dX3=dX4=MOVE_STEPX;

			mPainter.setAntiAlias(true);
			mSurfaceHolder = getHolder();
			mSurfaceHolder.addCallback(this);
		}

		private void drawBubble(Canvas canvas) {
			canvas.drawColor(Color.BLACK);
			canvas.drawBitmap(mBitmap, mX, mY, mPainter);
			canvas.drawBitmap(mBitmap2, mX2, mY2, mPainter);
			canvas.drawBitmap(mBitmap3, mX3, mY3, mPainter);
			canvas.drawBitmap(mBitmap4, mX4, mY4, mPainter);
		}

		private void move() {
			if (mY + dim1 >= mDisplayHeight || mY <= 0) {
				dY = -dY;
			}
			mY += dY;
			
			if (mX + dim1 >= mDisplayWidth || mX <= 0) {
				dX = -dX;
			}
			mX += dX;
			
			if (mY2 + dim2 >= mDisplayHeight || mY2 <= 0) {
				dY2 = -dY2;
			}
			mY2 += -dY2;
			
			if (mX2 + dim2 >= mDisplayWidth || mX2 <= 0) {
				dX2 = -dX2;
			}
			mX2 += dX2;
			

			if (mY3 + dim3 >= mDisplayHeight || mY3 <= 0) {
				dY3 = -dY3;
			}
			mY3 += -dY3;
			
			if (mX3 + dim3 >= mDisplayWidth || mX3 <= 0) {
				dX3 = -dX3;
			}
			mX3 += -dX3;
			
			if (mY4 + dim4 >= mDisplayHeight || mY4 <= 0) {
				dY4 = -dY4;
			}
			mY4 += dY4;
			
			if (mX4 + dim4 >= mDisplayWidth || mX4 <= 0) {
				dX4 = -dX4;
			}
			mX4 += -dX4;
		}

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
		}

		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			mDrawingThread = new Thread(new Runnable() {
				public void run() {
					Canvas canvas = null;
					while (!Thread.currentThread().isInterrupted()) {
						move();
						canvas = mSurfaceHolder.lockCanvas();
						if (null != canvas) {
							drawBubble(canvas);
							mSurfaceHolder.unlockCanvasAndPost(canvas);
						}
					}
				}
			});
			mDrawingThread.start();
		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			if (null != mDrawingThread)
				mDrawingThread.interrupt();
		}
	}
}