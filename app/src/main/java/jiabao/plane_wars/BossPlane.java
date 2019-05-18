package jiabao.plane_wars;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class BossPlane {
    private Bitmap bitmap;
    private int x,y;
    private int width, height;
    private int frameW,frameH;
    private int speed = 4;
    private int crazySpeed =50;
    private int count;//计数器
    private int time = 50;//疯狂时间间隔
    private boolean isCrazy;
    private boolean noCollision;
    private int noCollisionCount;


    public BossPlane(Bitmap bitmap) {
        this.bitmap = bitmap;
        this.frameW = bitmap.getWidth()/10;
        this.frameH = bitmap.getHeight();
    }

    public void draw(Canvas canvas, Paint paint){
        canvas.save();//先保存
        canvas.clipRect(x,y,x+frameW,y+frameH);//对Boss飞机进行裁剪
        canvas.drawBitmap(bitmap,x,y,paint);
        canvas.restore();//后释放
        logic();
    }

    public void logic() {
        count++;
        if (isCrazy) {
            //疯狂模式
            y = y + crazySpeed;
            crazySpeed--;
            if (y == 0) {
                isCrazy = false;
                crazySpeed = 50;
            }

        } else {
            if (y > MySurfaceView.height - frameH) {
                crazySpeed = -crazySpeed;
            }

            if (count % time == 0) {
                isCrazy = true;
            }
            x = x + speed;
            if (x > MySurfaceView.width - frameW) {
                speed = -speed;
            }
            if (x < 0) {
                speed = -speed;
            }
        }
    }

    public boolean isCollision(Bullet bullet) {
        if (noCollision) {
            return false;
        } else {
            if (bullet.getX() > x && bullet.getX() < x + width && bullet.getY() > y && bullet.getY() < y + height) {
                noCollision = true;
            }
        }
        return false;
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

    public int getFrameW() {
        return frameW;
    }

    public int getFrameH() {
        return frameH;
    }
}
