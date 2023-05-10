package com.andrei.game;
import javax.swing.JFrame;
public class Window extends JFrame {
    public Window(){
        setTitle("Dungeon Treasure");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new GamePanel(1280,720));
        setIgnoreRepaint(true);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
