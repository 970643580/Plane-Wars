package jiabao.plane_wars;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
public class BackGround {
    private int x;
    private int y;
    private Bitmap bitmap;

    public BackGround(Bitmap bitmap){
        this.bitmap = bitmap;
        x = 0;
        y = x-bitmap.getHeight();
    }

    public void draw(Canvas canvas, Paint paint){
        logic();
        canvas.drawBitmap(bitmap,0,x,paint);
        canvas.drawBitmap(bitmap,0,y,paint);
    }

    public void logic(){
        x+=10;
        y+=10;
        if(x>=bitmap.getHeight()){
            x = y-bitmap.getHeight();
        }
        if(y>=bitmap.getHeight()){
            y=x-bitmap.getHeight();
        }
    }

}
