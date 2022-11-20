package org.madbunny.twab.objects.plume;

import org.madbunny.twab.objects.AnimationContextImpl;
import org.madbunny.twab.objects.animated.AnimationContext;
import org.madbunny.twab.objects.Entity;
import org.madbunny.vsrat2d.api.FrameContext;
import org.madbunny.vsrat2d.api.MouseButton;
import org.madbunny.vsrat2d.api.Point2D;
import org.madbunny.vsrat2d.api.Screen;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Function;

public class Plume {
    private final Function<AnimationContext, Entity> unitConstructor;
    private int count;
    private final float createPeriod;
    private final Deque<Entity> units;
    private float previousCreatedTimeAgo = 0;
    private Point2D lastMousePosition = new Point2D(0, 0);
    private Point2D lastCreatedCenter = null;

    // TODO: it may just receive unitCreator
    public Plume(Function<AnimationContext, Entity> unitConstructor, int count, float ttl) {
        this.unitConstructor = unitConstructor;
        this.count = count;

        this.createPeriod = ttl / (float) count;
        this.units = new ArrayDeque<>(count);
    }

    public void Update(FrameContext ctx) {
        if (ctx.mouse().isButtonClicked(MouseButton.LEFT)) {
            lastCreatedCenter = null;
        }

        if (ctx.mouse().isButtonPressed(MouseButton.LEFT)) {
            previousCreatedTimeAgo += ctx.deltaTime();
        }

        var updatedCount = 0;
        if (previousCreatedTimeAgo > this.createPeriod && ctx.mouse().isButtonPressed(MouseButton.LEFT)) {
            updatedCount = (int) (previousCreatedTimeAgo / this.createPeriod);

            var vecAll = Point2D.subtract(ctx.mouse().getMousePosition(), lastMousePosition);
            var vecOne = vecAll.divide(updatedCount);

            for (int i = 1; i <= updatedCount; i++) {
                var vecToAdd = vecOne.multiply(i);
                var center = lastMousePosition.add(vecToAdd);

                var to = lastCreatedCenter == null ? center : lastCreatedCenter;

                units.add(unitConstructor.apply(new AnimationContextImpl(center, to)));
                lastCreatedCenter = center;
            }
            previousCreatedTimeAgo = 0;
        }

        if (units.size() > count) {
            for (int i = 0; i < units.size() - count; i++) {
                units.remove();
            }
        }
        lastMousePosition = ctx.mouse().getMousePosition();


        for (var unit : units) {
            unit.Update(ctx);
        }
    }

    public void Draw(Screen screen) {
        for (var unit : units) {
            unit.Draw(screen);
        }
    }

}
