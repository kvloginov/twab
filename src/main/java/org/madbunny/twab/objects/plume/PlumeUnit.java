package org.madbunny.twab.objects.plume;

import org.madbunny.vsrat2d.api.*;

public class PlumeUnit {
    private final Point2D center;
    private float radius;
    private final float endRadius;
    private Color color;
    private final boolean active;

    public PlumeUnit(Point2D center, float startRadius, float endRadius, Color startColor, boolean active) {
        this.center = center;
        this.radius = startRadius;
        this.endRadius = endRadius;
        this.color = startColor;
        this.active = active;
    }


    public void Update(FrameContext ctx) {
        color = new Color(color.red(), color.green()+0.01f, color.blue(), 0);
        if (radius > 0.1) {
            radius -= 0.2;
        }
    }

    public void Draw(Screen screen) {
        screen.drawCircle(center, radius, new Paint(color, true));
    }
}
