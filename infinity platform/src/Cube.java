import java.awt.*;

public class Cube {

    int x, y;
    double speedy;
    Ground gr;
    double g;
    KeyHandler kh;
    double speedx;
    Gamepanel gp;
    Color color;

public double jump;
    public Cube(double speedx, double speedy, Ground gr, double g, KeyHandler kh, Gamepanel gp) {
        this.x = x;
        this.y = y;
        this.g = g;
        this.speedy = speedy;
        this.gr = gr;
        this.kh = kh;
        this.speedx = speedx;
        this.gp = gp;

    }

    public void controlcube() {
        if (y + 15 < gr.y) {       // Kare zemine çarpmamışsa
            speedy += g;
            y += speedy;
        }
        if (y + 15 >= gr.y) {                    // Kare zemine çarptı
            y = gr.y - 15;
            speedy = 0;
        }
    }

    public void movement() {

        if ((speedy==0)) {
            if (kh.up) {
y -=0.1;

                gp.color = Math.sqrt(gp.color);
                gp.color = Math.sqrt(gp.color);
                gp.color = Math.sqrt(gp.color);


                System.out.println(gp.color);
                jump = gp.color/10;
                speedy = -4*jump*2.4;
                System.out.println(speedy);


            }
        }
        if ((speedy == 0)) {
            if (kh.up) {
                y -=0.1;


                speedy -=4;
                System.out.println(speedy);


            }
        }

            if (kh.right) {
//if(!gp.neartouchingright)
    //x += speedx;
            }
            if (kh.left) {
               // if(!gp.neartouchingfalse)
                    //x -= speedx;
            }


        }


    }

