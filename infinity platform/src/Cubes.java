import java.awt.*;
import java.util.Random;

public class Cubes  {


        int x,y;
        double worldx;
        double speedy;
        Ground gr;
        double g;
        KeyHandler kh;
        Color color;
    public boolean isuptouching = false;
    public boolean isdowntouching = false;
Random rr = new Random();

        public Cubes(double speedy,Ground gr,double g,KeyHandler kh,int x,int y) {
            this.x = x;
            this.y = y;
            this.g = g;
            this.speedy = speedy;
            this.gr = gr;
            this.kh = kh;
            this.color = new Color(rr.nextInt(0,255),rr.nextInt(0,255),rr.nextInt(0,255));

        }
        public void controlcube() {
            if (y + 15 < gr.y) {       // Kare zemine çarpmamışsa
                speedy += g;
                y += speedy;
            } else {                    // Kare zemine çarptı
                y = gr.y - 15;
                speedy = 0;
            }
        }




        }












