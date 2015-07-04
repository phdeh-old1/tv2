/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.environment.entities;

import static java.lang.Math.*;
import org.newdawn.slick.Color;
import org.tmd.main.Declaration;
import org.tmd.main.Main;
import org.tmd.render.Side;
import org.tmd.render.gui.Mouse;
import org.tmd.render.scenes.Dungeon;

/**
 *
 * @author yew_mentzaki
 */
public class Entity {

    Dungeon dungeon = Declaration.dungeon;
    double x, y, size = 100, hp;
    public Side side = Side.FRONT;
    double speed = 2;
    int faction;
    boolean phantom = false;

    private int sign(double a) {
        if (a > 0) {
            return 1;
        }
        if (a < 0) {
            return -1;
        }
        return 0;
    }

    public Entity(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void tick() {
        move(cos(atan2(Mouse.y - y, Mouse.x - x)) * speed, sin(atan2(Mouse.y - y, Mouse.x - x)) * speed);
        
        for (Entity e : dungeon.getEntities()) {
            double d = sqrt(pow(e.x - x, 2) + pow(e.y - y, 2));
            if (d < (e.size + size) / 2) {
                double a = atan2(e.y - y, e.x - x);
                e.move(cos(a) * e.speed / 2, sin(a) * e.speed / 2);
                move(-cos(a) * speed / 2, -sin(a) * speed / 2);
            }
        }
    }

    public void move(double x, double y) {
        if (!dungeon.terrain.get(this.x + x + sign(x) * size / 2, this.y + y).solid) {
            this.x += x;
            this.y += y;
        } else if (!dungeon.terrain.get(this.x + x + sign(x) * size / 2, this.y).solid) {
            this.x += x;
        } else if (!dungeon.terrain.get(this.x * size / 2, this.y + y).solid) {
            this.y += y;
        }
        
        
    }

    public void rotate() {
        int cos = (int)()
    }
    
    public void longTick() {

    }

    public void render() {
        Main.g.setColor(Color.black);
        Main.g.fillOval((float) x - 50, (float) y - 100, 100, 100);
        Main.g.setColor(Color.pink);
        Main.g.fillOval((float) x - 40, (float) y - 90, 80, 80);

    }

    public int getRenderQueuePriority() {
        return (int) y;
    }

    public void hit(double damage, Entity from) {

    }
}
