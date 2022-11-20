package org.madbunny.twab.objects.animated;

import org.madbunny.twab.objects.Entity;
import org.madbunny.vsrat2d.api.*;

public class DisappearingChainElement implements Entity {

    private final Point2D from;
    private final Point2D to;
    private float radius;
    private final float endRadius;
    private Color color;
    private final boolean active;

    public DisappearingChainElement(Point2D from, Point2D to, float startRadius, float endRadius, Color startColor, boolean active) {
        this.from = from;
        this.to = to;
        this.radius = startRadius;
        this.endRadius = endRadius;
        this.color = startColor;
        this.active = active;
    }


    public void Update(FrameContext ctx) {
        color = new Color(color.red(), color.green() + 0.01f, color.blue(), 0);
        if (radius > 0.1) {
            radius -= 0.2;
        }
    }

    public void Draw(Screen screen) {
        screen.drawCircle(to, radius, new Paint(color, true));
        screen.drawCircle(from, radius, new Paint(color, true));
        screen.drawStripe(from, to, radius * 2, new Paint(color, true));
    }
}
