package jiabao.plane_wars;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class BlackGround {
    private Bitmap bitmap;
    private int y1,y2;


    public BlackGround(Bitmap bitmap) {
        this.bitmap = bitmap;

        y2 = y1 -bitmap.getHeight();
    }



    public void draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(bitmap,0,y1,paint);
        canvas.drawBitmap(bitmap,0,y2,paint);
        logic();
    }

    public void logic(){
        y1 +=10;
        y2 +=10;
        if(y1>MySurfaceView.height){
            y1 = y2 -bitmap.getHeight();
        }
        if (y2>MySurfaceView.height){
            y2 = y1 - bitmap.getHeight();
        }
    }
}
