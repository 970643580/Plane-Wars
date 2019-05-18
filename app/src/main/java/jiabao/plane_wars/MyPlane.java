package jiabao.plane_wars;



import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

public class MyPlane {
    private Bitmap bitmap;
    private Bitmap bitmapHp;
    private int x, y;
    private int width, height;
    private boolean noCollision;
    private int noCollisionCount;
    private int hp = 3;

    //方法重载
    public MyPlane(Bitmap bitmap, Bitmap bitmapHp) {
        this.bitmapHp = bitmapHp;
        this.bitmap = bitmap;
        x = MySurfaceView.width / 2 - bitmap.getWidth() / 2;
        y = MySurfaceView.height - bitmap.getHeight();
        width = bitmap.getWidth();
        height = bitmap.getHeight();
    }

    public void draw(Canvas canvas, Paint paint) {
        if (hp <= 0) {
            MySurfaceView.GAME_STATE = 2;
        }
        if (noCollision) {
            noCollisionCount++;
            if (noCollisionCount % 10 == 0) {
                canvas.drawBitmap(bitmap, x, y, paint);//飞机闪烁
            }
            if (noCollisionCount > 100) {
                noCollision = false;
                noCollisionCount = 0;
            }
        } else {
            //非无敌状态
            canvas.drawBitmap(bitmap, x, y, paint);
        }
        for (int i = 0; i < hp; i++) {
            canvas.drawBitmap(bitmapHp, i * bitmapHp.getWidth(), MySurfaceView.height - bitmapHp.getHeight(), paint);

        }


    }

    //实现飞机的拖动
    public void touchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            float ex = event.getX();
            float ey = event.getY();
            if (ex > x && ex < x + width && ey > y && ey < y + height) {
                x = (int) ex - width / 2;
                y = (int) ey - height / 2;
                if (y < 0) {
                    y = 0;
                }
                if (y + height > MySurfaceView.height) {
                    y = MySurfaceView.height - height;
                }
            }
        }
    }

    //判断飞机与子弹的碰撞
    public boolean isCollision(Bullet bullet) {
        if (noCollision) {
            return false;
        } else {
            if (bullet.getX() > x && bullet.getX() < x + width && bullet.getY() > y && bullet.getY() < y + height) {
                noCollision = true;
                if (hp > 0) {
                    hp--;
                }
                return true;
            }
        }
        return false;
    }

    //判断飞机和BOSS的碰撞
    boolean isCollision(BossPlane bossPlan) {
        if (hp < 0) {
            MySurfaceView.GAME_STATE = 2;
        }
        if (noCollision) {
            return false;
        } else {
            if (bossPlan.getY() + bossPlan.getFrameH() > y && bossPlan.getY() + bossPlan.getFrameH() < y + height) {
                if (x < bossPlan.getX() && x + width > bossPlan.getX()) {
                    noCollision = true;
                    if (hp >= 0) {
                        hp--;
                    }
                    return true;
                }
                if (x > bossPlan.getX() && x + width < bossPlan.getX() + bossPlan.getFrameW()) {
                    noCollision = true;
                    if (hp > 0) {
                        hp--;
                    }
                    return true;
                }
                if (x > bossPlan.getX() && x < bossPlan.getX() + bossPlan.getFrameW()) {
                    noCollision = true;
                    if (hp > 0) {
                        hp--;
                    }
                    return true;
                }
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
