package jiabao.plane_wars;


import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Vector;
//多态、接口、继承应用
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    //封装
    private SurfaceHolder surfaceHolder;
    private Canvas canvas;//绘制图形
    private Boolean a = true;//标志位
    public static int height;
    public static int width;
    private MyPlane plan;
    //Vector是线程安全的，ArrayList是非线性安全的
    private Vector<Bullet> bulletVector = new Vector<>();//玩家子弹数组
    private Vector<Boom> boomVector = new Vector<>();//Boss子弹数组
    private int count;
    private GameSoundPool gameSoundPool;
    public static int GAME_STATE = 0;


    public MySurfaceView(Context context) {
        super(context);
        gameSoundPool = new GameSoundPool(context);
        init();
    }

    private void init() {
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);//添加回调时间监听
        setFocusable(true);//设置可聚焦
        setKeepScreenOn(true);//设置屏幕常亮
        setFocusableInTouchMode(true);//设置触摸模式
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        new Thread(this).start();//启动子线程
        height = getHeight();
        width = getWidth();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        a = false;
    }

    @Override
    public void run() {
        Paint paint = new Paint();
        BackGround backGround = new BackGround(BitmapFactory.decodeResource(getResources(), R.mipmap.background));
        plan = new MyPlane(BitmapFactory.decodeResource(getResources(), R.mipmap.myplane), BitmapFactory.decodeResource(getResources(), R.mipmap.hp));
        BossPlane bossPlane = new BossPlane(BitmapFactory.decodeResource(getResources(), R.mipmap.bossplane));

        while (a) {
            count++;
            try {
                canvas = surfaceHolder.lockCanvas();//锁定画布
                canvas.drawColor(Color.WHITE);


                switch (GAME_STATE) {
                    case 0:

                        backGround.draw(canvas, paint);
                        plan.draw(canvas, paint);
                        bossPlane.draw(canvas, paint);
                        //打印我方子弹
                        if (count % 5 == 0) {
                            gameSoundPool.playSound(1);
                            Bullet bullet = new Bullet(BitmapFactory.decodeResource(getResources(), R.mipmap.mybullet), plan.getX(), plan.getY(), 0);
                            Bullet bullet1 = new Bullet(BitmapFactory.decodeResource(getResources(), R.mipmap.mybullet), plan.getX() + plan.getWidth(), plan.getY(), 0);
                            bulletVector.add(bullet);
                            bulletVector.add(bullet1);
                        }
                        for (int i = 0; i < bulletVector.size(); i++) {
                            if (bulletVector.elementAt(i).isDead()) {
                                bulletVector.remove(i);//控制子弹数量
                            }

                        }
                        for (int i = 0; i < bulletVector.size(); i++) {
                            bulletVector.elementAt(i).draw(canvas, paint);//添加子弹
                            if (bossPlane.isCollision(bulletVector.elementAt(i))) {
                                gameSoundPool.playSound(2);
                                Log.e("MySurfaceView", "run: 00000000000000000000000000000" );
                                Boom boom = new Boom(BitmapFactory.decodeResource(getResources(), R.mipmap.boom), bossPlane.getX(), bossPlane.getY(), 7);
                                boomVector.add(boom);
                            }

                        }
                        for (int i = 0; i < boomVector.size(); i++) {
                            boomVector.elementAt(i).draw(canvas, paint);

                            if (boomVector.elementAt(i).isEnd()) {

                                boomVector.remove(i);
                            } else {

                            }
                        }
                        //打印boos子弹
                        if (count % 50 == 0) {
                            Bullet bullet = new Bullet(BitmapFactory.decodeResource(getResources(), R.mipmap.bossbullet), bossPlane.getX(), bossPlane.getY() + bossPlane.getFrameH(), 1);
                            Bullet bullet1 = new Bullet(BitmapFactory.decodeResource(getResources(), R.mipmap.bossbullet), bossPlane.getX() + bossPlane.getFrameH(), bossPlane.getY() + bossPlane.getFrameH(), 1);
                            bulletVector.add(bullet);
                            bulletVector.add(bullet1);

                        }
                        //移除无效子弹
                        for (int i = 0; i < bulletVector.size(); i++) {
                            if (bulletVector.elementAt(i).isDead()) {
                                bulletVector.remove(i);
                            }

                        }
                        for (int i = 0; i < bulletVector.size(); i++) {
                            bulletVector.elementAt(i).draw(canvas, paint);//添加子弹
                            plan.isCollision(bulletVector.elementAt(i));//调用飞机撞子弹
                        }
                        plan.isCollision(bossPlane);//调用飞机撞到Boss
                        break;
                    case 1:
                        RectF rectF1 = new RectF(0, 0, getWidth(), getHeight());
                        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.gamewin), null, rectF1, paint);
                        break;
                    case 2:
                        RectF rectF = new RectF(0, 0, getWidth(), getHeight());
                        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.gamelost), null, rectF, paint);
                        break;
                    case 3:
                        //Welcome welcome = new Welcome(BitmapFactory.decodeResource(getResources(),R.mipmap.mainmenu));

                        break;
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);//解锁画布
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        plan.touchEvent(event);

        return true;//永远监听屏幕触摸事件
    }
}


