package org.madbunny.twab.objects.plume;

import org.madbunny.vsrat2d.api.Color;
import org.madbunny.vsrat2d.api.FrameContext;
import org.madbunny.vsrat2d.api.Point2D;
import org.madbunny.vsrat2d.api.Screen;

import java.util.ArrayDeque;
import java.util.Deque;

public class Plume {
    private int count;
    private final float startRadius;
    private final float endRadius;
    private final Color startColor;

    private final float createPeriod;

    private final Deque<PlumeUnit> units;

    private float previousCreatedTimeAgo = 0;

    private Point2D lastMousePosition = new Point2D(0, 0);

    // TODO: it may just receive unitCreator
    public Plume(int count, float startRadius, float endRadius, Color startColor, float ttl) {
        this.count = count;
        this.startRadius = startRadius;
        this.endRadius = endRadius;
        this.startColor = startColor;

        this.createPeriod = ttl / (float) count;
        this.units = new ArrayDeque<>(count);
    }

    public void Update(FrameContext ctx) {
        previousCreatedTimeAgo += ctx.deltaTime();

        var updatedCount = 0;
        if (previousCreatedTimeAgo > this.createPeriod) {
            updatedCount = (int) (previousCreatedTimeAgo / this.createPeriod);

            var vecAll = Point2D.subtract(ctx.mouse().getMousePosition(), lastMousePosition);
            var vecOne = vecAll.divide(updatedCount);

            for (int i = 1; i <= updatedCount; i++) {
                var vecToAdd = vecOne.multiply(i);
                var center = lastMousePosition.add(vecToAdd);
                units.add(new PlumeUnit(center, startRadius, endRadius, startColor, true));
            }
            previousCreatedTimeAgo = 0;
        }

        if (units.size() > count) {
            for (int i = 0; i < units.size() - count; i++) {
                units.remove();
            }
        }
        lastMousePosition = ctx.mouse().getMousePosition();


        for (PlumeUnit unit : units) {
            unit.Update(ctx);
        }
    }

    public void Draw(Screen screen) {
        for (PlumeUnit unit : units) {
            unit.Draw(screen);
        }
    }

}
