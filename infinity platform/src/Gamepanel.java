import javax.swing.*;
import java.awt.*;
import java.util.*;

// Ana oyun paneli
public class Gamepanel extends JPanel implements Runnable {

    Thread gameThread;       // Oyun döngüsü
    int FPS = 144;           // FPS
    int x = 100, y = 100;    // Kare pozisyonu
    double speedx = 5;       // X ekseni hızı
    double speedy = 0;       // Y ekseni hızı
    double g = 0.1;          // Gravity
boolean infinity = false;
public boolean neartouchingright = false;
public boolean neartouchingfalse = false;
public double color;
public int x2 = -9999;
  public  int difference = 0;
  public int worldx = 0;
  public int devir = 0;

    public Ground gr = new Ground(0, 0, 800, 30, Color.BLACK);
// Zemin nesnesi
    public KeyHandler kh = new KeyHandler();
    public Cube cube = new Cube(speedx,speedy,gr,g,kh,this);
    public Random rr = new Random();
    public Scanner sr = new Scanner(System.in);
  public  Cubes cubes[];




    public Gamepanel() {
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(800, 600));
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(kh);
        this.setFocusable(true);
        int a = sr.nextInt();
        cubes = new  Cubes[a];

cube.x = 150;
cube.y = 100;
        for (int i = 0; i < cubes.length; i++) {
cubes[i] = new Cubes(speedy,gr,g,kh,x,y);
            cubes[i].x =rr.nextInt(-12,54)*15;
            System.out.println(cubes[i].x );
            cubes[i].y =rr.nextInt(0,10)*15;

        }


     // zemin nesnesi

    }

    public void startThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000.0 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            update();          // Kare pozisyonunu güncelle
            repaint();         // Paneli yeniden çiz

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                if (remainingTime < 0) remainingTime = 0;
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
   if(infinity == false) {




       cube.movement();
       ControlGame();
       cube.controlcube();
       for (int i = 0; i < cubes.length; i++) {
           cubes[i].controlcube();


       }
       upandnear();
       touch();
       difference();
       setDevir();

   }




    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Zemin panel altına, resize uyumlu
        gr.y = getHeight() - gr.height;
        gr.width = getWidth();

        // Zemin çizimi
        g2.setColor(gr.color);
        g2.fillRect(gr.x, gr.y, gr.width, gr.height);

        // Kare çizimi
        g2.setColor(Color.BLACK);
        for (int i = 0; i < cubes.length; i++) {

            g2.setColor(cubes[i].color);

            if(kh.right&&!neartouchingright) {
                cubes[i].x-=speedx;
            }if(kh.left&&!neartouchingfalse) {
                cubes[i].x+=speedx;
            }


    g2.fillRect(cubes[i].x, cubes[i].y, 15, 15);


        }

g2.setColor(cube.color);
        g2.fillRect(cube.x, cube.y, 15,15);
        g2.drawString("x=" + (devir*devir/10000)/2,10,20);
    }

    public void ControlGame() {
      //  x += speedx;
        if (x > getWidth() - 15 || x < 0) speedx = -speedx;

        // Y ekseninde gravity
        if (y + 15 < gr.y) {       // Kare zemine çarpmamışsa
            speedy += g;
            y += speedy;
        } else {                    // Kare zemine çarptı
            y = gr.y - 15;
            speedy = 0;
        }
    }
    public void touch() {
neartouchingfalse = false;
neartouchingright = false;
        if(!(y>cube.y+15||y<cube.y-15)&&(x==cube.x-15||x==cube.x+15))
            speedx = -speedx;

        if((x + 15 > cube.x && x < cube.x + 15 && y + 15 > cube.y && y < cube.y + 15))
            cube.speedy = 0;
        for (int i = 0; i < cubes.length; i++) {


            if (!(cubes[i].y > cube.y + 15 || cubes[i].y < cube.y - 15) && (cubes[i].x == cube.x - 15 )) {
                neartouchingfalse = true;
            }
            if (!(cubes[i].y > cube.y + 15 || cubes[i].y < cube.y - 15)  &&(cubes[i].x == cube.x + 15 )) {
                neartouchingright = true;
            }





            if ((cubes[i].x + 15 > cube.x && cubes[i].x < cube.x + 15 && cubes[i].y + 15 > cube.y && cubes[i].y < cube.y + 15)) {
                cube.speedy = 0;
               cube.color= cubes[i].color;



                color = cubes[i].color.getBlue() * cubes[i].color.getRed() * cubes[i].color.getGreen();


            }
        }


    }
    public void upandnear() {
        for (int i = 0; i < cubes.length; i++) {
            for (int j = 0; j < cubes.length; j++) {
                if(!(i==j)) {
                    if ((cubes[i].y + 15 >= cubes[j].y && cubes[j].y + 15 > cubes[i].y)&&(cubes[i].x<cubes[j].x+15&&cubes[i].x+15>cubes[j].x)) {
                        cubes[i].y = cubes[j].y - 15;
                        cubes[i].speedy = 0;
                        cubes[i].isuptouching = true;
                        cubes[j].isdowntouching = true;
                        if(!((cubes[i].color.getRed()/1.6)>cubes[j].color.getRed())) {
                            if(cubes[j].color.getRed()>1) {

                                int r = cubes[j].color.getRed()-1;
                                cubes[j].color = new Color(r,cubes[j].color.getGreen(),cubes[j].color.getBlue());
                            }
                        } if(!((cubes[i].color.getBlue()/1.6)>cubes[j].color.getBlue())) {
                            if(cubes[j].color.getBlue()>1) {
                                int r = cubes[j].color.getBlue()-1;
                                cubes[j].color = new Color(cubes[j].color.getRed(),cubes[j].color.getGreen(),r);
                            }
                        } if(!((cubes[i].color.getGreen()/1.6)>cubes[j].color.getGreen())) {
                            if(cubes[j].color.getGreen()>1) {
                                int r = cubes[j].color.getGreen()-1;
                                cubes[j].color = new Color(cubes[j].color.getRed(),r,cubes[j].color.getBlue());
                            }
                        }

                    }
                }
            }



        }


    }
    public void difference() {
difference=0;
        int x1 = cube.x;

        if (x2 == -9999)
            x2 = x1;

        if (!(x1 == x2)) {
            System.out.println(x1-x2);
            difference = x1 - x2;
            x2=x1;

        }

    }
    public void setDevir() {
        for (int i = 0; i < cubes.length; i++) {
            if(cubes[i].x<-210) {
                cubes[i].x = (54)*15;
devir++;
                cubes[i].y = rr.nextInt(0,10)*15;
                if(rr.nextInt(0,4)<1) {
                    cubes[i].color = new Color(rr.nextInt(0, 255), rr.nextInt(0, 255), rr.nextInt(0, 255));
                    System.out.println("çalıştı");
                }
            }
        }
    }






    // Ana method
    public static void main(String[] args) {
        JFrame frame = new JFrame("Gravity & Ground Test");
        Gamepanel panel = new Gamepanel();
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        panel.startThread();

    }
}
