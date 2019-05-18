package jiabao.plane_wars;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Bullet {
    private Bitmap bitmap;
    private int x, y;
    private boolean isDead;
    private int speed = 20;
    private int type;

    public Bullet(Bitmap bitmap, int x, int y, int type) {
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(bitmap, x, y, paint);
        logic();
    }

    public void logic() {
        switch (type) {
            //玩家子弹
            case 0:
                y -= speed;
                if (y < 0) {
                    isDead = true;
                }
                break;
            //boss子弹
            case 1:
                y += speed;
                if (y < 0) {
                    isDead = true;
                }
                break;
        }

    }

    public boolean isDead() {
        return isDead;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}