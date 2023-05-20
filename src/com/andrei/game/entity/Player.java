package com.andrei.game.entity;

import com.andrei.game.graphics.Sprite;
import com.andrei.game.util.Camera;
import com.andrei.game.util.KeyHandler;
import com.andrei.game.util.MouseHandler;
import com.andrei.game.util.Vector2f;
import java.util.List;
import java.util.ArrayList;

import java.awt.*;

public class Player extends Entity {
    public int ATTACK_RIGHT = 2;
    public int ATTACK_LEFT = 3;
    public int ATTACK_UD_RIGHT = 4; //ud-->up down
    public int ATTACK_UD_LEFT = 5; //ud-->up down
    public int IDLE_RIGHT = 6;
    public int IDLE_LEFT = 7;
    private Camera cam;
    private int timer = 0;
    private int attackTime = 10;

    public Player(Camera cam, Sprite sprite, Vector2f origin, int size) {
        super(sprite, origin, size);
        this.cam = cam;
        maxSpeed = 4f;
        acc = 1f;
        deacc = 0.3f;
        bounds.setWidth(45);
        bounds.setHeight(20);
        bounds.setXOffset(43);
        bounds.setYOffset(80);
        hitBounds.setWidth(64);
        hitBounds.setHeight(64);

        ani.setNumFrames(5, ATTACK_RIGHT);
        ani.setNumFrames(5, ATTACK_LEFT);
        ani.setNumFrames(5, ATTACK_UD_RIGHT);
        ani.setNumFrames(5, ATTACK_UD_LEFT);
        ani.setNumFrames(4, IDLE_RIGHT);
        ani.setNumFrames(4, IDLE_LEFT);

    }

    private void move() {
        if (up) {
            dy -= acc;
            if (dy < -maxSpeed) {
                dy = -maxSpeed;
            }
        } else {
            if (dy < 0) {
                dy += deacc;
                if (dy > 0) {
                    dy = 0;
                }
            }
        }
        if (down) {
            dy += acc;
            if (dy > maxSpeed) {
                dy = maxSpeed;
            }
        } else {
            if (dy > 0) {
                dy -= deacc;
                if (dy < 0) {
                    dy = 0;
                }
            }
        }
        if (left) {
            dx -= acc;
            if (dx < -maxSpeed) {
                dx = -maxSpeed;
            }
        } else {
            if (dx < 0) {
                dx += deacc;
                if (dx > 0) {
                    dx = 0;
                }
            }
        }
        if (right) {
            dx += acc;
            if (dx > maxSpeed) {
                dx = maxSpeed;
            }
        } else {
            if (dx > 0) {
                dx -= deacc;
                if (dx < 0) {
                    dx = 0;
                }
            }

        }

    }

    public void update(List<Enemy> enemies) {
        super.update();
        if (timer != 0)
            timer--;
        if (timer == 0) {
            timer = 30;
            if (attack) {
                attackTime = 20;
            }
            for (Enemy enemy : enemies) {
                if (attack && hitBounds.collides(enemy.getBounds())) {
                    enemy.health -= 50;
                    System.out.println("Tinta lovita");
                    if (enemy.currentAnimation == UP) {
                        enemy.dy += 15;
                    }
                    if (enemy.currentAnimation == LEFT) {
                        enemy.dx += 15;
                    }
                    if (enemy.currentAnimation == RIGHT) {
                        enemy.dx -= 15;
                    }
                    if (enemy.currentAnimation == DOWN) {
                        enemy.dy -= 15;
                    }
                }

            }
        }
        move();
        if (!tc.collisionTile(dx, 0)) {
            pos.x += dx;
            xCol = false;

        } else {
            xCol = true;
        }
        if (!tc.collisionTile(0, dy)) {
            pos.y += dy;
            yCol = false;
        } else {
            yCol = true;
        }
        xCol = false;
        yCol = false;
    }

    public void input(MouseHandler mouse, KeyHandler key) {
//        if(mouse.getButton() == 1)
//        {
//            System.out.println("Player: " + pos.x + ", " + pos.y);
//        }
//        Testare coordonate

        if (key.up.down) {
            up = true;
        } else {
            up = false;
        }
        if (key.down.down) {
            down = true;
        } else {
            down = false;
        }
        if (key.left.down) {
            left = true;
        } else {
            left = false;
        }
        if (key.right.down) {
            right = true;
        } else {
            right = false;
        }
        if (key.attack.down && canAttack) {
            attack = true;
        } else {
            attack = false;
        }
        if (up && down) {
            up = false;
            down = false;
        }
        if (right && left) {
            left = false;
            right = false;
        }
        if (key.shift.down) {
            maxSpeed = 8;
            cam.setMaxSpeed((float) 7.8);
        } else {
            maxSpeed = 4;
            cam.setMaxSpeed(4);
        }
    }

    @Override
    public void animate() {
        if (up) {
            lastDir = UP;
        } else if (down) {
            lastDir = DOWN;
        } else if (right) {
            lastDir = RIGHT;
        } else if (left) {
            lastDir = LEFT;
        }
        if (left) {
            lastAni = LEFT;
        } else if (right) {
            lastAni = RIGHT;
        }

        if (attack) {
            if (lastAni == LEFT && lastDir == LEFT)
                if (currentAnimation != ATTACK_LEFT || ani.getDelay() == -1) {
                    setAnimation(ATTACK_LEFT, sprite.getSpriteArray(ATTACK_LEFT), 5);
                }
            if (lastAni == RIGHT && lastDir == RIGHT) {
                if (currentAnimation != ATTACK_RIGHT || ani.getDelay() == -1) {
                    setAnimation(ATTACK_RIGHT, sprite.getSpriteArray(ATTACK_RIGHT), 5);
                }
            }
            if ((lastDir == UP || lastDir == DOWN) && lastAni == RIGHT) {
                if (currentAnimation != ATTACK_UD_RIGHT || ani.getDelay() == -1) {
                    setAnimation(ATTACK_UD_RIGHT, sprite.getSpriteArray(ATTACK_UD_RIGHT), 5);
                }
            }
            if ((lastDir == UP || lastDir == DOWN) && lastAni == LEFT) {
                if (currentAnimation != ATTACK_UD_LEFT || ani.getDelay() == -1) {
                    setAnimation(ATTACK_UD_LEFT, sprite.getSpriteArray(ATTACK_UD_LEFT), 5);
                }
            }
        } else if (left) {
            if (currentAnimation != LEFT || ani.getDelay() == -1) {
                setAnimation(LEFT, sprite.getSpriteArray(LEFT), 5);
            }
        } else if (right) {
            if (currentAnimation != RIGHT || ani.getDelay() == -1) {
                setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 5);

            }

        } else if (up) {
            if (currentAnimation != lastAni || ani.getDelay() == -1) {
                setAnimation(lastAni, sprite.getSpriteArray(lastAni), 5);
            }
        } else if (down) {
            if (currentAnimation != lastAni || ani.getDelay() == -1) {
                setAnimation(lastAni, sprite.getSpriteArray(lastAni), 5);
            }
        } else if (!attack && !up && !down && !left && !right) {
            if (lastAni == RIGHT) {
                if (currentAnimation != IDLE_RIGHT || ani.getDelay() == -1) {
                    setAnimation(IDLE_RIGHT, sprite.getSpriteArray(IDLE_RIGHT), 9);
                }
            } else if (lastAni == LEFT) {
                if (currentAnimation != IDLE_LEFT || ani.getDelay() == -1) {
                    setAnimation(IDLE_LEFT, sprite.getSpriteArray(IDLE_LEFT), 9);
                }
            }
        } else {
            setAnimation(currentAnimation, sprite.getSpriteArray(currentAnimation), -1);
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.green);
        g.drawRect((int) (pos.getWorldVar().x + bounds.getXOffset()), ((int) (pos.getWorldVar().y + bounds.getYOffset())), (int) bounds.getWidth(), (int) bounds.getHeight());
        if (attack && timer == 0 || attackTime != 0) {
            attackTime--;
            g.setColor(Color.red);
            g.drawRect((int) (hitBounds.getPos().getWorldVar().x + hitBounds.getXOffset()), (int) (hitBounds.getPos().getWorldVar().y + hitBounds.getYOffset()), (int) hitBounds.getWidth(), (int) hitBounds.getHeight());
        }
        g.drawImage(ani.getImage(), (int) (pos.getWorldVar().x), (int) (pos.getWorldVar().y), size, size, null);
    }


}
