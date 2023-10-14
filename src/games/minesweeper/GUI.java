package games.minesweeper;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.*;
import java.util.Date;
import java.util.Random;

public class GUI extends JFrame{
    public boolean resetter = false;
    public boolean flag = false;

    Date startDate = new Date();
    Date endDate;
    int spacing = 5;
    static int neighs = 0;
    String gameMes;
    int gameMesX = 700;
    int gameMesY = -50;
    public int mx = -100;
    public int my = -100;

    public int smileyX = 605;
    public int smileyY = 5;
    public int smileyCenterX = smileyX + 35;
    public int smileyCenterY = smileyY + 35;

    public int flagX = 445;
    public int flagY = 5;
    public int flagCenterX = flagX + 35;
    public int flagCenterY = flagY + 35;

    public int spacingX = 90;
    public int spacingY = 10;
    public int minusX = spacingX + 160;
    public int minusY = spacingY;
    public int plusX = spacingX + 240;
    public int plusY = spacingY;

    public int timeCountX = 1130;
    public int timeCountY = 5;
    public int sec = 0;

    public boolean happy = true;
    public boolean victory = false;
    public boolean defeat = false;

    static Random rand = new Random();
    static int[][] mines = new int[16][7];
    static int[][] neighbours = new int[16][7];
    static boolean [][] revealed = new boolean[16][7];
    static boolean [][] flagged = new boolean[16][7];

    public GUI() {
        this.setTitle("Mine Sweeper");
        this.setSize(1286, 829);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        // We can change this to true
        this.setResizable(false);
        // This line of code is used for resizing the dimensions of the app
        //this.setResizable(); if true user will be able to change its dimension if false then it is fixed

        boardGenerator();

        Board board = new Board();
        this.setContentPane(board);

        Move move = new Move();
        this.addMouseMotionListener(move);

        Click click = new Click();
        this.addMouseListener(click);
    }
    public class Board extends JPanel{
        public void paintComponent(Graphics g){
            g.setColor(Color.darkGray);
            g.fillRect(0, 0, 1280, 800);
            //FIXME change the board to 16 X 6
            for(int i = 0; i < 16; i++){
                for(int j =0; j < 7; j++){
                    g.setColor(Color.gray);

//                    Reveals mines emplacement
//                    if(mines[i][j] == 1){
//                        g.setColor(Color.yellow);
//                    }

                    if(revealed[i][j]){
                        g.setColor(Color.white);
                        if(mines[i][j] == 1){
                            g.setColor(Color.red);
                        }
                    }

                    if(mx >= spacing + i * 80 && mx < spacing + i * 80 + 80 - 2 * spacing &&
                            my >= spacing + j * 80 + 80 + 26 && my < spacing + j * 80 + 80 + 80 + 26 - 2 * spacing){

                        g.setColor(Color.lightGray);
                    }
                    g.fillRect(spacing + i * 80,spacing + j * 80 + 80,80-2*spacing, 80-2*spacing);
                    if(revealed[i][j]){
                        g.setColor(Color.black);
                        if(mines[i][j] == 0 && neighbours[i][j] != 0) {
                            if(neighbours[i][j] == 1){
                                g.setColor(Color.blue);
                            } else if (neighbours[i][j] == 2) {
                                g.setColor(Color.green);
                            } else if (neighbours[i][j] == 3) {
                                g.setColor(Color.red);
                            } else if (neighbours[i][j] == 4) {
                                g.setColor(new Color(0,0,128));
                            } else if (neighbours[i][j] == 5) {
                                g.setColor(new Color(178, 34,34));
                            } else if (neighbours[i][j] == 6) {
                                g.setColor(new Color(72,209,204));
                            }  else if (neighbours[i][j] == 8) {
                                g.setColor(Color.darkGray);
                            }
                            g.setFont(new Font("Comic Sans", Font.BOLD, 40));
                            g.drawString(Integer.toString(neighbours[i][j]), i * 80 + 30, j * 80 + 80 + 55);
                        } else if(mines[i][j] == 1){
                            g.fillRect(  i * 80 + 10 + 20, j * 80 + 80 + 20,20, 40);
                            g.fillRect( i * 80 + 20, j * 80 + 80 + 10 + 20,40, 20);
                            g.fillRect( i * 80 + 5 + 20, j * 80 + 80 + 5 + 20,30, 30);
                            g.fillRect(i * 80 + 38, j * 80 + 80 + 15,4, 50);
                            g.fillRect(i * 80 + 15, j * 80 + 80 + 38,50, 4);
                        }
                    }
                    //flag painting
                    if(flagged[i][j]){
                        g.setColor(Color.black);
                        g.fillRect(i * 80 + 32 + 5,j * 80 + 80 + 15 + 5,5,40);
                        g.fillRect(i * 80 + 20 + 5,j * 80 + 80 + 50 + 5,30,10);
                        g.setColor(Color.red);
                        g.fillRect(i * 80 + 16 + 5,j * 80 + 80 + 15 + 5,20,15);
                        g.setColor(Color.black);
                        g.drawRect(i * 80 + 16 + 5, j * 80 + 80 + 15 + 5, 20, 15);
                        g.drawRect(i * 80 + 17 + 5, j * 80 + 80 + 16 + 5, 18, 13);
                        g.drawRect(i * 80 + 18 + 5, j * 80 + 80 + 17 + 5, 16, 11);
                    }
                }
            }

            // spacing plus - minus painting
            g.setColor(Color.black);
            g.fillRect(spacingX, spacingY, 300,60);

            g.setColor(Color.white);
            g.fillRect(minusX + 5, minusY + 10, 40,40);
            g.fillRect(plusX + 5, plusY + 10, 40,40);

            g.setFont(new Font("Comic Sans", Font.PLAIN, 35));
            g.drawString("Spacing", spacingX + 20,spacingY + 40);

            g.setColor(Color.black);
            g.fillRect(minusX + 15,minusY + 27,20,6);

            g.fillRect(plusX + 15,plusY + 27,20,6);
            g.fillRect(plusX + 22,plusY + 20,6,20);

            g.setColor(Color.white);
            g.setFont(new Font("Comic Sans", Font.PLAIN,30));
            if(spacing < 10){
                g.drawString("0"+(spacing),minusX +49, minusY + 40);
            }else{
                g.drawString(Integer.toString(spacing),minusX +49, minusY + 40);
            }


            // Smile painting
            g.setColor(Color.yellow);
            g.fillOval(smileyX,smileyY,70,70);
            g.setColor(Color.black);
            g.fillOval(smileyX + 15, smileyY + 20,10,10);
            g.fillOval(smileyX + 45, smileyY + 20, 10,10);
            if(happy){
                g.fillRect(smileyX + 20, smileyY + 50, 30, 5);
                g.fillRect(smileyX + 17, smileyY + 45, 5, 5);
                g.fillRect(smileyX + 48, smileyY + 45, 5, 5);
            } else{
                g.fillRect(smileyX + 20, smileyY +45, 30, 5);
                g.fillRect(smileyX + 17, smileyY + 50, 5, 5);
                g.fillRect(smileyX + 48, smileyY + 50, 5, 5);
            }

            //flag painting
            g.setColor(Color.black);
            g.fillRect(flagX + 32,flagY + 15,5,40);
            g.fillRect(flagX + 20,flagY + 50,30,6);
            g.setColor(Color.red);
            g.fillRect(flagX + 16,flagY + 15,20,15);
            g.setColor(Color.black);
            g.drawRect(flagX + 16, flagY + 15, 20, 15);
            g.drawRect(flagX + 17, flagY + 16, 18, 13);
            g.drawRect(flagX + 18, flagY + 17, 16, 11);

            if(flag){
                g.setColor(Color.red);
            }
            g.drawOval(flagX,flagY,70,70);
            g.drawOval(flagX + 1,flagY + 1,68,68);
            g.drawOval(flagX + 2,flagY + 2,66,66);

            // Time counter painting
            g.setColor(Color.black);
            g.fillRect(timeCountX,timeCountY, 140, 70);
            if(!defeat && !victory) {
                sec = (int) ((new Date().getTime() - startDate.getTime()) / 1000);
            }
            if( sec > 999){
                sec = 999;
            }
            g.setColor(Color.white);
            if(victory){
                g.setColor(Color.green);
            } else if (defeat) {
                g.setColor(Color.red);
            }
            g.setFont(new Font("Comic Sans", Font.PLAIN, 80));
            if(sec < 10){
                g.drawString("00" + (sec), timeCountX,timeCountY + 65);
            }else if( sec < 100){
                g.drawString("0" + (sec), timeCountX,timeCountY + 65);
            }else{
                g.drawString(Integer.toString(sec), timeCountX,timeCountY + 65);
            }

            //victory message painting
            if(victory){
                g.setColor(Color.green);
                gameMes = "YOU WIN!";
            } else if (defeat) {
                g.setColor(Color.red);
                gameMes = "YOU LOSE!";
            }
            if(victory || defeat){
                gameMesY = -50 + (int) (new Date().getTime() - endDate.getTime()) / 10;
                if(gameMesY > 67){
                    gameMesY = 67;
                }
                g.setFont(new Font("Comic Sans", Font.PLAIN, 70));
                g.drawString(gameMes, gameMesX, gameMesY);
            }
        }
    }

    public class Move implements MouseMotionListener{

        @Override
        public void mouseDragged(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            mx = e.getX();
            my = e.getY();
//            System.out.println("The mouse was moved!");
//
//            System.out.println("x:" + mx +"\ny:" + my);
        }
    }
    public class Click implements mouseClicker, MouseListener {

        @Override
        public void mouseClicked(MouseEvent e){
            mx = e.getX();
            my = e.getY();
            int boxX = inBoxX();
            int boxY = inBoxY();

            if(mx >= minusX + 20 && mx < minusX + 60 && my >= minusY + 20 && my < minusY + 60){
                spacing --;
                if(spacing < 1){
                    spacing = 1;
                }
            } else if (mx >= plusX + 20 && mx < plusX + 60 && my >= plusY + 20 && my < plusY + 60) {
                spacing ++;
                if(spacing > 15){
                    spacing = 15;
                }
            }

            if(boxX != -1 && boxY != -1){
                System.out.println("The mouse was clicked in box [" + boxX +" , " + boxY+"], Number of mine neighs: " + neighbours[boxX][boxY]);
                System.out.println("x:" + mx +"\ny:" + my);
                if(flag && !revealed[boxX][boxY]) {
                    flagged[boxX][boxY] = !flagged[boxX][boxY];
                } else {
                    if(!flagged[boxX][boxY]) {
                        revealed[boxX][boxY] = true;
                    }
                }
            } else{
                System.out.println("The pointer is not in the box");
            }
            if(inSmiley()){
                resetAll();
            }else{
                System.out.println("The pointer is not inside the smiley!");
            }
            if(inFlag()){
                if(!flag) {
                    flag = true;
                    System.out.println("In flag = true");
                } else{
                    flag = false;
                    System.out.println("In flag = false");
                }
            }
        }

        @Override
        public void mouseEntered(MouseEvent arg0) {

        }

        @Override
        public void mouseExited(MouseEvent arg0) {

        }

        @Override
        public void mousePressed(MouseEvent arg0) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }
    }
    public void boardGenerator(){
        for(int i = 0; i < 16; i++) {
            for (int j = 0; j < 7; j++) {
                if (rand.nextInt(100) < 20) { // 2-10 Easy, 11-20 Medium/Norm, 21-30 Hard
                    mines[i][j] = 1;
                } else {
                    mines[i][j] = 0;
                }
                revealed[i][j] = false;
                flagged[i][j] = false;
            }
        }

        for(int i = 0; i < 16; i++) {
            for (int j = 0; j < 7; j++) {
                neighs = 0;
                for(int k = 0; k < 16; k++) {
                    for (int l = 0; l < 7; l++) {
                        if (!(k == i && l == j)) {

                            if (isN(i, j, k, l))
                                neighs ++;
                        }
                    }
                }
                neighbours[i][j] = neighs;
            }
        }
        resetter = false;
    }

    public void checkVictoryStatus(){

        if(!defeat) {
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 7; j++) {
                    if (revealed[i][j] && mines[i][j] == 1) {
                        defeat = true;
                        happy = false;
                        endDate = new Date();
                    }
                }
            }
        }
        if(totalBoxesReveal() >= 144 - totalMines() && !victory){
            victory = true;
            endDate = new Date();
        }
    }
    public int totalMines(){
        int total = 0;

        for(int i = 0; i < 16; i++) {
            for (int j = 0; j < 7; j++) {
                if( mines[i][j] == 1){
                    total ++;
                }
            }
        }
        return total;
    }
    public int totalBoxesReveal(){
        int total = 0;

        for(int i = 0; i < 16; i++) {
            for (int j = 0; j < 7; j++) {
                if(revealed[i][j]){
                    total++;
                }
            }
        }
        return total;
    }

    public void resetAll(){

        resetter = true;
        flag = false;
        startDate = new Date();

        gameMesY = -50;
        gameMes = "Nothing yet!";
        happy = true;
        victory = false;
        defeat = false;
        boardGenerator();
    }

    public int inBoxX(){
        for(int i = 0; i < 16; i++){
            for(int j =0; j < 7; j++){
                if(mx >= spacing + i * 80 && mx < spacing + i * 80 + 80 - 2 * spacing &&
                        my >= spacing + j * 80 + 80 + 26 && my < spacing + j * 80 + 80 + 80 + 26 - 2 * spacing){
                    return i;
                }
            }
        }
        return -1;
    }

    public boolean inSmiley(){
        int dif = (int) Math.sqrt(Math.abs(mx - smileyCenterX) * Math.abs(mx - smileyCenterX) + Math.abs(my - smileyCenterY) * Math.abs(my - smileyCenterY));
        return dif < 35;
    }
    public boolean inFlag(){
        int dif = (int) Math.sqrt(Math.abs(mx - flagCenterX) * Math.abs(mx - flagCenterX) + Math.abs(my - flagCenterY) * Math.abs(my - flagCenterY));
        return dif < 35;
    }
    public int inBoxY(){
        for(int i = 0; i < 16; i++){
            for(int j =0; j < 7; j++){
                if(mx >= spacing + i * 80 && mx < spacing + i * 80 + 80 - 2 * spacing &&
                        my >= spacing + j * 80 + 80 + 26 && my < spacing + j * 80 + 80 + 80 + 26 - 2 * spacing){
                    return j;
                }
            }
        }
        return -1;
    }

    public static boolean isN(int mX, int mY, int cX, int cY){

        return mX - cX < 2 && mX - cX > -2 && mY - cY < 2 && mY - cY > -2 && mines[cX][cY] == 1;
    }
}